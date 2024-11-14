package com.iegm.studyconnect.ui.fragments
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
    private lateinit var addsBtn: FloatingActionButton // Botón para agregar apuntes
    private lateinit var volver1: ImageView // Imagen para volver al fragmento anterior
    private lateinit var recy: RecyclerView // RecyclerView para mostrar la lista de apuntes
    private lateinit var userList: ArrayList<Apunte> // Lista para almacenar los apuntes
    private lateinit var userAdapter: UserAdapter // Adaptador para el RecyclerView
    private lateinit var button_comentarios: FloatingActionButton // Botón para abrir los comentarios
    private lateinit var top_bar: ConstraintLayout // Barra superior de la pantalla

    // Método que infla el layout del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_apuntes, container, false) // Inflamos el layout
    }

    // Método llamado después de que la vista se ha creado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializamos las vistas
        userList = ArrayList()
        recy = view.findViewById(R.id.listaDeApuntes)
        volver1 = view.findViewById(R.id.devolver1)
        addsBtn = view.findViewById(R.id.addingBtn)
        button_comentarios = view.findViewById(R.id.button_comentarios)
        top_bar = view.findViewById(R.id.top_bar1)

        // Cambiamos el color de fondo de la barra superior según el tema
        top_bar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Inicializamos el adaptador y configuramos el RecyclerView
        userAdapter = UserAdapter(requireContext())
        recy.layoutManager = LinearLayoutManager(requireContext())
        recy.adapter = userAdapter

        // Configuramos el botón para agregar apuntes
        addsBtn.setOnClickListener { addInfo() }

        // Configuramos el botón para volver al fragmento anterior
        volver1.setOnClickListener {
            (activity as MainActivity).abrirPeriodoFragment() // Llamamos al método para abrir el fragmento del periodo
        }

        // Configuramos el botón para abrir el fragmento de comentarios
        button_comentarios.setOnClickListener {
            (activity as MainActivity).abrirComentariosFragment()
        }

        // Obtenemos las preferencias compartidas para verificar si el usuario es representante
        requireActivity().apply {
            val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
            val representantes = sharedPreferences.getBoolean("REPRESENTANTE", false)

            // Habilitamos o deshabilitamos el botón de agregar apuntes dependiendo de si el usuario es representante
            if (representantes) {
                addsBtn.isEnabled = true
            } else {
                addsBtn.isEnabled = false
            }
        }

        // Configuración de Firebase
        val database = FirebaseDatabase.getInstance().reference
        val apuntesRef = database.child("grados/0/materias/0/periodos/0/apuntes") // Referencia a la base de datos de apuntes

        Log.d("ApuntesFragment", apuntesRef.toString())

        // Escuchamos cambios en la base de datos y actualizamos la lista de apuntes
        apuntesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val apuntesList = mutableListOf<Apunte>()
                for (comentarioSnapshot in snapshot.children) {
                    val apunte = comentarioSnapshot.getValue(Apunte::class.java) // Obtenemos el apunte de la base de datos
                    apunte?.let { apuntesList.add(it) } // Añadimos el apunte a la lista
                }
                // Actualizamos la lista en el adaptador y notificamos los cambios
                userAdapter.userList = apuntesList
                userAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejamos errores posibles
            }
        })
    }

    // Método para agregar un nuevo apunte
    private fun addInfo() {
        // Inflamos el layout para el diálogo de agregar un apunte
        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.add_item, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)

        // Configuramos el diálogo de agregar
        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)
        val database = FirebaseDatabase.getInstance().reference
        val apuntesRef = database.child("grados/${DataManager.grado}/materias/${DataManager.materia}/periodos/${DataManager.periodo}/apuntes")

        Log.d("ApuntesFragment", apuntesRef.toString())

        // Configuramos los botones del diálogo
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val names = userName.text.toString() // Obtenemos el nombre del apunte
            val number = userNo.text.toString() // Obtenemos el número del apunte
            val apunte = Apunte(nombre = names, mes = number) // Creamos el objeto Apunte
            apuntesRef.push().setValue(apunte) // Lo añadimos a Firebase
            userAdapter.notifyDataSetChanged() // Actualizamos la lista en el adaptador
            Toast.makeText(requireContext(), "Adding User Information Success", Toast.LENGTH_SHORT)
                .show() // Mostramos un mensaje de éxito

            dialog.dismiss() // Cerramos el diálogo
        }

        addDialog.setNegativeButton("cancel") { dialog, _ ->
            dialog.dismiss() // Si se cancela, cerramos el diálogo
            Toast.makeText(requireContext(), "cancel", Toast.LENGTH_SHORT).show() // Mostramos un mensaje de cancelación
        }
        addDialog.create() // Creamos el diálogo
        addDialog.show() // Lo mostramos
    }
}

