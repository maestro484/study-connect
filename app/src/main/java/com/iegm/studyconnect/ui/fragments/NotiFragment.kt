package com.iegm.studyconnect.ui.fragments

import android.app.Application
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.utils.AdministradorDePreferencias
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotiFragment : Fragment() {

    lateinit var notificaciones_2: Switch
    lateinit var recientes: Switch
    lateinit var comentarios_noti: Switch
    lateinit var flecha3: ImageView
    lateinit var seguidos: Switch
    lateinit var menciones: Switch

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
        seguidos = view.findViewById(R.id.seguidos)
        menciones = view.findViewById(R.id.menciones)


        flecha3.setOnClickListener {
            //(activity as MainActivity).abrirConfigurationFragment()

            notificaciones_2.setOnClickListener {
                AdministradorDePreferencias.notificaciones_2 = notificaciones_2.isChecked

                recientes.setOnClickListener {
                    AdministradorDePreferencias.recientes = recientes.isChecked
                }
                comentarios_noti.setOnClickListener {
                    AdministradorDePreferencias.comentarios_noti = comentarios_noti.isChecked

                }
                seguidos.setOnClickListener {
                    AdministradorDePreferencias.seguidos = seguidos.isChecked
                }
                menciones.setOnClickListener {
                    AdministradorDePreferencias.menciones = menciones.isChecked

                }
            }
        }
    }
}







