package com.iegm.studyconnect.ui.fragments

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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

          userList = ArrayList()
        recy = view.findViewById(R.id.mRecycler)
        regresar = view.findViewById(R.id.regresar)
        addsBtn = view.findViewById(R.id.addingBtn)
        userAdapter = UserAdapter(this,userList)
        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = userAdapter

      addsBtn.setOnClickListener { addInfo() }

        regresar.setOnClickListener {
           // (activity as MainActivity).abrirPeriodoFragment()
        }


        //agregar_apunte.setOnClickListener {
            //(activity as MainActivity).abrirApunteFragment
        }

    private fun addInfo() {

     val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item,null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(this)

            addDialog.setView(v)

        addDialog.setPositiveButton( "Ok"){

            dialog,_ ->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("Name: $names", " Mobile No. : $number"))
            userAdapter.notifyDataSetChanged()

            Toast.makeText(this, "Adding User Information Succsess",Toast.LENGTH_SHORT).show()

            dialog.dismiss()
         }

       addDialog.setNegativeButton("cancel"){
           dialog,_->
           dialog.dismiss()
          Toast.makeText(this, "cancel",Toast.LENGTH_SHORT).show()
       }
        addDialog.create()
        addDialog.show()

    }


}
