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

//venice te voy a pasar un codigo de kotlin para que le agreges comentarios en linea, que comentes la funcion de ese codigo

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

    // Variables para referirse a los elementos de la vista.
    private lateinit var devolver1: ImageView
    private lateinit var teclado: EditText
    private lateinit var buttonDeEnviar: Button
    private lateinit var listaDeComentarios: RecyclerView

    // Método que se ejecuta cuando se crea el fragmento.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Método que infla la vista del fragmento.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comentarios, container, false)
    }

    // Método que se ejecuta cuando se ha vinculado la vista del fragmento.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencia a los elementos de la vista.
        devolver1 = view.findViewById(R.id.volver1)
        buttonDeEnviar = view.findViewById(R.id.buttonDeEnviar)
        teclado = view.findViewById(R.id.teclado)
        listaDeComentarios = view.findViewById(R.id.listaDeMaterias)

        // Configuración del RecyclerView.
        val customAdapter = ComentariosAdapter()
        listaDeComentarios.layoutManager = LinearLayoutManager(requireContext())
        listaDeComentarios.adapter = customAdapter

        // Configuración de Firebase.
        val database = FirebaseDatabase.getInstance().reference
        buttonDeEnviar.setOnClickListener {
            // Al hacer clic en el botón, obtiene el texto del EditText y verifica si está vacío.
            val comentario = teclado.text.toString()
            if (comentario.isNotEmpty()) {
                // Si no está vacío, crea un objeto Comentario con los datos del comentario y el autor.
                val message = Comentario("Oscar", 6, comentario)
                // Agrega el comentario a la base de datos de Firebase.
                database.child("comentarios").push().setValue(message)
            } else {
                // Si está vacío, muestra un mensaje de error.
                Toast.makeText(
                    requireContext(),
                    "Por favor ingresa algún texto",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Limpia el texto del EditText.
            teclado.text.clear()
        }


        // Escucha cambios en la base de datos de Firebase y actualiza el RecyclerView cuando hay cambios.
        val messagesReference = database.child("comentarios")
        messagesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Cuando hay cambios en la base de datos, extrae los comentarios y los pasa al adaptador.
                val messages = dataSnapshot.children.mapNotNull { it.getValue(Comentario::class.java) }
                customAdapter.dataset = messages.toMutableList()
                customAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Maneja errores aquí.
            }
        })
    }
}
