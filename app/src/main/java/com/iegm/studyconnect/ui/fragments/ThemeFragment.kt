package com.iegm.studyconnect.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class ThemeFragment : Fragment() {

    lateinit var topBar: ConstraintLayout
    lateinit var predeterminado: RadioButton
    lateinit var azul: RadioButton
    lateinit var oscuro: RadioButton
    lateinit var flecha1: ImageView
    lateinit var claro: RadioButton

    companion object {
        fun newInstance() = ThemeFragment()
    }

    private val viewModel: ThemeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        topBar = view.findViewById(R.id.topBar)
        predeterminado = view.findViewById(R.id.predeterminado)
        azul = view.findViewById(R.id.azul)
        oscuro = view.findViewById(R.id.oscuro)
        flecha1 = view.findViewById(R.id.flecha1)
        claro = view.findViewById(R.id.claro)


        val mainActivity = (requireActivity() as MainActivity)


        topBar.setBackgroundColor(Color.parseColor(AppTheme.temaElegido))

        oscuro.setOnClickListener {
           topBar.setBackgroundColor(Color.parseColor(AppTheme.moradoOscuro))
                    AppTheme.aplicarTema(AppTheme.moradoOscuro2)
                    mainActivity.cambiarColor(AppTheme.moradoOscuro2, AppTheme.moradoOscuro2, AppTheme.gris)
        }

        predeterminado.setOnClickListener {
            topBar.setBackgroundColor(Color.parseColor(AppTheme.moradoClaro))
            AppTheme.aplicarTema(AppTheme.moradoClaro2)
            mainActivity.cambiarColor(AppTheme.moradoClaro2, AppTheme.moradoClaro2, AppTheme.gris)
        }


        azul.setOnClickListener {
            topBar.setBackgroundColor(Color.parseColor(AppTheme.azul))
            AppTheme.aplicarTema(AppTheme.azulClaro)
            mainActivity.cambiarColor(AppTheme.azulClaro, AppTheme.azulClaro, AppTheme.gris)

        }

        claro.setOnClickListener {
            topBar.setBackgroundColor(Color.parseColor(AppTheme.moradoClaro))
            AppTheme.aplicarTema(AppTheme.moradoClaro2)
            mainActivity.cambiarColor(AppTheme.moradoClaro2, AppTheme.moradoClaro2, AppTheme.gris)

        }
    }

}


