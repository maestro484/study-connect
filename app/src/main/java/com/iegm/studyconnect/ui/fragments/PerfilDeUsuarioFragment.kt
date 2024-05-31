package com.iegm.studyconnect.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iegm.studyconnect.R

class PerfilDeUsuarioFragment : Fragment() {

    lateinit var ir_a_imagenes: Button
    lateinit var regresar: ImageView
    lateinit var editar_numero: EditText
    lateinit var editar_nombre: EditText
    lateinit var editar_correo: EditText

    /*private var apuntesMutableList: MutableList<apuntes> =
        PerfilDeUsuarioFragment.apuntes.toMutableList()*/


    companion object {

        fun newInstance() = ConfiguracionFragment

    }

    private val viewModel: ConfiguracionViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil_de_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        6

        ir_a_imagenes = view.findViewById(R.id.ir_a_imagenes)
        regresar = view.findViewById(R.id.regresar)
        editar_numero = view.findViewById(R.id.editar_numero)
        editar_nombre = view.findViewById(R.id.editar_nombre)
        editar_correo = view.findViewById(R.id.editar_correo)

    }


}