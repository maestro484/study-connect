package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class TerminosCondicionesFragment : Fragment() {

    lateinit var flecha4: ImageView
    lateinit var topBar: ConstraintLayout

    companion object {
        fun newInstance() = TerminosCondicionesFragment()
    }

    private val viewModel: TerminosCondicionesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_terminos_condiciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flecha4 = view.findViewById(R.id.flecha4)
        topBar = view.findViewById(R.id.topBar3)


        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        flecha4.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment()

        }
    }
}