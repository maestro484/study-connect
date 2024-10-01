package com.iegm.studyconnect.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.lifecycle.lifecycleScope
import com.iegm.studyconnect.R
import com.rajat.pdfviewer.PdfRendererView
import com.rajat.pdfviewer.PdfViewerActivity
import com.rajat.pdfviewer.util.saveTo


class PdfFragment : Fragment() {

    lateinit var pickPdfButton: Button
    private var download_file_url = "https://css4.pub/2015/usenix/example.pdf"
    private var large_pdf = "https://research.nhm.org/pdfs/10840/10840.pdf"
    private var download_file_url1 = "https://css4.pub/2017/newsletter/drylab.pdf"
    private var download_file_url2 = "https://css4.pub/2015/textbook/somatosensory.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pdf2, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pdfView: PdfRendererView = view.findViewById(R.id.pdfView)
        //Creo que eso no va porque yo no tengo un   Button
     //   val showInView: Button = view.findViewById(R.id.showInView)

        //igual
     //   val pickPdfButton: Button = view.findViewById(R.id.pickPdfButton)

        pickPdfButton.setOnClickListener {
            launchFilePicker()
        }


        pdfView.statusListener = object : PdfRendererView.StatusCallBack {
            override fun onPdfLoadStart() {
                Log.i("statusCallBack", "onPdfLoadStart")
            }

            override fun onPdfLoadProgress(
                progress: Int,
                downloadedBytes: Long,
                totalBytes: Long?
            ) {
                //Download is in progress
            }

            override fun onPdfLoadSuccess(absolutePath: String) {
                Log.i("statusCallBack", "onPdfLoadSuccess")
            }

            override fun onError(error: Throwable) {
                Log.i("statusCallBack", "onError")
            }

            override fun onPageChanged(currentPage: Int, totalPage: Int) {
                //Page change. Not require
            }

        }

        pdfView.initWithUrl(
            url = download_file_url2,
            lifecycleCoroutineScope = lifecycleScope,
            lifecycle = lifecycle
        )
        pdfView.jumpToPage(3)
    }

    private fun launchPdfFromUrl(url: String) {

//        Headers can be passed like this, be default header will be empty.
//        val url = "http://10.0.2.2:5000/download_pdf" // Use 10.0.2.2 for Android emulator to access localhost
//        val headers = mapOf("Authorization" to "123456789")

        requireActivity().startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                context = requireContext(),
                pdfUrl = url,
                pdfTitle = "PDF Title",
                saveTo = saveTo.ASK_EVERYTIME,
                enableDownload = true
            )
        )
    }

    private val filePicker =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val selectedFileUri = result.data?.data
                selectedFileUri?.let { uri ->
                    launchPdfFromUri(uri.toString())
                }
            }
        }

    private fun launchFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
        }
        filePicker.launch(intent)
    }

    private fun launchPdfFromUri(uri: String) {
        requireActivity().startActivity(
            PdfViewerActivity.launchPdfFromPath(
                context = requireContext(), path = uri,
                pdfTitle = "Title", saveTo = saveTo.ASK_EVERYTIME, fromAssets = false
            )
        )
    }

    private fun launchPdfFromAssets(uri: String) {
        requireActivity().startActivity(
            PdfViewerActivity.launchPdfFromPath(
                context = requireContext(), path = uri,
                pdfTitle = "Title", saveTo = saveTo.ASK_EVERYTIME, fromAssets = true
            )
        )
    }


}

