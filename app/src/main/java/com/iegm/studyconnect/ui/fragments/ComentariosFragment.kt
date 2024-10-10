package com.iegm.studyconnect.ui.fragments

import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

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
    private val client = OkHttpClient() // Instancia de OkHttpClient

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

        devolver1 = view.findViewById(R.id.volver1)
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
                sendPushNotification(comentario)// Llama a la función para enviar la notificación
            } else {
                Toast.makeText(
                    requireContext(),
                    "Por favor ingresa algún texto",
                    Toast.LENGTH_SHORT
                ).show()
            }
            teclado.text.clear()
        }

        // Listener para los comentarios
        val messagesReference = database.child("comentarios")
        messagesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val messages =
                    dataSnapshot.children.mapNotNull { it.getValue(Comentario::class.java) }
                customAdapter.dataset = messages.toMutableList()
                customAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Manejo de errores aquí.
            }
        })
    }

    fun sendPushNotification(comentario: Comentario) {
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient()

            val json = """
            {
            "app_id": "335e2f0f-5f50-4378-896f-3eeda23a1c41",
                "filters": [
                    {
                        "field": "tag",
                        "key": "Grade",
                        "relation": "=",
                        "value": "11"
                    }
                ],
                "contents": {
                    "en": "comentario",
                    "es": "comentario"
                },
                "name": "INTERNAL_CAMPAIGN_NAME"
            }
        """.trimIndent()

            val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)

            val request = Request.Builder()
                .url("https://onesignal.com/api/v1/notifications")
                .post(requestBody)
                .addHeader("Authorization", "Basic OGUwMDk1YjYtZThjNS00MjI0LTgxNmEtOGMwMjAxZDNkODI4")
                .addHeader("accept", "application/json")
                .build()

            try {
                val response: Response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                // Regresar al hilo principal para manejar la respuesta
                withContext(Dispatchers.Main) {
                    // Manejar la respuesta aquí
                    println(responseBody)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
