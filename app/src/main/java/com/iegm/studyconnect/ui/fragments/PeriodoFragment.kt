package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.iegm.studyconnect.R

class PeriodoFragment : Fragment() {

    lateinit var periodo1 : Button
    lateinit var periodo2 : Button
    lateinit var periodo3 : Button
    lateinit var volver1 : ImageButton

    companion object {
        fun newInstance() = PeriodoFragment()
    }

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
        volver1 = view.findViewById(R.id.volver1)

        volver1.setOnClickListener {
        }

        periodo1.setOnClickListener {

        }
        periodo2.setOnClickListener {

        }
        periodo3.setOnClickListener {

        }

    }

}