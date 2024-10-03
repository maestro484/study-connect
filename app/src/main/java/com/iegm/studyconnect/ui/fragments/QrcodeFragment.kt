package com.iegm.studyconnect.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R


class QrcodeFragment : Fragment() {

    lateinit var back: ImageView
    lateinit var topBar: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back = view.findViewById(R.id.back)

        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))



        back.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment()

        }

    }


}