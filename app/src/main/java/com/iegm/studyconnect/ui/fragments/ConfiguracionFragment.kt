package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class ConfiguracionFragment : Fragment() {

    lateinit var flecha: ImageView
    lateinit var adm_cuenta: Button
    lateinit var notificaciones: Button
    lateinit var theme: Button
    lateinit var terminos_condiciones : Button

    companion object {
        fun newInstance() = ConfiguracionFragment()
    }

    private val viewModel: ConfiguracionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_configuracion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flecha = view.findViewById(R.id.flecha)
        adm_cuenta = view.findViewById(R.id.cerrar_sesion)
        notificaciones = view.findViewById(R.id.notificaciones)
        theme = view.findViewById(R.id.theme)
        terminos_condiciones = view.findViewById(R.id.terminos_condiciones)

        adm_cuenta.setOnClickListener {
            (activity as MainActivity).abrirAdmFragment()
        }

        notificaciones.setOnClickListener {
            (activity as MainActivity).abrirNotiFragment()

        }

        theme.setOnClickListener {
            (activity as MainActivity).abrirThemeFragment()

        }

        flecha.setOnClickListener{


        }

        terminos_condiciones.setOnClickListener {
            (activity as MainActivity).abrirTerminosCondicionesFragment()

        }


    }

    }