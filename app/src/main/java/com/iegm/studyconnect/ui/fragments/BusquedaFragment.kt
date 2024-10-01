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
import com.google.apphosting.datastore.testing.DatastoreTestTrace.FirestoreV1Action.RemoveListen
import com.google.firebase.firestore.model.mutation.ArrayTransformOperation.Remove
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
    //

    lateinit var busquedaAdapter: BusquedaAdapter

    //aqui declaramos las variables de la vista busqueda
    lateinit var devolver: ImageView
    lateinit var materia: Button
    lateinit var fecha: Button
    lateinit var buscador: SearchView
    lateinit var apunte: Button
    lateinit var listaDeBusqueda: RecyclerView

    lateinit var topBar: ConstraintLayout

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
        fecha = view.findViewById(R.id.fecha)
        buscador = view.findViewById(R.id.buscador)
        apunte = view.findViewById(R.id.apunte)
        listaDeBusqueda = view.findViewById(R.id.ListaDeBusqueda)
        topBar = view.findViewById(R.id.topBar)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())

        busquedaAdapter = BusquedaAdapter(requireContext())


        listaDeBusqueda.apply {
            layoutManager = linearLayoutManager
            adapter = busquedaAdapter
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

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                busqueda = newText.toString()
                return true
            }
        })

        devolver.setOnClickListener {
            (activity as MainActivity).abrirHomeFragment()

        }
//aqui se hace el codigo para poder filtar las materias
        materia.setOnClickListener {
            filtrarMateria(data.grados[grado])
            (activity as MainActivity).abrirHomeFragment()
        }

        fecha.setOnClickListener {
            filtrarFecha(data.grados[grado])
            (activity as MainActivity).abrirApuntesFragment()
        }
        apunte.setOnClickListener {
            filtrarApunte(data.grados[grado])
            (activity as MainActivity).abrirApuntesFragment()

        }
    }

    //aqui es para que el buscador busque los elementos
    private fun buscar(busqueda: String, data: SchoolData) {
        Log.d("busqueda", "buscar: " + busqueda)
        val grado = data.grados[grado]


        objetos.clear()

        busquedaAdapter.apply {
            this.resultados.clear()
            notifyDataSetChanged()
        }

        val resultados = mutableListOf<Resultados>()

        grado.materias.map {
            val materia = it.nombre.toLowerCase().replaceAccents()
            Log.d("OscarNoHaceNada", "materia: " + materia)
            if (materia.contains(busqueda)) run {
                val resultado = Resultados(it.nombre, Tipo.MATERIA)
                resultados.add(resultado)
            }

            val profesor = it.profesor.toLowerCase().replaceAccents()
            if (profesor.contains(busqueda)) run {
                val resultado = Resultados(it.profesor, Tipo.PROFESOR)
                resultados.add(resultado)
            }

            if (it.periodos.isNotEmpty()) {
                it.periodos.map {
                    it.apuntes.map {
                        val apunte = it.nombre.toLowerCase().replaceAccents()

                        if (apunte.contains(busqueda)) run {
                            val resultado = Resultados(it.nombre, Tipo.APUNTE)
                            resultados.add(resultado)
                        }


                        var meses = "1"
                        when (busqueda) {
                            "enero" -> meses = 1.toString()
                            "febrero" -> meses = 2.toString()
                            "marzo" -> meses = 3.toString()
                            "abril" -> meses = 4.toString()
                            "mayo" -> meses = 5.toString()
                            "junio" -> meses = 6.toString()
                            "julio" -> meses = 7.toString()
                            "agosto" -> meses = 8.toString()
                            "septiembre" -> meses = 9.toString()
                            "octubre" -> meses = 10.toString()
                            "noviembre" -> meses = 11.toString()
                            "diciembre" -> meses = 12.toString()
                        }


                        val mes = it.mes.toString().toLowerCase().replaceAccents()
                        if (mes == meses) run {
                            val resultado = Resultados(it.nombre.toString(), Tipo.FECHA)
                            resultados.add(resultado)
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


