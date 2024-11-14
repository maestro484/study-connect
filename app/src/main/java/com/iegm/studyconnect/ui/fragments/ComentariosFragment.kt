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
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.ComentariosAdapter
import com.iegm.studyconnect.model.Comentario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

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

    // Declaración de las variables que se inicializarán más tarde con findViewById
    private lateinit var devolver1: ImageView
    private lateinit var teclado: EditText
    private lateinit var buttonDeEnviar: Button
    private lateinit var listaDeComentarios: RecyclerView

    // Instancia de OkHttpClient para hacer solicitudes HTTP
    private val client = OkHttpClient()

    // Método onCreate, que se ejecuta cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Método onCreateView, inflará el diseño del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout para este fragmento
        return inflater.inflate(R.layout.fragment_comentarios, container, false)
    }

    // Método onViewCreated, se ejecuta una vez que la vista del fragmento ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de las vistas (ImageView, Button, EditText)
        devolver1 = view.findViewById(R.id.devolver1)
        buttonDeEnviar = view.findViewById(R.id.buttonDeEnviar)
        teclado = view.findViewById(R.id.teclado)

        // Configuración del RecyclerView para mostrar los comentarios
        val customAdapter = ComentariosAdapter() // Adaptador personalizado para RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.listaDeMaterias)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext()) // Establece el diseño de lista
        recyclerView.adapter = customAdapter // Asocia el adaptador al RecyclerView

        // Configuración de Firebase para obtener los comentarios desde la base de datos
        val database = FirebaseDatabase.getInstance().reference
        val comentariosRef =
            database.child("grados/0/materias/0/comentarios") // Referencia a los comentarios
        Log.d("ComentariosFragment", comentariosRef.toString()) // Imprime la referencia para debug

        // Acción cuando el usuario hace clic en el botón devolver1 (probablemente para regresar a otro fragmento)
        devolver1.setOnClickListener {
            (activity as MainActivity).abrirApuntesFragment() // Llama a una función en la actividad principal
        }

        // Listener para escuchar cambios en los comentarios de Firebase
        comentariosRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val comentariosList =
                    mutableListOf<Comentario>() // Lista para almacenar los comentarios

                // Recorre todos los elementos del snapshot para extraer los comentarios
                for (comentarioSnapshot in snapshot.children) {
                    val comentario =
                        comentarioSnapshot.getValue(Comentario::class.java) // Convierte cada snapshot a un objeto Comentario
                    comentario?.let { comentariosList.add(it) } // Si el comentario no es null, se agrega a la lista
                }
                // Actualiza el adaptador con la nueva lista de comentarios
                customAdapter.dataset = comentariosList
                customAdapter.notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
            }

            // Método para manejar errores si la lectura de Firebase es cancelada
            override fun onCancelled(error: DatabaseError) {
                // Manejo de posibles errores al leer de Firebase (actualmente no se implementa)
            }
        })

        // Acción cuando el usuario hace clic en el botón de enviar
        buttonDeEnviar.setOnClickListener {
            val comentario = teclado.text.toString() // Obtiene el texto del EditText

            // Verifica que el comentario no esté vacío
            if (comentario.isNotEmpty()) {
                // Crea un objeto Comentario y lo guarda en Firebase
                val message = Comentario("Oscar", 6, comentario)
                comentariosRef.push().setValue(message) // Guarda el comentario en la base de datos

                // Llama a la función para enviar una notificación push
                sendPushNotification(comentario)
            } else {
                // Muestra un mensaje si el comentario está vacío
                Toast.makeText(
                    requireContext(),
                    "Por favor ingresa algún texto",
                    Toast.LENGTH_SHORT
                ).show()
            }
            teclado.text.clear() // Limpia el campo de texto después de enviar
        }
    }

    // Función para enviar una notificación push usando la API de OneSignal
    fun sendPushNotification(comentario: String) {
        // Ejecuta el código en un hilo de IO (entrada/salida) usando coroutines
        CoroutineScope(Dispatchers.IO).launch {
            val client = OkHttpClient() // Instancia de OkHttpClient para hacer la solicitud

            // Crea el cuerpo de la solicitud JSON con el comentario
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
                "en": "$comentario",
                "es": "$comentario"
            },
            "name": "INTERNAL_CAMPAIGN_NAME"
        }
        """.trimIndent()

            // Crea el cuerpo de la solicitud HTTP
            val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json)

            // Crea la solicitud HTTP POST
            val request = Request.Builder()
                .url("https://onesignal.com/api/v1/notifications")
                .post(requestBody)
                .addHeader(
                    "Authorization",
                    "Basic OGUwMDk1YjYtZThjNS00MjI0LTgxNmEtOGMwMjAxZDNkODI4"
                )
                .addHeader("accept", "application/json")
                .build()

            try {
                // Realiza la solicitud y obtiene la respuesta
                val response: Response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                // Regresa al hilo principal para manejar la respuesta de la notificación
                withContext(Dispatchers.Main) {
                    // Aquí puedes manejar la respuesta de la notificación (por ejemplo, mostrar un mensaje o log)
                    println(responseBody)
                }
            } catch (e: Exception) {
                // Si ocurre un error en la solicitud, lo imprime en el log
                e.printStackTrace()
            }
        }
    }
}
