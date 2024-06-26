package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.iegm.studyconnect.R
import com.rajat.pdfviewer.PdfRendererView

class ApunteFragment : Fragment() {

    lateinit var pdfView : PdfRendererView
    lateinit var descrepcion : EditText
    lateinit var atras : ImageView
    lateinit var fileTitleTextView : TextView
    lateinit var addCommentButton : Button

    val representante : String = ""

    companion object {
        fun newInstance() = ApunteFragment()
    }

    private val viewModel: ApunteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_apunte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pdfView = view.findViewById(R.id.pdfView)
        descrepcion = view.findViewById(R.id.Descripción)
        atras = view.findViewById(R.id.Atras)
        fileTitleTextView = view.findViewById(R.id.fileTitleTextView)
        addCommentButton = view.findViewById(R.id.addCommentButton)

        atras.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment()
        }

        val rol : String = "representante"


        if (rol == representante) {
            descrepcion.isEnabled = true
            pdfView.isEnabled = true

        } else {
            descrepcion.isEnabled = false
            pdfView.isEnabled = true
        }



    }


}