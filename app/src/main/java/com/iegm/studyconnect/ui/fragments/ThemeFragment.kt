package com.iegm.studyconnect.ui.fragments

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import com.google.android.material.internal.ClippableRoundedCornerLayout
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class ThemeFragment : Fragment() {

    lateinit var predeterminado: RadioButton
    lateinit var azul: RadioButton
    lateinit var oscuro: RadioButton
    lateinit var flecha: ImageView
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

        predeterminado = view.findViewById(R.id.predeterminado)
        azul = view.findViewById(R.id.azul)
        oscuro = view.findViewById(R.id.oscuro)
        //flecha = view.findViewById(R.id.flecha)
        claro = view.findViewById(R.id.claro)

        val mainActivity = (requireActivity() as MainActivity)

        oscuro.setOnClickListener {
            //mainActivity.cambiarColor("#550363", "#550363", "#181819")
        }

        predeterminado.setOnClickListener {
            //mainActivity.cambiarColor("#683781", "#A766C9", "#181819")


            azul.setOnClickListener {
                //mainActivity.cambiarColor("#7A8EFA", "#7A8EFA", "#181819")

            }

            claro.setOnClickListener {
                //mainActivity.cambiarColor("#683781", "#A766C9", "#181819")

            }

        }
    }
}

