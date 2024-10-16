package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.AvatarsAdapter
// Fragmento que muestra una lista de avatares para seleccionar
class AvatarsFragment(val onAvatarSelected: OnAvatarSelected) : BottomSheetDialogFragment() {

    // Declaración de variables para la interfaz de usuario
    lateinit var topBar: ConstraintLayout // Barra superior del fragmento
    lateinit var listaAvatars: RecyclerView // RecyclerView que muestra la lista de avatares
    lateinit var flecha2: ImageView // Imagen que permite cerrar el fragmento
    var avatarsAdapter: AvatarsAdapter? = null // Adaptador para manejar la lista de avatares

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño del fragmento de avatares
        return inflater.inflate(R.layout.fragment_avatars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtiene las preferencias compartidas de la actividad
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        // Inicializa la barra superior y aplica el color del tema actual
        topBar = view.findViewById(R.id.topBar)
        topBar.setBackgroundColor(
            Color.parseColor(AppTheme.obtenerTema(requireActivity()))
        )

        // Inicializa el adaptador de avatares con el callback de selección
        avatarsAdapter = AvatarsAdapter(onAvatarSelected)
        listaAvatars = view.findViewById(R.id.listaAvatars) // Inicializa el RecyclerView
        flecha2 = view.findViewById(R.id.flecha2) // Inicializa la flecha de cierre

        // Configura el layout manager para el RecyclerView en formato de grilla
        val gridLayoutManager = GridLayoutManager(requireContext(), 3) // 3 columnas

        listaAvatars.apply {
            layoutManager = gridLayoutManager // Asigna el layout manager al RecyclerView
            adapter = avatarsAdapter // Asigna el adaptador al RecyclerView
        }

        // Configura el listener para cerrar el fragmento al hacer clic en la flecha
        flecha2.setOnClickListener {
            dismiss() // Cierra el BottomSheetDialogFragment
        }
    }
}

// Constante para la clave de las preferencias del avatar guardado
const val SAVED_AVATAR_PROFILE = "saved_avatar_profile"
