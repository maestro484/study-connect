package com.iegm.studyconnect.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.iegm.studyconnect.Manifest

class PerfilDeUsuarioFragment : Fragment() {

    lateinit var ir_a_imagenes: Button
    lateinit var regresar: ImageView
    lateinit var editar_numero: EditText
    lateinit var editar_nombre: EditText
    lateinit var editar_correo: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil_de_usuario, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ir_a_imagenes = view.findViewById(R.id.Ir_a_imagenes)
        regresar = view.findViewById(R.id.regresar)
        editar_numero = view.findViewById(R.id.editar_numero)
        editar_nombre = view.findViewById(R.id.editar_nombre)
        editar_correo = view.findViewById(R.id.editar_correo)



    }
}