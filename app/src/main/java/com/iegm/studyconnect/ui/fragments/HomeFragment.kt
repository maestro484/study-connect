package com.iegm.studyconnect.ui.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.MateriasAdapter
import com.iegm.studyconnect.model.Grado
import com.iegm.studyconnect.model.SchoolData
import org.json.JSONObject
import java.io.InputStream

class HomeFragment : Fragment() {

    lateinit var ajuste: ImageView
    lateinit var buscador: SearchView
    lateinit var gradoG: TextView
    lateinit var listaDeMaterias: RecyclerView
    private lateinit var materiasAdapter: MateriasAdapter
    var grado: Int = 0

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ajuste = view.findViewById(R.id.ajuste)
        buscador = view.findViewById(R.id.Buscador)
        gradoG = view.findViewById(R.id.textViewG)
        listaDeMaterias = view.findViewById(R.id.ListaNueva)
        materiasAdapter = MateriasAdapter()


        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())

        ajuste.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment()
        }

        buscador.setOnClickListener {
            (activity as MainActivity).abrirBusquedaFragment()
        }

        listaDeMaterias.apply {
            layoutManager = linearLayoutManager
            adapter = materiasAdapter
        }

        val jsonString = readJsonFromRaw(requireContext(), R.raw.grupos)
        var jsonObject = JSONObject(jsonString)

        val gson = Gson()

        val data: SchoolData = gson.fromJson(jsonString, object : TypeToken<SchoolData>() {}.type)



        gradoG.text = "Grado ${data.grados[grado].grado}"

        materiasAdapter.materias = filtrarMateria(data.grados[grado])


    }

    fun filtrarMateria(grado: Grado): List<String> {
        val materias: MutableList<String> = mutableListOf()
        grado.materias.map {
            it.nombre
            materias.add(it.nombre)
        }
        return materias
    }
}