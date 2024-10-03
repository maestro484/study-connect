package com.iegm.studyconnect.ui.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class ConfiguracionFragment : Fragment() {

    lateinit var flecha1: ImageView
    lateinit var notificaciones: Button
    lateinit var theme: Button
    lateinit var terminos_condiciones: Button
    lateinit var toBar: ConstraintLayout
    lateinit var qrButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_configuracion, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toBar = view.findViewById(R.id.topBar)

        toBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        flecha1 = view.findViewById(R.id.flecha1)
        notificaciones = view.findViewById(R.id.notificaciones)
        theme = view.findViewById(R.id.theme)
        terminos_condiciones = view.findViewById(R.id.terminos_condiciones)
        qrButton = view.findViewById(R.id.qrButton)


        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
        drawable?.mutate()?.colorFilter = PorterDuffColorFilter(Color.parseColor("#A15EDB"), PorterDuff.Mode.SRC_IN)
        notificaciones.background = drawable

        // Obtener el tema guardado y aplicar el color a los botones
        val temaActual = AppTheme.obtenerTema(requireActivity())

        when(temaActual) {
            AppTheme.moradoOscuro -> "#A15EDB" // Color para morado oscuro
            AppTheme.moradoClaro -> "#CB69DB"  // Color para morado claro
            AppTheme.azul -> "#B6BADB"          // Color para azul
            else -> "#CB69DB"                   // Color predeterminado
        }

        notificaciones.setOnClickListener {
            (activity as MainActivity).abrirNotiFragment()

        }

        qrButton.setOnClickListener {
            (activity as MainActivity).abrirQrcodeFragment()

        }

        theme.setOnClickListener {
            (activity as MainActivity).abrirThemeFragment()

        }

        flecha1.setOnClickListener {
            (activity as MainActivity).abrirHomeFragment()

        }

        terminos_condiciones.setOnClickListener {
            (activity as MainActivity).abrirTerminosCondicionesFragment()

        }


    }

}

