package com.iegm.studyconnect.ui.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.rajat.pdfviewer.PdfRendererView

class ApunteFragment : Fragment() {

    lateinit var pdfView: PdfRendererView
    lateinit var descripcion: EditText
    lateinit var atras: ImageView
    lateinit var fileTitleTextView: TextView
    lateinit var imageView: ImageView
    lateinit var Relative: RelativeLayout

    companion object {
        fun newInstance() = ApunteFragment()
    }

    private val viewModel: ApunteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        descripcion = view.findViewById(R.id.descripcion)
        atras = view.findViewById(R.id.Atras)
        fileTitleTextView = view.findViewById(R.id.fileTitleTextView)
        imageView = view.findViewById(R.id.imageView1)
        Relative = view.findViewById(R.id.relative)


        atras.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment()
        }

        val sharedPref = requireActivity().getSharedPreferences(
            getString(R.string.app_name), Context.MODE_PRIVATE)


        val representante = sharedPref.getBoolean("REPRESENTANTE", false)



        if (representante) {
            descripcion.isEnabled = true
            pdfView.isEnabled = true
            fileTitleTextView.isEnabled = true

        } else {
            descripcion.isEnabled = false
            pdfView.isEnabled = false
            fileTitleTextView.isEnabled = false

        }


    }


}