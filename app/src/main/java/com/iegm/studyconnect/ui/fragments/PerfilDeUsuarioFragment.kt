package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.LoginActivity
import com.iegm.studyconnect.MainActivity
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
    lateinit var franja: ImageView
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
        franja = view.findViewById(R.id.franja)

        franja.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Obtener el tema guardado
        val temaActual = AppTheme.obtenerTema(requireActivity())


        fun setButtonColor(button: Button) {
            val color = when (temaActual) {
                AppTheme.moradoClaro -> "#C0A1DB" // Color para el tema morado claro
                AppTheme.moradoOscuro -> "#A799E0" // Color para el tema morado oscuro
                AppTheme.azul -> "#B6BADB"         // Color para el tema azul
                else -> "#CB69DB"                  // Color predeterminado
            }

            // Obtener el drawable existente y aplicar el nuevo color
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
            drawable?.mutate()?.colorFilter =
                PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)

            button.background = drawable  // Aplicar el drawable modificado al botón

        }

        // Aplicar el color a todos los botones
        setButtonColor(cerrar_sesion)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val avatar = sharedPref.getInt(SAVED_AVATAR_PROFILE, 0)

        if (avatar != 0) {
            abriravt.setImageResource(avatar)
        }


        abriravt.setOnClickListener {
            avatarsFragment = AvatarsFragment(this)
            avatarsFragment?.show(requireActivity().supportFragmentManager, "AvatarsFragment")
        }

        regresar.setOnClickListener {
            (activity as MainActivity).abrirPeriodoFragment()
        }

        cerrar_sesion.setOnClickListener {

            FirebaseAuth.getInstance().signOut()  // Cerrar sesión en Firebase

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)  // lo manda pal  LoginActivity

            requireActivity().finish()
            // Finalizar la actividad actual para evitar que el usuario vuelva a ella
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