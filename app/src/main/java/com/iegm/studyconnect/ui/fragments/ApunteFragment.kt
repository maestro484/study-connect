package com.iegm.studyconnect.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.storage.FirebaseStorage
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.rajat.pdfviewer.PdfRendererView
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo

class ApunteFragment : Fragment() {

    private lateinit var pdfView: PdfRendererView
    private lateinit var descripcion: EditText
    private lateinit var atras: ImageView
    private lateinit var fileTitleTextView: TextView

    private val storageRef = FirebaseStorage.getInstance().reference
    private val viewModel: ApunteViewModel by viewModels()

    companion object {
        fun newInstance() = ApunteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_apunte, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        pdfView = view.findViewById(R.id.pdfView1)
        descripcion = view.findViewById(R.id.Descripcion)
        atras = view.findViewById(R.id.Atras)
        fileTitleTextView = view.findViewById(R.id.fileTitleTextView)

        atras.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment()
        }

        val rol: String = "representante"

        if (rol == "representante") {
            descripcion.isEnabled = true
            pdfView.isEnabled = true
            fileTitleTextView.isEnabled = true
        } else {
            descripcion.isEnabled = false
            pdfView.isEnabled = false
            fileTitleTextView.isEnabled = false
        }

        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*"
        }

        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val fileUri: Uri? = data?.data
            fileUri?.let {
                val fileRef = storageRef.child("uploads/${it.lastPathSegment}")
                val uploadTask = fileRef.putFile(it)

                uploadTask.addOnSuccessListener {
                    // Manejar éxito
                    PdfViewerActivity.launchPdfFromPath(
                        context = requireContext(),
                        path = "your_file_path_or_uri_here",
                        pdfTitle = "Title",
                        saveTo = saveTo.ASK_EVERYTIME,
                        fromAssets = false
                    )
                }.addOnFailureListener {
                    // Manejar error
                }
            }
        }
    }
}
