package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.iegm.studyconnect.R

class AdmFragment : Fragment() {

    lateinit var cerrar_sesion: Button
    lateinit var cambiar_tlf: Button
    lateinit var flecha: ImageView


    companion object {
        fun newInstance() = AdmFragment()
    }

    private val viewModel: AdmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_adm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flecha = view.findViewById(R.id.flecha)
        cambiar_tlf = view.findViewById(R.id.cambiar_tlf)
        cerrar_sesion = view.findViewById(R.id.cerrar_sesion)
    }
}