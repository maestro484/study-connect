package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.SearchView
import android.widget.TextView
import com.iegm.studyconnect.R

class HomeFragment : Fragment() {

    lateinit var ajuste : ImageView
    lateinit var buscador : SearchView
    lateinit var grado : TextView
    lateinit var listaNueva : ScrollView
    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ajuste = view.findViewById(R.id.Ajustes)
        buscador = view.findViewById(R.id.Buscador)
        grado = view.findViewById(R.id.textViewG)
        listaNueva = view.findViewById(R.id.ListaNueva)

        ajuste.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment()
        }

        buscador.setOnClickListener {
            (activity as MainActivity).abrirBusquedaFragment()
        }

        listaNueva.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment()
        }


        val grupo : Grado = Grado()
    }

}