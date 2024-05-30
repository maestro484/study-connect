package com.iegm.studyconnect.ui.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.Grado
import com.iegm.studyconnect.model.SchoolData
import org.json.JSONObject
import java.io.InputStream

class BusquedaFragment : Fragment() {

    lateinit var devolver: ImageView
    lateinit var materia: Button
    lateinit var profesor: Button
    lateinit var fecha: Button
    lateinit var buscador:SearchView
    lateinit var apunte:Button
    lateinit var listaDeBusqueda: RecyclerView
    var grado : Int = -1

    val objetos : MutableList<String> = mutableListOf()

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

        devolver = view.findViewById(R.id.devolver )
        materia = view.findViewById(R.id.materia)
        profesor = view.findViewById(R.id.profesor)
        fecha = view.findViewById(R.id.fecha)
        buscador = view.findViewById(R.id.buscador)
        apunte=view.findViewById(R.id.apunte)
        listaDeBusqueda = view.findViewById(R.id.ListaDeBusqueda)


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




        devolver.setOnClickListener{
            //aqui necesito que cuando se le unda al boton se debuelva a la anterior pagina
        }

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

    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream : InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use{ it.readText() }
    }

    fun filtrarMateria(grado: Grado){
        grado.materias.map {
            it.nombre
            objetos.add(it.nombre)
        }
    }

    fun  filtrarApunte(grado: Grado){
        for(materia in grado.materias){
            for(periodo in materia.periodos){
                periodo.apuntes.map {
                    it.nombre
                    objetos.add(it.nombre)
                }
            }
        }
    }



    fun filtrarProfesor(grado: Grado){
        grado.materias.map {
            it.profesor
            objetos.add(it.profesor)
        }
    }

    fun filtrarFecha(grado: Grado){
        for(materia in grado.materias){
            for (periodo in materia.periodos){
                periodo.apuntes.map {
                    it.mes
                    objetos.add(it.mes.toString())
                }
            }
        }
    }






}






