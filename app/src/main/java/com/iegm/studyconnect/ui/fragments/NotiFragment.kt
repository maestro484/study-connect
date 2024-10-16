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

    lateinit var notificaciones_2: Switch //Switch para activar/desactivar notificaciones
    lateinit var recientes: Switch // Switch para activar/desactivar notificaciones recientes
    lateinit var comentarios_noti: Switch // Switch para activar/desactivar notificaciones de comentarios
    lateinit var flecha3: ImageView // Imagen que permite regresar a la pantalla anterior
    lateinit var seguidos: Switch // Switch para activar/desactivar notificaciones de seguidos
    lateinit var menciones: Switch // Switch para activar/desactivar notificaciones de menciones
    lateinit var topBar: ConstraintLayout // Contenedor de la barra superior


    // Inicialización del ViewModel
    private val viewModel: NotiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama al método de la superclase
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño del fragmento de notificaciones
        return inflater.inflate(R.layout.fragment_noti, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización  (BUSCA EL id y le asigna la variable)
        notificaciones_2 = view.findViewById(R.id.notificaciones)
        recientes = view.findViewById(R.id.recientes)
        comentarios_noti = view.findViewById(R.id.comentarios_noti)
        flecha3 = view.findViewById(R.id.flecha3)
        seguidos = view.findViewById(R.id.seguidos)
        topBar = view.findViewById(R.id.topBar)

        //Establece el color de fondo de la barra superior según el tema actual
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Abre el fragmento de configuración
        flecha3.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment()

            // Listener para guardar las preferencias de notificación
            notificaciones_2.setOnClickListener {
                AdministradorDePreferencias.notificaciones_2 = notificaciones_2.isChecked
                // Guarda el estado del switch

                recientes.setOnClickListener {
                    AdministradorDePreferencias.recientes = recientes.isChecked
                    // Guarda el estado del switch
                }
                comentarios_noti.setOnClickListener {
                    AdministradorDePreferencias.comentarios_noti = comentarios_noti.isChecked
                    // Guarda el estado del switch

                }
                seguidos.setOnClickListener {
                    AdministradorDePreferencias.seguidos = seguidos.isChecked
                    // Guarda el estado del switch
                }
                menciones.setOnClickListener {
                    AdministradorDePreferencias.menciones = menciones.isChecked
                    // Guarda el estado del switch


                }
            }
        }
    }
}










