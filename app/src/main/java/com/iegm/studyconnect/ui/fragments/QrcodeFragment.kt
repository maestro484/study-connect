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
    lateinit var qr_image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back = view.findViewById(R.id.back)
        qr_image = view.findViewById(R.id.qr_image)
        topBar = view.findViewById(R.id.topBar)

        qr_image.setOnClickListener {

            // Crear el intent para abrir el navegador con la URL

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://juanmg777vg.wixsite.com/study-connect"))
            startActivity(intent)
        }

        // Cambiar el color de la barra superior usando el tema actual
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Configurar el botón de retroceso
        back.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment()
        }
    }
}
