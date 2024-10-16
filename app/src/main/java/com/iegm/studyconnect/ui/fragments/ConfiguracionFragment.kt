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
    // Declaración de variables que representan elementos de la interfaz de usuario
    lateinit var flecha1: ImageView // Imagen que probablemente navega hacia atrás
    lateinit var notificaciones: Button // Botón para abrir la sección de notificaciones
    lateinit var theme: Button // Botón para abrir la configuración del tema
    lateinit var terminos_condiciones: Button // Botón para abrir los términos y condiciones
    lateinit var toBar: ConstraintLayout // Contenedor de la barra superior
    lateinit var qrButton: Button // Botón para abrir el escáner QR


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el layout del fragmento de configuración
        return inflater.inflate(R.layout.fragment_configuracion, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa la barra superior y establece su color de fondo según el tema actual
        toBar = view.findViewById(R.id.topBar)
        toBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Inicializa los botones y la imagen desde el layout
        flecha1 = view.findViewById(R.id.flecha1)
        notificaciones = view.findViewById(R.id.notificaciones)
        theme = view.findViewById(R.id.theme)
        terminos_condiciones = view.findViewById(R.id.terminos_condiciones)
        qrButton = view.findViewById(R.id.qrButton)

       // Modifica el fondo del botón de notificaciones
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
        drawable?.mutate()?.colorFilter = PorterDuffColorFilter(Color.parseColor("#A15EDB"), PorterDuff.Mode.SRC_IN)
        notificaciones.background = drawable

        // Obtener el tema guardado
        val temaActual = AppTheme.obtenerTema(requireActivity())

        // Función para aplicar color a los botones manteniendo el shape_background
        fun setButtonColor(button: Button) {
            val color = when (temaActual) {
                AppTheme.moradoClaro -> "#C0A1DB" // Color para el tema morado claro
                AppTheme.moradoOscuro -> "#A799E0" // Color para el tema morado oscuro
                AppTheme.azul -> "#B6BADB"         // Color para el tema azul
                else -> "#CB69DB"                  // Color predeterminado
            }

            // Obtener el drawable existente y aplicar el nuevo color
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
            drawable?.mutate()?.colorFilter = PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)

            button.background = drawable  // Aplicar el drawable modificado al botón
        }

        // Aplicar el color a todos los botones
        setButtonColor(notificaciones)
        setButtonColor(theme)
        setButtonColor(terminos_condiciones)
        setButtonColor(qrButton)

        // Configura los listeners para cada botón, abriendo el fragmento correspondiente
        notificaciones.setOnClickListener {
            (activity as MainActivity).abrirNotiFragment()  // Abre el fragmento de notificaciones

        }

        qrButton.setOnClickListener {
            (activity as MainActivity).abrirQrcodeFragment()  // Abre el fragmento del escáner QR


        }

        theme.setOnClickListener {
            (activity as MainActivity).abrirThemeFragment()  // Abre el fragmento Tema

        }

        flecha1.setOnClickListener {
            (activity as MainActivity).abrirHomeFragment() // Abre el fragmento principal Home

        }

        terminos_condiciones.setOnClickListener {
            (activity as MainActivity).abrirTerminosCondicionesFragment() // Abre el fragmento de terminos y condiciones

        }


    }

}

