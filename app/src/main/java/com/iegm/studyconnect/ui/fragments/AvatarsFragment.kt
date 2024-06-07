package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.AvatarsAdapter

class AvatarsFragment : Fragment() {

    lateinit var listaAvatars : RecyclerView
    lateinit var flecha2 : ImageView
    var avatarsAdapter : AvatarsAdapter? = null

    companion object {
        fun newInstance() = AvatarsFragment()
    }

    private val viewModel: AvatarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_avatars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatarsAdapter = AvatarsAdapter()
        listaAvatars= view.findViewById(R.id.listaAvatars)
        flecha2 = view.findViewById(R.id.flecha2)

        //flecha2.setOnClickListener {
//(activity as MainActivity).abrirPerfilDeUsuarioFragment()


        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        listaAvatars.apply {
            layoutManager = gridLayoutManager
            adapter = avatarsAdapter
        }


    }
}