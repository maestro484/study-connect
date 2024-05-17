package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.Grado

class BusquedaFragment : Fragment() {

    lateinit var devolver: Button
    lateinit var materia: Button
    lateinit var profesor: Button
    lateinit var fecha: Button
    lateinit var buscardor:SearchView
    lateinit var apunte:Button


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
        buscardor = view.findViewById(R.id.buscador)
        apunte=view.findViewById(R.id.apunte)

        devolver.setOnClickListener{

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


        val grupo : Grado = Grado()



    }
}