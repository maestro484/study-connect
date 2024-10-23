package com.iegm.studyconnect.ui.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.iegm.studyconnect.AppTheme
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
    private lateinit var button_comentarios: FloatingActionButton
    lateinit var top_bar: ConstraintLayout


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
        volver1 = view.findViewById(R.id.devolver1)
        addsBtn = view.findViewById(R.id.addingBtn)
        button_comentarios = view.findViewById(R.id.button_comentarios)

        top_bar = view.findViewById(R.id.top_bar1)


        top_bar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))


        userAdapter =
            UserAdapter(requireContext(), this, userList) //pasarlo al fragment y no adapter
        recy.layoutManager = LinearLayoutManager(requireContext())
        recy.adapter = userAdapter

        addsBtn.setOnClickListener { addInfo() }

        volver1.setOnClickListener {
            (activity as MainActivity).abrirPeriodoFragment()
        }


        button_comentarios.setOnClickListener {
            (activity as MainActivity).abrirComentariosFragment()
        }
    }


    private fun addInfo() {
//
        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.add_item, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(requireContext())


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



    }

}

class MyLinearLayoutClickListener(private val activity: AppCompatActivity) : View.OnClickListener {
    override fun onClick(v: View?) {

         Toast.makeText(activity, "¡LinearLayout clickeado desde clase aparte!", Toast.LENGTH_SHORT).show()
    }
}

