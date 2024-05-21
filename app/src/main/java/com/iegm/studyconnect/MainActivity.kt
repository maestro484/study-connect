package com.iegm.studyconnect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun launchPdfFromUrl(url: String) {

//        Headers can be passed like this, be default header will be empty.
//        val url = "http://10.0.2.2:5000/download_pdf" // Use 10.0.2.2 for Android emulator to access localhost
//        val headers = mapOf("Authorization" to "123456789")

        startActivity(
            PdfViewerActivity.launchPdfFromUrl(
                context = this,
                pdfUrl = url,
                pdfTitle = "PDF Title",
                saveTo = saveTo.ASK_EVERYTIME,
                enableDownload = true
            )
        )
    }

}