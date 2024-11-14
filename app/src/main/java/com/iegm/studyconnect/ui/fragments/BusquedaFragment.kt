package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.BusquedaAdapter
import com.iegm.studyconnect.model.Grado
import com.iegm.studyconnect.model.Resultados
import com.iegm.studyconnect.model.SchoolData
import com.iegm.studyconnect.model.Tipo
import org.json.JSONObject
import java.io.InputStream
import java.text.Normalizer
class BusquedaFragment : Fragment() {

    lateinit var busquedaAdapter: BusquedaAdapter

    // Declaración de las variables para los elementos de la vista de búsqueda
    lateinit var devolver: ImageView
    lateinit var materia: Button
    lateinit var fecha: Button
    lateinit var buscador: SearchView
    lateinit var apunte: Button
    lateinit var listaDeBusqueda: RecyclerView

    lateinit var topBar: ConstraintLayout

    var grado: Int = 0  // Variable para almacenar el grado seleccionado

    val objetos: MutableList<String> = mutableListOf()  // Lista de objetos que se utilizarán en la búsqueda

    companion object {
        fun newInstance() = BusquedaFragment()  // Función para crear una nueva instancia del fragmento
    }

    private val viewModel: BusquedaViewModel by viewModels()  // ViewModel para manejar datos de la búsqueda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.fragment_busqueda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de las variables de la vista
        devolver = view.findViewById(R.id.devolver)
        materia = view.findViewById(R.id.materia)
        fecha = view.findViewById(R.id.fecha)
        buscador = view.findViewById(R.id.buscador)
        apunte = view.findViewById(R.id.apunte)
        listaDeBusqueda = view.findViewById(R.id.ListaDeBusqueda)
        topBar = view.findViewById(R.id.topBar)

        // Configuración de color de fondo para el botón 'materia'
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
        drawable?.mutate()?.colorFilter =
            PorterDuffColorFilter(Color.parseColor("#A15EDB"), PorterDuff.Mode.SRC_IN)
        materia.background = drawable

        // Obtener el tema guardado y aplicar colores
        val temaActual = AppTheme.obtenerTema(requireActivity())

        // Función para aplicar color a los botones manteniendo el fondo
        fun setButtonColor(button: Button) {
            val color = when (temaActual) {
                AppTheme.moradoClaro -> "#C0A1DB"
                AppTheme.moradoOscuro -> "#A799E0"  // Color para morado claro
                AppTheme.azul -> "#B6BADB"          // Color para azul
                else -> "#CB69DB"                   // Color predeterminado
            }

            // Modificar el color del fondo del botón
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
            drawable?.mutate()?.colorFilter =
                PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
            button.background = drawable
        }

        // Aplicar color a todos los botones
        setButtonColor(materia)
        setButtonColor(apunte)
        setButtonColor(fecha)

        // Aplicar el color de fondo del top bar según el tema
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Configuración del RecyclerView
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())
        busquedaAdapter = BusquedaAdapter(context)
        listaDeBusqueda.apply {
            layoutManager = linearLayoutManager
            adapter = busquedaAdapter
        }

        // Cargar datos de 'grupos' desde un archivo JSON
        val jsonString = readJsonFromRaw(requireContext(), R.raw.grupos)
        var jsonObject = JSONObject(jsonString)

        val gson = Gson()
        val data: SchoolData = gson.fromJson(jsonString, object : TypeToken<SchoolData>() {}.type)

        var busqueda = ""

        // Configurar el SearchView para realizar la búsqueda
        buscador.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                busqueda = query.toString()
                buscar(busqueda.toLowerCase().replaceAccents(), data)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                busqueda = newText.toString()
                return true
            }
        })

        // Configurar el botón 'devolver' para navegar al fragmento Home
        devolver.setOnClickListener {
            (activity as MainActivity).abrirHomeFragment()
        }

        // Configurar el botón 'materia' para filtrar por materia
        materia.setOnClickListener {
            filtrarMateria(data.grados[grado])
            (activity as MainActivity).abrirHomeFragment()
        }

        // Configurar el botón 'fecha' para filtrar por fecha
        fecha.setOnClickListener {
            filtrarFecha(data.grados[grado])
        }

        // Configurar el botón 'apunte' para filtrar por apuntes
        apunte.setOnClickListener {
            filtrarApunte(data.grados[grado])
            (activity as MainActivity).abrirApuntesFragment()
        }
    }

    // Función para realizar la búsqueda de resultados
    private fun buscar(busqueda: String, data: SchoolData) {
        Log.d("busqueda", "buscar: " + busqueda)
        val grado = data.grados[grado]

        objetos.clear()

        busquedaAdapter.apply {
            this.resultados.clear()
            notifyDataSetChanged()
        }

        val resultados = mutableListOf<Resultados>()

        // Buscar en materias y profesores
        grado.materias.map {
            val materia = it.nombre.toLowerCase().replaceAccents()
            if (materia.contains(busqueda)) run {
                val resultado = Resultados(it.nombre, Tipo.MATERIA)
                resultados.add(resultado)
            }

            val profesor = it.profesor.toLowerCase().replaceAccents()
            if (profesor.contains(busqueda)) run {
                val resultado = Resultados(it.profesor, Tipo.PROFESOR)
                resultados.add(resultado)
            }

            // Buscar en apuntes y fechas
            if (it.periodos.isNotEmpty()) {
                it.periodos.map {
                    it.apuntes.map {
                        val apunte = it.nombre.toLowerCase().replaceAccents()
                        val fecha = it.mes
                        if (apunte.contains(busqueda)) run {
                            val resultado = Resultados(it.nombre, Tipo.APUNTE)
                            resultados.add(resultado)
                        }

                        if (fecha.contains(busqueda)) run {
                            val resultado = Resultados(it.nombre, Tipo.FECHA)
                            resultados.add(resultado)
                        }
                    }
                }
            }
        }

        // Actualizar los resultados en el adaptador
        busquedaAdapter.apply {
            this.resultados.addAll(resultados)
            notifyDataSetChanged()
        }

        Log.d("busqueda", "buscar: " + resultados)
    }

    // Función para eliminar los acentos de una cadena de texto
    fun String.replaceAccents(): String {
        val chars = Normalizer.normalize(this, Normalizer.Form.NFD)
        return chars.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
    }

    // Función para leer un archivo JSON desde 'raw'
    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }

    // Funciones para filtrar los elementos de acuerdo con el grado y mostrar resultados
    fun filtrarMateria(grado: Grado) {
        grado.materias.map {
            it.nombre
            objetos.add(it.nombre)
        }
    }

    fun filtrarApunte(grado: Grado) {
        for (materia in grado.materias) {
            for (periodo in materia.periodos) {
                periodo.apuntes.map {
                    it.nombre
                    objetos.add(it.nombre)
                }
            }
        }
    }

    fun filtrarProfesor(grado: Grado) {
        grado.materias.map {
            it.profesor
            objetos.add(it.profesor)
        }
    }

    fun filtrarFecha(grado: Grado) {
        for (materia in grado.materias) {
            for (periodo in materia.periodos) {
                periodo.apuntes.map {
                    it.mes
                    objetos.add(it.mes.toString())
                }
            }
        }
    }
}
