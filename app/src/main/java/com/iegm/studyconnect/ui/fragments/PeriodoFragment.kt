package com.iegm.studyconnect.ui.fragments

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class PeriodoFragment (val materia: String): Fragment() {

    lateinit var periodo1: Button
    lateinit var periodo2: Button
    lateinit var periodo3: Button
    lateinit var volver1: ImageView
    lateinit var topBar: ConstraintLayout




    private val viewModel: PeriodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_periodo, container, false)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        periodo1 = view.findViewById(R.id.periodo1)
        periodo2 = view.findViewById(R.id.periodo2)
        periodo3 = view.findViewById(R.id.periodo3)
        volver1 = view.findViewById(R.id.devolver1)

        topBar = view.findViewById(R.id.topBar)


        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))


        periodo1.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment(0 , materia )
        }
        periodo2.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment(1, materia)
        }
        periodo3.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment(2, materia)

        }
        volver1.setOnClickListener {
             (activity as MainActivity).abrirHomeFragment()
        }


    }

}
