package com.iegm.studyconnect.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.ComentariosAdapter
import com.iegm.studyconnect.model.Comentario

// Singleton para la lista de avatares
object AvatarProvider {
    val avatars = listOf(
        R.drawable.ardilla,
        R.drawable.ballena,
        R.drawable.buho,
        R.drawable.caballo,
        R.drawable.capibara,
        R.drawable.chita,
        R.drawable.cocodrilo,
        R.drawable.delfin,
        R.drawable.huron,
        R.drawable.leon,
        R.drawable.mapache,
        R.drawable.oso_pardo,
        R.drawable.oso_polar,
        R.drawable.oveja,
        R.drawable.pajaro,
        R.drawable.puma,
        R.drawable.serpiente,
        R.drawable.tiburon,
        R.drawable.tortuga,
        R.drawable.vaca,
        R.drawable.zorro
    )
}

class ComentariosFragment : Fragment() {

    private lateinit var devolver1: ImageView
    private lateinit var teclado: EditText
    private lateinit var buttonDeEnviar: Button
    private lateinit var listaDeComentarios: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comentarios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        devolver1 = view.findViewById(R.id.devolver1)
        buttonDeEnviar = view.findViewById(R.id.buttonDeEnviar)
        teclado = view.findViewById(R.id.teclado)

        // Configuración del RecyclerView
        val customAdapter = ComentariosAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.listaDeMaterias)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = customAdapter

        // Configuración de Firebase
        val database = FirebaseDatabase.getInstance().reference
        buttonDeEnviar.setOnClickListener {
            val comentario = teclado.text.toString()
            if (comentario.isNotEmpty()) {
                val message = Comentario("Oscar", 6, comentario)
                database.child("comentarios").push().setValue(message)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Por favor ingresa algún texto",
                    Toast.LENGTH_SHORT
                ).show()
            }
            teclado.text.clear()
        }

        val messagesReference = database.child("comentarios")
        messagesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val messages = dataSnapshot.children.mapNotNull { it.getValue(Comentario::class.java) }
                customAdapter.dataset = messages.toMutableList()
                customAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here.
            }
        })
    }

}
