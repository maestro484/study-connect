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
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class PeriodoFragment: Fragment() {

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


        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
        drawable?.mutate()?.colorFilter =
            PorterDuffColorFilter(Color.parseColor("#A15EDB"), PorterDuff.Mode.SRC_IN)
        periodo1.background= drawable

        // Obtener el tema guardado
        val temaActual = AppTheme.obtenerTema(requireActivity())

        // Función para aplicar color al botón manteniendo el shape_background
        fun setButtonColor(button: Button) {
            val color = when (temaActual) {
                AppTheme.moradoClaro -> "#C0A1DB"
                AppTheme.moradoOscuro -> "#A799E0"  // Color para morado claro
                AppTheme.azul -> "#B6BADB"          // Color para azul
                else -> "#CB69DB"                   // Color predeterminado
            }

            // Obtener el drawable existente
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
            drawable?.mutate()?.colorFilter =
                PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)

            button.background = drawable // Aplicar el drawable modificado al botón
        }

        // Aplicar el color a todos los botones
        setButtonColor(periodo1)
        setButtonColor(periodo2)
        setButtonColor(periodo3)

        topBar = view.findViewById(R.id.topBar)


        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))


        periodo1.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment()
        }
        periodo2.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment()
        }
        periodo3.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment()

        }
        volver1.setOnClickListener {
             (activity as MainActivity).abrirHomeFragment()
        }


    }

}
