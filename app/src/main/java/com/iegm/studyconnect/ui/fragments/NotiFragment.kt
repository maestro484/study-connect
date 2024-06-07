package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.utils.AdministradorDePreferencias

class NotiFragment : Fragment() {

    lateinit var notificaciones_2: Switch
    lateinit var recientes: Switch
    lateinit var comentarios_noti: Switch
    lateinit var flecha3: ImageView


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

        notificaciones_2 = view.findViewById(R.id.notificaciones_2)
        recientes = view.findViewById(R.id.recientes)
        comentarios_noti = view.findViewById(R.id.comentarios_noti)
        flecha3 = view.findViewById(R.id.flecha3)

        flecha3.setOnClickListener {
            (activity as MainActivity).abrirConfigurationFragment()


            notificaciones_2.setOnClickListener {
                AdministradorDePreferencias.notificaciones_2 = notificaciones_2.isChecked

                recientes.setOnClickListener {
                    AdministradorDePreferencias.recientes = recientes.isChecked
                }
                comentarios_noti.setOnClickListener {
                    AdministradorDePreferencias.comentarios_noti = comentarios_noti.isChecked

                }
            }
        }
    }
}







