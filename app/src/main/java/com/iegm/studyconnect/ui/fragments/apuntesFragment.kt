package com.iegm.studyconnect.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.UserData
import com.iegm.studyconnect.view.UserAdapter

class apuntesFragment : Fragment() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var volver1: ImageButton
    private lateinit var recy: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter


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
        volver1 = view.findViewById(R.id.volver1)
        addsBtn = view.findViewById(R.id.addingBtn)
        userAdapter = UserAdapter(requireContext(),this, userList) //pasarlo al fragment y no adapter
        recy.layoutManager = LinearLayoutManager(requireContext())
        recy.adapter = userAdapter

        addsBtn.setOnClickListener { addInfo() }

        volver1.setOnClickListener {
            // (activity as MainActivity).abrirPeriodoFragment()
        }


        //agregar_apunte.setOnClickListener {
        //(activity as MainActivity).abrirApunteFragment
    }


    private fun addInfo() {

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

   // fun onApunteClicked(recyclerView: RecyclerView, view: View, position: Int) {
      // val item = recyclerView.adapter?.getItemId(position) as ApunteFragment

        // Crea la vista de apunte
      //val vistaApunte = ApunteFragment.newInstance(item)

        // Recupera el FragmentManager
      // val fragmentManager = (view.context as Activity).abrirApunteFragment

        // Reemplaza el fragmento actual por la vista de apunte
       // transaction.replace(R.id.contenedor_fragmentos, ApunteFragment)

        // Añade la transacción a la pila de retroceso
    // transaction.addToBackStack("vista_apunte")

        // Ejecuta la transacción
        //transaction.commit()
    //}

}
