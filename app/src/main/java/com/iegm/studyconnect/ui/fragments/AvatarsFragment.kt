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

class AvatarsFragment(val onAvatarSelected: OnAvatarSelected) : BottomSheetDialogFragment() {

    lateinit var top_bar2: ConstraintLayout
    lateinit var listaAvatars: RecyclerView
    lateinit var flecha2: ImageView
    var avatarsAdapter: AvatarsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_avatars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        top_bar2 = view.findViewById(R.id.topBar3)
        top_bar2.setBackgroundColor(
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