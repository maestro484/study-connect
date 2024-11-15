package com.iegm.studyconnect.ui.fragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.fragment.app.viewModels
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
import com.iegm.studyconnect.DataManager
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class PeriodoFragment: Fragment() {

    // Declaración de los botones y vistas necesarias
    lateinit var periodo1: Button
    lateinit var periodo2: Button
    lateinit var periodo3: Button
    lateinit var volver1: ImageView
    lateinit var topBar: ConstraintLayout

    // Se usa el ViewModel correspondiente
    private val viewModel: PeriodoViewModel by viewModels()

    // Método que se llama cuando el Fragment es creado
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Método que infla el layout del Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_periodo, container, false)
    }

    // Método que se llama una vez que el layout ha sido creado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de las vistas
        periodo1 = view.findViewById(R.id.periodo1)
        periodo2 = view.findViewById(R.id.periodo2)
        periodo3 = view.findViewById(R.id.periodo3)
        volver1 = view.findViewById(R.id.devolver1)

        // Configuración del color de fondo para el botón periodo1
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
        drawable?.mutate()?.colorFilter =
            PorterDuffColorFilter(Color.parseColor("#A15EDB"), PorterDuff.Mode.SRC_IN)
        periodo1.background = drawable

        // Obtener el tema actual guardado y cambiar el color de los botones según el tema
        val temaActual = AppTheme.obtenerTema(requireActivity())

        // Función que determina el color del botón según el tema
        fun setButtonColor(button: Button) {
            val color = when (temaActual) {
                AppTheme.moradoClaro -> "#C0A1DB"   // Color para tema morado claro
                AppTheme.moradoOscuro -> "#A799E0"   // Color para tema morado oscuro
                AppTheme.azul -> "#B6BADB"           // Color para tema azul
                else -> "#CB69DB"                    // Color predeterminado
            }

            // Aplica el color a los botones
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
            drawable?.mutate()?.colorFilter =
                PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)

            button.background = drawable // Establece el color en el fondo del botón
        }

        // Aplica el color a los tres botones
        setButtonColor(periodo1)
        setButtonColor(periodo2)
        setButtonColor(periodo3)

        // Inicialización del top bar con el color correspondiente al tema
        topBar = view.findViewById(R.id.topBar)
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Configuración de los listeners para cada botón
        periodo1.setOnClickListener {
            DataManager.periodo = 0
            (activity as MainActivity).abrirApuntesFragment()  // Abre el fragmento de apuntes
        }
        periodo2.setOnClickListener {
            DataManager.periodo = 1
            (activity as MainActivity).abrirApuntesFragment()  // Abre el fragmento de apuntes
        }
        periodo3.setOnClickListener {
            DataManager.periodo = 2
            (activity as MainActivity).abrirApuntesFragment()  // Abre el fragmento de apuntes
        }

        // Configuración del listener para el botón "volver"
        volver1.setOnClickListener {
            (activity as MainActivity).abrirHomeFragment()  // Vuelve al fragmento principal (home)
        }
    }

}
