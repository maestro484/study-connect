package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.MateriasAdapter
import com.iegm.studyconnect.model.Grado
import com.iegm.studyconnect.model.SchoolData
import org.json.JSONObject
import java.io.InputStream

class HomeFragment : Fragment() {

    // Variables para los elementos de la interfaz
    lateinit var ajuste: ImageView // Icono para ajustes
    lateinit var buscador: SearchView // Barra de búsqueda
    lateinit var gradoG: TextView // Texto que muestra el grado actual
    lateinit var listaDeMaterias: RecyclerView // Lista para mostrar las materias
    lateinit var perfil: ImageView // Icono para perfil de usuario
    lateinit var topBar: ConstraintLayout

    private val SAVED_AVATAR_PROFILE = "saved_avatar_profile"



    private var materiasAdapter: MateriasAdapter? = null // Adaptador para la lista de materias


    var grado: Int = 0 // Índice del grado actual


    private val viewModel: HomeViewModel by viewModels() // Inicializa el ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aquí se puede usar el ViewModel si es necesario
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla la vista del fragmento
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // Método para leer el archivo JSON desde la carpeta raw
    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() } // Lee el contenido del archivo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa los elementos de la interfaz
        ajuste = view.findViewById(R.id.ajuste)
        buscador = view.findViewById(R.id.Buscador)
        gradoG = view.findViewById(R.id.textViewG)
        listaDeMaterias = view.findViewById(R.id.ListaNueva)
        perfil = view.findViewById(R.id.perfil)
        topBar = view.findViewById(R.id.topBar)

        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Inicializa el adaptador de materias
        materiasAdapter = MateriasAdapter(context)

        // Configura el RecyclerView
        listaDeMaterias.apply {
            layoutManager = GridLayoutManager(requireContext(), 3) // Configura un GridLayout con 3 columnas
            adapter = materiasAdapter // Asigna el adaptador al RecyclerView
        }

        // Configura los listeners para los botones
        ajuste.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment() // Navega a la configuración
        }

        buscador.setOnClickListener {
            (activity as MainActivity).abrirBusquedaFragment() // Navega al fragmento de búsqueda
        }

        perfil.setOnClickListener {
            (activity as MainActivity).abrirPerfilDeUsuarioFragment() // Navega al perfil del usuario
        }

        listaDeMaterias.setOnClickListener {
            (activity as MainActivity).abrirPeriodoFragment() // Navega al fragmento de período
        }

        // Cargar el avatar guardado de SharedPreferences
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val avatar = sharedPref.getInt(SAVED_AVATAR_PROFILE, R.drawable.person_24dp_fill0_wght400_grad0_opsz24__1_) // Usa un avatar por defecto si no está configurado

        perfil.setImageResource(avatar) // Establece la imagen del avatar en el ImageView de perfil

        // Lee el archivo JSON con los datos de grupos
        val jsonString = readJsonFromRaw(requireContext(), R.raw.grupos)
        val jsonObject = JSONObject(jsonString) // Crea un objeto JSON a partir de la cadena leída

        val gson = Gson() // Inicializa Gson para convertir JSON a objetos
        val data: SchoolData = gson.fromJson(jsonString, object : TypeToken<SchoolData>() {}.type) // Convierte el JSON a SchoolData

        // Actualiza el texto con el grado actual

        requireActivity().apply {
            val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
            grado = sharedPreferences.getInt("GRADO_USUARIO", 0)
            when(grado){
                0 -> gradoG.text = "Grado 11"
                1 -> gradoG.text = "Grado 10"
                2 -> gradoG.text = "Grado 9"
                3 -> gradoG.text = "Grado 8"
            }
        }

        // Filtra las materias y las asigna al adaptador
        materiasAdapter!!.materias = filtrarMateria(data.grados[grado])
    }

    // Función para filtrar las materias según el grado
    fun filtrarMateria(grado: Grado): List<String> {
        val materias: MutableList<String> = mutableListOf() // Lista mutable para almacenar los nombres de las materias
        grado.materias.map {
            materias.add(it.nombre) // Agrega el nombre de cada materia a la lista
        }
        return materias // Devuelve la lista de nombres de materias
    }
}
