package com.iegm.studyconnect.ui.fragments

import android.graphics.Color
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

class ConfiguracionFragment : Fragment() {

    lateinit var flecha1: ImageView
    lateinit var notificaciones: Button
    lateinit var theme: Button
    lateinit var terminos_condiciones: Button
    lateinit var toBar: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_configuracion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toBar = view.findViewById(R.id.topBar3)

       toBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        flecha1 = view.findViewById(R.id.flecha1)
        notificaciones = view.findViewById(R.id.notificaciones)
        theme = view.findViewById(R.id.theme)
        terminos_condiciones = view.findViewById(R.id.terminos_condiciones)


        notificaciones.setOnClickListener {
            (activity as MainActivity).abrirNotiFragment()

        }

        theme.setOnClickListener {
            (activity as MainActivity).abrirThemeFragment()

        }

        /*flecha1.setOnClickListener*/

        terminos_condiciones.setOnClickListener {
            (activity as MainActivity).abrirTerminosCondicionesFragment()

        }


    }

}

