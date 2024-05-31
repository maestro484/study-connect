package com.iegm.studyconnect.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.UserData
import com.iegm.studyconnect.view.UserAdapter

class apuntesFragment : Fragment() {
     lateinit var addsBtn : FloatingActionButton
    lateinit var  regresar : ImageButton
    lateinit var recy : RecyclerView
    lateinit var userList: ArrayList<UserData>
    lateinit var userAdapter: UserAdapter


    companion object {
        fun newInstance() = apuntesFragment()
    }

    private val viewModel: PeopleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_apuntes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recy = view.findViewById(R.id.mRecycler)
        regresar = view.findViewById(R.id.regresar)
        addsBtn = view.findViewById(R.id.addingBtn)

        addsBtn.setOnClickListener {
            //(activity as MainActivity).abrirApunteFragment()

        }

        regresar.setOnClickListener {
           // (activity as MainActivity).abrirPeriodoFragment()
        }


        agregar_apunte.setOnClickListener {
            //(activity as MainActivity).abrirApunteFragment
        }

    }
}