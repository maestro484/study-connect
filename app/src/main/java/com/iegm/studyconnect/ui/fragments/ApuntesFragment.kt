package com.iegm.studyconnect.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.UserData
import com.iegm.studyconnect.view.UserAdapter

class ApuntesFragment : Fragment() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var volver1: ImageView
    private lateinit var recy: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    private lateinit var mMenus: ImageView
    private lateinit var button_comentarios: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_apuntes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userList = ArrayList()
        recy = view.findViewById(R.id.listaDeMaterias)
        volver1 = view.findViewById(R.id.volver1)
        addsBtn = view.findViewById(R.id.addingBtn)
        mMenus = view.findViewById(R.id.mMenus)
        button_comentarios = view.findViewById(R.id.button_comentarios)

        userAdapter =
            UserAdapter(requireContext(), this, userList) //pasarlo al fragment y no adapter
        recy.layoutManager = LinearLayoutManager(requireContext())
        recy.adapter = userAdapter

        addsBtn.setOnClickListener { addInfo() }

        volver1.setOnClickListener {
            // (activity as MainActivity).abrirPeriodoFragment()
        }


        /* addsBtn.setOnClickListener {
             (activity as MainActivity).abrirApunteFragment()
         } */

        button_comentarios.setOnClickListener {
            (activity as MainActivity).abriComentariosFragment()
        }
    }


    private fun addInfo() {

        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.add_item, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(requireContext())

        val representante: String = ""

        addDialog.setView(v)

        addDialog.setPositiveButton("Ok") {

                dialog, _ ->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("Name: $names", " Mobile No. : $number"))
            userAdapter.notifyDataSetChanged()

            Toast.makeText(requireContext(), "Adding User Information Succsess", Toast.LENGTH_SHORT)
                .show()

            dialog.dismiss()
        }

        addDialog.setNegativeButton("cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()

        val rol: String = "representante"

        if (rol == representante) {
            mMenus.isEnabled = true
            mMenus.visibility = View.VISIBLE

            addsBtn.isEnabled = true
            addsBtn.visibility = View.VISIBLE

        } else {
            mMenus.isEnabled = false
            mMenus.visibility = View.INVISIBLE

            addsBtn.isEnabled = false
            addsBtn.visibility = View.INVISIBLE


        }

    }

}

