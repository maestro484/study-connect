package com.iegm.studyconnect.ui.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.MateriasAdapter
import com.iegm.studyconnect.model.Grado
import com.iegm.studyconnect.model.SchoolData
import org.json.JSONObject
import java.io.InputStream

class MateriaFragment : Fragment() {
    private lateinit var materiasAdapter: MateriasAdapter
    private lateinit var listaDeMaterias: RecyclerView
    private lateinit var atras2: ImageView

    var grado: Int = 0

    var busqueda = ""

    companion object {
        fun newInstance() = MateriaFragment()
    }

    private val viewModel: MateriaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        atras2 = view.findViewById(R.id.Atras2)
        listaDeMaterias = view.findViewById(R.id.listaMaterias)


        materiasAdapter = MateriasAdapter()

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())


        listaDeMaterias.apply {
            layoutManager = linearLayoutManager
            adapter = materiasAdapter
        }

        val jsonString = readJsonFromRaw(requireContext(), R.raw.grupos)
        var jsonObject = JSONObject(jsonString)

        val gson = Gson()

        val data: SchoolData = gson.fromJson(jsonString, object : TypeToken<SchoolData>() {}.type)


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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_materia, container, false)
    }

    private fun readJsonFromRaw(context: Context, resourceId: Int): String {
        val inputStream: InputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }

}
