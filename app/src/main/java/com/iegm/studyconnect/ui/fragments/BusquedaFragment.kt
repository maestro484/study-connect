package com.iegm.studyconnect.ui.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iegm.studyconnect.R
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

    companion object {
        fun newInstance() = BusquedaFragment()
    }

    private val viewModel: BusquedaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // TODO: Use the ViewModel


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

        devolver.setOnClickListener{
 //aqui nesesito que cuando se le unda al boton se debuelba a la anterior pajina
        }

        materia.setOnClickListener {

          var filtro : String = "materia"
        }

      profesor.setOnClickListener {
         var filtro : String = "profesor"
      }

        fecha.setOnClickListener {
         var filtro : String = "fecha"
        }
        apunte.setOnClickListener {
         var filtro : String =  "apunte"
        }



        val jsonString = readJsonFromRaw(requireContext(), R.raw.grupos)
        var jsonObject = JSONObject(jsonString)

        val gson = Gson()

        val data: SchoolData = gson.fromJson(jsonString, object : TypeToken<SchoolData>() {}.type)

        Log.d("BusquedaFragment", data.grados[0].materias[0].nombre)
    }

    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream : InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use{ it.readText() }
    }



}