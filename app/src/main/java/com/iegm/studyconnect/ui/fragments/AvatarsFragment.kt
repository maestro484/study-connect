package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iegm.studyconnect.AppTheme
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
        return inflater.inflate(R.layout.fragment_avatars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        topBar = view.findViewById(R.id.topBar)
        topBar.setBackgroundColor(
            Color.parseColor(AppTheme.obtenerTema(requireActivity()))
        )

        avatarsAdapter = AvatarsAdapter(onAvatarSelected)
        listaAvatars = view.findViewById(R.id.listaAvatars)
        flecha2 = view.findViewById(R.id.flecha2)


        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        listaAvatars.apply {
            layoutManager = gridLayoutManager
            adapter = avatarsAdapter
        }


        flecha2.setOnClickListener {
            dismiss()
        }
    }
}

const val SAVED_AVATAR_PROFILE = "saved_avatar_profile"