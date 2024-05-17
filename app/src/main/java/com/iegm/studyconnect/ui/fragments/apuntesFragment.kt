package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class apuntesFragment : Fragment() {
    lateinit var lista_de_apuntes : RecyclerView
    lateinit var agregar_apunte :  Button
    lateinit var  regresar : ImageButton

    companion object {
        fun newInstance() = apuntesFragment()
    }

    private val viewModel: PeopleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_apuntes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista_de_apuntes = view.findViewById(R.id.lista_de_apuntes)
        agregar_apunte = view.findViewById(R.id.agregar_apunte)
        regresar = view.findViewById(R.id.regresar)

        regresar.setOnClickListener {
            (activity as MainActivity).abrirPeriodoFragment()
        }

        agregar_apunte.setOnClickListener {

        }

    }
}