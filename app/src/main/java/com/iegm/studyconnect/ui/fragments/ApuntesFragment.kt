package com.iegm.studyconnect.ui.fragments

// Importaciones necesarias
import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.DataManager
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.model.Apunte
import com.iegm.studyconnect.view.UserAdapter

class ApuntesFragment : Fragment() {
    // Declaración de variables
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var volver1: ImageView
    private lateinit var recy: RecyclerView
    private lateinit var userList: ArrayList<Apunte>
    private lateinit var userAdapter: UserAdapter
    private lateinit var button_comentarios: FloatingActionButton
    private lateinit var top_bar: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout del fragmento
        return inflater.inflate(R.layout.fragment_apuntes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de vistas y variables
        userList = ArrayList()
        recy = view.findViewById(R.id.listaDeApuntes)
        volver1 = view.findViewById(R.id.devolver1)
        addsBtn = view.findViewById(R.id.addingBtn)
        button_comentarios = view.findViewById(R.id.button_comentarios)
        top_bar = view.findViewById(R.id.top_bar1)

        // Configuración del tema de la barra superior
        top_bar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Configuración del adaptador y layout del RecyclerView
        userAdapter = UserAdapter(requireContext())
        recy.layoutManager = LinearLayoutManager(requireContext())
        recy.adapter = userAdapter

        // Configuración del botón "addsBtn" para agregar información
        addsBtn.setOnClickListener { addInfo() }

        // Configuración del botón "volver1" para volver al fragmento de período
        volver1.setOnClickListener {
            (activity as MainActivity).abrirPeriodoFragment()
        }

        // Configuración del botón "button_comentarios" para abrir el fragmento de comentarios
        button_comentarios.setOnClickListener {
            (activity as MainActivity).abrirComentariosFragment()
        }

        // Configuración de permisos para el botón "addsBtn" según las preferencias guardadas
        requireActivity().apply {
            val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
            val representantes = sharedPreferences.getBoolean("REPRESENTANTE", false)

            if (representantes) {
                addsBtn.isEnabled = true
                addsBtn.visibility = View.VISIBLE
            } else {
                addsBtn.isEnabled = false
                addsBtn.visibility = View.GONE
            }
        }

        // Configuración de la referencia a Firebase para obtener los apuntes
        val database = FirebaseDatabase.getInstance().reference
        val apuntesRef = database.child("grados/${DataManager.grado}/materias/${DataManager.materia}/periodos/${DataManager.periodo}/apuntes")

        // Registro del evento para escuchar cambios en los datos de Firebase
        Log.d("ApuntesFragment", apuntesRef.toString())
        apuntesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val apuntesList = mutableListOf<Apunte>()
                for (comentarioSnapshot in snapshot.children) {
                    val apunte = comentarioSnapshot.getValue(Apunte::class.java)
                    apunte?.let { apuntesList.add(it) }
                }
                // Actualización del adaptador del RecyclerView
                userAdapter.userList = apuntesList
                userAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores
            }
        })
    }

    private fun addInfo() {
        // Inflado de vista personalizada para el diálogo de agregar información
        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.add_item, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        // Configuración del diálogo de alerta para agregar apuntes
        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)
        val database = FirebaseDatabase.getInstance().reference
        val apuntesRef = database.child("grados/${DataManager.grado}/materias/${DataManager.materia}/periodos/${DataManager.periodo}/apuntes")

        // Acción al confirmar el diálogo de agregar información
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            val apunte = Apunte(nombre = names, mes = number)
            apuntesRef.push().setValue(apunte)
            Toast.makeText(requireContext(), "Adding User Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        // Acción al cancelar el diálogo
        addDialog.setNegativeButton("cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}
