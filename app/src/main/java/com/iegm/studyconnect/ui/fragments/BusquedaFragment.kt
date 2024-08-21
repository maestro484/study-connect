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
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.BusquedaAdapter
import com.iegm.studyconnect.model.Grado
import com.iegm.studyconnect.model.SchoolData
import org.json.JSONObject
import java.io.InputStream
import java.text.Normalizer

class BusquedaFragment : Fragment() {
    //

    lateinit var busquedaAdapter: BusquedaAdapter
//aqui declaramos las variables de la vista busqueda
    lateinit var devolver: ImageView
    lateinit var materia: Button
    lateinit var profesor: Button
    lateinit var fecha: Button
    lateinit var buscador: SearchView
    lateinit var apunte: Button
    lateinit var listaDeBusqueda: RecyclerView

    lateinit var topBar : ConstraintLayout

    var grado: Int = 0




    val objetos: MutableList<String> = mutableListOf()

    companion object {
        fun newInstance() = BusquedaFragment()
    }

    private val viewModel: BusquedaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_busqueda, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//aqui llamamos las variables de la lista de busqueda
        devolver = view.findViewById(R.id.devolver)
        materia = view.findViewById(R.id.materia)
        profesor = view.findViewById(R.id.profesor)
        fecha = view.findViewById(R.id.fecha)
        buscador = view.findViewById(R.id.buscador)
        apunte = view.findViewById(R.id.apunte)
        listaDeBusqueda = view.findViewById(R.id.ListaDeBusqueda)
        topBar = view.findViewById(R.id.topBar)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())

        busquedaAdapter = BusquedaAdapter()


        listaDeBusqueda.apply {
            layoutManager = linearLayoutManager
            adapter = busquedaAdapter
        }

        buscador.setOnClickListener {
            profesor.visibility = View.INVISIBLE
            fecha.visibility = View.INVISIBLE
            materia.visibility = View.INVISIBLE
            apunte.visibility = View.INVISIBLE
            listaDeBusqueda.visibility = View.VISIBLE
        }

        val jsonString = readJsonFromRaw(requireContext(), R.raw.grupos)
        var jsonObject = JSONObject(jsonString)

        val gson = Gson()

        val data: SchoolData = gson.fromJson(jsonString, object : TypeToken<SchoolData>() {}.type)

        var busqueda = ""

        buscador.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                busqueda = query.toString()
                buscar(busqueda.toLowerCase().replaceAccents(), data)
                profesor.visibility = View.INVISIBLE
                fecha.visibility = View.INVISIBLE
                materia.visibility = View.INVISIBLE
                apunte.visibility = View.INVISIBLE
                listaDeBusqueda.visibility = View.VISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                busqueda = newText.toString()
                return true
            }
        })

        devolver.setOnClickListener {
            //aqui necesito que cuando se le unda al boton se debuelva a la anterior pagina
        }
//aqui se hace el codigo para poder filtar las materias
        materia.setOnClickListener {
            filtrarMateria(data.grados[grado])
        }

        profesor.setOnClickListener {
            filtrarProfesor(data.grados[grado])
        }

        fecha.setOnClickListener {
            filtrarFecha(data.grados[grado])
        }
        apunte.setOnClickListener {
            filtrarApunte(data.grados[grado])
        }
    }
//aqui es para que el buscador busque los elementos
    private fun buscar(busqueda: String, data: SchoolData) {
        Log.d("busqueda", "buscar: " + busqueda)
        val grado = data.grados[grado]

    
        objetos.clear()

        val resultados = mutableListOf<String>()

        grado.materias.map {
            val materia = it.nombre.toLowerCase().replaceAccents()
            Log.d("OscarNoHaceNada", "materia: " + materia)
            if (materia.contains(busqueda)) run {
                resultados.add(it.nombre)
            }

            val profesor = it.profesor.toLowerCase().replaceAccents()
            if (profesor.contains(busqueda)) run {
                resultados.add(it.profesor)
            }

            if (it.periodos.isNotEmpty()) {
                it.periodos.map {
                    it.apuntes.map {
                        val apunte = it.nombre.toLowerCase().replaceAccents()
                        if (apunte.contains(busqueda)) run {
                            resultados.add(it.nombre)
                        }

                        val mes = it.mes.toString().toLowerCase().replaceAccents()
                        if (mes.contains(busqueda)) run {
                            resultados.add(it.mes.toString())
                        }
                    }
                }
            }
        }

        busquedaAdapter.apply {
            this.resultados.addAll(resultados)
            notifyDataSetChanged()
        }

        Log.d("busqueda", "buscar: " + resultados)
    }
//aqui es para que cuando busque el ususario aparesca enseguida su resultado
    fun String.replaceAccents(): String {
        val chars = Normalizer.normalize(this, Normalizer.Form.NFD)
        return chars.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
    }

    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }
//esto es para que los filtros busquen exactamente el lugar y lo que les corresponde
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





