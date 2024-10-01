package com.iegm.studyconnect.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.storage.FirebaseStorage
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.rajat.pdfviewer.PdfRendererView
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ApunteFragment : Fragment() {

    enum class SaveTo {
        ASK_EVERYTIME,
        // Otras opciones si las necesitas
    }


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


        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "*/*"
        }
        pdfView.initWithUrl(
            url = "http://www.scielo.org.pe/pdf/hm/v18n2/a05v18n2.pdf",
            lifecycleCoroutineScope = lifecycleScope,
            lifecycle = lifecycle
        )

        requireActivity().startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                context = requireContext(),
                pdfUrl = "http://www.scielo.org.pe/pdf/hm/v18n2/a05v18n2.pdf",
                pdfTitle = "PDF Title",
                saveTo = saveTo.ASK_EVERYTIME,
                enableDownload = true
            )
        )





        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val fileUri: Uri? = data?.data
            fileUri?.let {
                val fileRef = storageRef.child("uploads/${it.lastPathSegment}")
                val uploadTask = fileRef.putFile(it)

                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        uploadTask.await() // Espera a que se complete la carga
                        val downloadUri = fileRef.downloadUrl.await() // Espera la URL
                        withContext(Dispatchers.Main) {
                            requireActivity().startActivity(
                                PdfViewerActivity.launchPdfFromUrl(
                                    context = requireContext(),
                                    pdfUrl = "https://www.hq.nasa.gov/alsj/a17/A17_FlightPlan.pdf",
                                    pdfTitle = "PDF Title",
                                    saveTo = saveTo.ASK_EVERYTIME,
                                    enableDownload = true
                                )
                            )
                        }
                    } catch (e: Exception) {
                        Log.d("", "error: ${e.message}")
                    }
                }
            }
        }
    }


}
