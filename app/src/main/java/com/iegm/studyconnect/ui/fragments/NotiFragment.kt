package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.iegm.studyconnect.R

class NotiFragment : Fragment() {

    lateinit var notificaciones : Switch
    lateinit var recientes : Switch
    lateinit var noti_comentarios : Switch




    companion object {
        fun newInstance() = NotiFragment()
    }

    private val viewModel: NotiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_noti, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificaciones = view.findViewById(R.id.notificaciones)
        //recientes = view.findViewById(R.id.rosado)
        noti_comentarios = view.findViewById(R.id.oscuro)
    }

}
