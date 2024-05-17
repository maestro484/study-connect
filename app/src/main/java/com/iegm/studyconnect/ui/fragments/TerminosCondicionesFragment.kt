package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class TerminosCondicionesFragment : Fragment() {

    lateinit var flecha: ImageView

    companion object {
        fun newInstance() = TerminosCondicionesFragment()
    }

    private val viewModel: TerminosCondicionesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_terminos_condiciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flecha = view.findViewById(R.id.flecha)

        flecha.setOnClickListener {
            (activity as MainActivity).abrirConfigurationFragment()

        }
    }
}