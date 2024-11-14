package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

    //aqui declaramos las variables de la vista busqueda
    lateinit var devolver: ImageView
    lateinit var materia: Button
    lateinit var fecha: Button
    lateinit var buscador: SearchView
    lateinit var apunte: Button
    lateinit var listaDeBusqueda: RecyclerView


    // Variable para la barra de título
    lateinit var topBar: ConstraintLayout

    var grado: Int = 0


    val objetos: MutableList<String> = mutableListOf()


    companion object {
        fun newInstance() = BusquedaFragment()
    }
// Método onCreate

    private val viewModel: BusquedaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Método onCreateView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_busqueda, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
 // Inicializamos las variables de la lista de busqueda
        devolver = view.findViewById(R.id.devolver)
        materia = view.findViewById(R.id.materia)
        fecha = view.findViewById(R.id.fecha)
        buscador = view.findViewById(R.id.buscador)
        apunte = view.findViewById(R.id.apunte)
        listaDeBusqueda = view.findViewById(R.id.ListaDeBusqueda)
        topBar = view.findViewById(R.id.topBar)
// Configuramos la RecyclerView
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())

        busquedaAdapter = BusquedaAdapter(context)


        listaDeBusqueda.apply {
            layoutManager = linearLayoutManager
            adapter = busquedaAdapter
        }

// Cargamos los datos desde el archivo raw "grupos"
        val jsonString = readJsonFromRaw(requireContext(), R.raw.grupos)
        var jsonObject = JSONObject(jsonString)

        val gson = Gson()

        val data: SchoolData = gson.fromJson(jsonString, object : TypeToken<SchoolData>() {}.type)

        var busqueda = ""
// Configuramos el campo de búsqueda
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
        // Evento al seleccionar el botón "Volver"
        devolver.setOnClickListener {
            (activity as MainActivity).abrirHomeFragment()

        }
        // Evento al seleccionar el botón "Materia"
        materia.setOnClickListener {
            filtrarMateria(data.grados[grado])
            (activity as MainActivity).abrirHomeFragment()
        }
        // Evento al seleccionar el botón "fecha"
        fecha.setOnClickListener {
            filtrarFecha(data.grados[grado])

        }
        apunte.setOnClickListener {
            filtrarApunte(data.grados[grado])
            (activity as MainActivity).abrirApuntesFragment()

        }
    }

    // Método para buscar elementos
    private fun buscar(busqueda: String, data: SchoolData) {
        Log.d("busqueda", "buscar: " + busqueda)
        val grado = data.grados[grado]

        // Limpia la lista de resultados y la adaptador
        objetos.clear()

        busquedaAdapter.apply {
            this.resultados.clear()
            notifyDataSetChanged()
        }
        // Crea una lista de resultados
        val resultados = mutableListOf<Resultados>()
        // Recorre las materias del grado seleccionado
        grado.materias.map {
            val materia = it.nombre.toLowerCase().replaceAccents()
            Log.d("OscarNoHaceNada", "materia: " + materia)
            if (materia.contains(busqueda)) run {
                val resultado = Resultados(it.nombre, Tipo.MATERIA)
                resultados.add(resultado)
            }
            // Recorre los profesores de las materias
            val profesor = it.profesor.toLowerCase().replaceAccents()
            if (profesor.contains(busqueda)) run {
                val resultado = Resultados(it.profesor, Tipo.PROFESOR)
                resultados.add(resultado)
            }
            // Recorre los apuntes de los periodos
            if (it.periodos.isNotEmpty()) {
                it.periodos.map {
                    it.apuntes.map {
                        val apunte = it.nombre.toLowerCase().replaceAccents()

                        val fecha  = it.mes

                        if (apunte.contains(busqueda)) run {
                            val resultado = Resultados(it.nombre, Tipo.APUNTE, )
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
        // Actualiza la lista de resultados y el adaptador
        busquedaAdapter.apply {
            this.resultados.addAll(resultados)
            notifyDataSetChanged()
        }

        Log.d("busqueda", "buscar: " + resultados)
    }

    // Método para reemplazar caracteres con acentos
    fun String.replaceAccents(): String {
        val chars = Normalizer.normalize(this, Normalizer.Form.NFD)
        return chars.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
    }
    // Método para leer el archivo raw "grupos"
    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }

    // Método para filtrar materia
    fun filtrarMateria(grado: Grado) {
        grado.materias.map {
            it.nombre
            objetos.add(it.nombre)
        }
    }
    // Método para filtrar apunte
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

    // Método para filtrar profesor
    fun filtrarProfesor(grado: Grado) {
        grado.materias.map {
            it.profesor
            objetos.add(it.profesor)
        }
    }
    // Método para filtrar fechas
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


