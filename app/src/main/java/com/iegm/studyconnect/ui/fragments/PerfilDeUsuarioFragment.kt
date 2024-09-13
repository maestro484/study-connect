package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.R
import de.hdodenhof.circleimageview.CircleImageView

class PerfilDeUsuarioFragment : Fragment(), OnAvatarSelected {

    lateinit var regresar: ImageView
    lateinit var editar_numero: EditText
    lateinit var editar_nombre: EditText
    lateinit var editar_correo: EditText
    lateinit var cerrar_sesion: Button
    lateinit var abriravt: CircleImageView
    lateinit var topBar: ConstraintLayout

    private var avatarsFragment: AvatarsFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil_de_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        abriravt = view.findViewById(R.id.abrir_avt)
        cerrar_sesion = view.findViewById(R.id.cerrar_sesion)
        regresar = view.findViewById(R.id.regresar)
        editar_numero = view.findViewById(R.id.editar_numero)
        editar_nombre = view.findViewById(R.id.editar_nombre)
        editar_correo = view.findViewById(R.id.editar_correo)
        topBar = view.findViewById(R.id.topBar3)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        val avatar = sharedPref?.getInt(SAVED_AVATAR_PROFILE, 0)

        if (avatar != 0) {
            abriravt.setImageResource(avatar!!)
        }

        abriravt.setOnClickListener {
            avatarsFragment = AvatarsFragment(this)
            avatarsFragment?.show(requireActivity().supportFragmentManager, "AvatarsFragment")
        }
    }

    override fun onAvatarClick(avatar: Int) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(SAVED_AVATAR_PROFILE, avatar)
            apply()
        }

        abriravt.setImageResource(avatar)

        avatarsFragment?.dismiss()
    }
}