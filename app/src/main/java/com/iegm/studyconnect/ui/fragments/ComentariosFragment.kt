package com.iegm.studyconnect.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iegm.studyconnect.R
import com.iegm.studyconnect.adapter.ComentariosAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ComentariosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ComentariosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var devolver1: ImageView
    private lateinit var teclado: EditText
    private lateinit var buttonDeEnviar: Button
    private lateinit var listaDeComentarios: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }

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


        val listaDeComentarios =
            mutableListOf("juan", "vero", "felipe", "oscar", "1", "2", "3", "4", "5")
        val customAdapter = ComentariosAdapter()
        customAdapter.dataset = listaDeComentarios


        val recyclerView: RecyclerView = view.findViewById(R.id.listaDeMaterias)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = customAdapter

        buttonDeEnviar.setOnClickListener {
            val comentario = teclado.text.toString()
            if (comentario.isNotEmpty()) {
                customAdapter.dataset.add(comentario)
                customAdapter.notifyDataSetChanged()

                recyclerView.smoothScrollToPosition(customAdapter.itemCount - 1)
            }  else{
            Toast.makeText(requireContext(), "por favor ingresa  algun texto", Toast.LENGTH_SHORT).show()

        }
            teclado.text.clear()


        }
}


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment comentariosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ComentariosFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}