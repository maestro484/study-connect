package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.iegm.studyconnect.R

class CNombreFragment : Fragment() {

    lateinit var guardar : Button
    lateinit var cancelar : Button
    lateinit var CNombre : EditText

    companion object {
        fun newInstance() = CNombreFragment()
    }

    private val viewModel: CNombreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_c_nombre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        guardar = view.findViewById(R.id.guardar)
        cancelar = view.findViewById(R.id.cancelar)
        CNombre = view.findViewById(R.id.CNombre)
    }
}