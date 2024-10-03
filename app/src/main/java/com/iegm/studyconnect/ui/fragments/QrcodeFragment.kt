package com.iegm.studyconnect.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R

class QrcodeFragment : Fragment() {

    lateinit var back: ImageView
    lateinit var topBar: ConstraintLayout
    lateinit var imageQr: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar las vistas
        back = view.findViewById(R.id.back)
        topBar = view.findViewById(R.id.topBar)
        imageQr = view.findViewById(R.id.ImageQr)

        // Cambiar el color del topBar según el tema
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Listener para el botón "back"
        back.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment()
        }

        // Listener para el ImageView que abre la página web
        imageQr.setOnClickListener {
            val uri = Uri.parse("https://juanmg777vg.wixsite.com/study-connect")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}
