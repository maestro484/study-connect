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

        top_bar2 = view.findViewById(R.id.top_bar2)
        top_bar2.setBackgroundColor(
            Color.parseColor(AppTheme.temaElegido)
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