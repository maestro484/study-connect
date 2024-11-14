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
import com.google.android.gms.auth.api.Auth
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.LoginActivity
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
import de.hdodenhof.circleimageview.CircleImageView

class PerfilDeUsuarioFragment : Fragment(), OnAvatarSelected {

    // Declaración de las vistas utilizadas en el fragmento
    lateinit var regresar: ImageView
    lateinit var editar_nombre: EditText
    lateinit var editar_correo: EditText
    lateinit var cerrar_sesion: Button
    lateinit var abriravt: CircleImageView
    lateinit var topBar: ConstraintLayout

    // Fragmento para seleccionar el avatar
    private var avatarsFragment: AvatarsFragment? = null

    // Inflamos el layout para este fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        return inflater.inflate(R.layout.fragment_perfil_de_usuario, container, false)
    }

    // Se llama después de que la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializamos las vistas
        abriravt = view.findViewById(R.id.abrir_avt)
        cerrar_sesion = view.findViewById(R.id.cerrar_sesion)
        regresar = view.findViewById(R.id.devolver1)
        editar_nombre = view.findViewById(R.id.editar_nombre)
        editar_correo = view.findViewById(R.id.editar_correo)
        topBar = view.findViewById(R.id.constraintLayout)


        // Configuración del color de fondo del botón "cerrar sesión"
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
        drawable?.mutate()?.colorFilter =
            PorterDuffColorFilter(Color.parseColor("#A15EDB"), PorterDuff.Mode.SRC_IN)
        cerrar_sesion.background = drawable

        // Obtener el tema guardado
        val temaActual = AppTheme.obtenerTema(requireActivity())

        // Función que aplica el color al botón según el tema seleccionado
        fun setButtonColor(button: Button) {
            val color = when (temaActual) {
                AppTheme.moradoClaro -> "#C0A1DB"
                AppTheme.moradoOscuro -> "#A799E0"  // Color para el tema morado oscuro
                AppTheme.azul -> "#B6BADB"          // Color para el tema azul
                else -> "#CB69DB"                   // Color por defecto
            }

            // Obtener el drawable existente y modificarlo con el color elegido
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
            drawable?.mutate()?.colorFilter =
                PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)

            button.background = drawable // Aplicar el drawable modificado al botón
        }

        // Aplicar el color al botón "cerrar sesión"
        setButtonColor(cerrar_sesion)

        // Cambiar el color de la barra superior (top bar) con el tema actual
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Obtener el avatar guardado en las preferencias compartidas
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val avatar = sharedPref.getInt(SAVED_AVATAR_PROFILE, 0)

        // Si existe un avatar guardado, asignarlo al ImageView
        if (avatar != 0) {
            abriravt.setImageResource(avatar)
        }

        // Configuración del listener para abrir el fragmento de avatares
        abriravt.setOnClickListener {
            // Mostrar el fragmento de selección de avatares
            avatarsFragment = AvatarsFragment(this)
            avatarsFragment?.show(requireActivity().supportFragmentManager, "AvatarsFragment")
        }

        // Configuración del listener para el botón de regresar
        regresar.setOnClickListener {
            // Regresar al fragmento principal de la aplicación
            (activity as MainActivity).abrirHomeFragment()

        }

        // Configuración del listener para el botón de cerrar sesión
        cerrar_sesion.setOnClickListener {
            // Cerrar sesión en Firebase
            FirebaseAuth.getInstance().signOut()

            // Redirigir al LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

            // Finalizar la actividad actual para evitar que el usuario regrese a ella
            requireActivity().finish()


        }
    }

    // Método para manejar la selección de un avatar
    override fun onAvatarClick(avatar: Int) {
        // Guardar el avatar seleccionado en las preferencias compartidas
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(SAVED_AVATAR_PROFILE, avatar)
            apply()
        }

        // Actualizar la imagen del avatar en el ImageView
        abriravt.setImageResource(avatar)

        // Cerrar el fragmento de selección de avatares
        avatarsFragment?.dismiss()
    }

}
