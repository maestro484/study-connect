package com.iegm.studyconnect.ui.fragments

import android.graphics.pdf.PdfRenderer
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.iegm.studyconnect.R
import com.rajat.pdfviewer.PdfRendererView

class ApunteFragment : Fragment() {

    lateinit var pdfView : PdfRendererView
    lateinit var descrepcion : EditText
    lateinit var Add : Button

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
        Add = view.findViewById(R.id.add)

        val roll : String = "representante"

        if (roll == representante) {
            descrepcion.isEnabled = true
            pdfView.isEnabled = true

        } else {
            descrepcion.isEnabled = false
            pdfView.isEnabled = true
        }



    }


}