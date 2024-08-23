package com.iegm.studyconnect.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.utils.AdministradorDePreferencias

class NotiFragment : Fragment() {

    lateinit var notificaciones_2: Switch
    lateinit var recientes: Switch
    lateinit var comentarios_noti: Switch
    lateinit var flecha3: ImageView
    lateinit var seguidos: Switch
    lateinit var menciones: Switch
    lateinit var top_bar4: ConstraintLayout

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

        notificaciones_2 = view.findViewById(R.id.notificaciones)
        recientes = view.findViewById(R.id.recientes)
        comentarios_noti = view.findViewById(R.id.comentarios_noti)
        flecha3 = view.findViewById(R.id.flecha3)
        seguidos = view.findViewById(R.id.seguidos)
        menciones = view.findViewById(R.id.menciones)
        top_bar4 = view.findViewById(R.id.top_bar)

        top_bar4.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))


        flecha3.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment()

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










