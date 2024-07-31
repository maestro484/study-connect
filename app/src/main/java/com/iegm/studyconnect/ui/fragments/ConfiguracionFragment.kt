/*package com.iegm.studyconnect.ui.fragments

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
    lateinit var tema: Button

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
/*  flecha = view.findViewById(R.id.flecha)
  adm_cuenta = view.findViewById(R.id.adm_cuenta)
  notificaciones = view.findViewById(R.id.notificaciones)
  tema = view.findViewById(R.id.tema)

  adm_cuenta.setOnClickListener {
      (activity as MainActivity).abrirAdmFragment()
  }

  notificaciones.setOnClickListener {

  }

  tema.setOnClickListener {

  }
}

}*/