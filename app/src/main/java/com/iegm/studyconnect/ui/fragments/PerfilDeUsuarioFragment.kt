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

    lateinit var regresar: ImageView // Variable para el botón de regreso
    lateinit var editar_nombre: EditText // Variable para el campo de edición del nombre
    lateinit var editar_correo: EditText // Variable para el campo de edición del correo
    lateinit var cerrar_sesion: Button // Variable para el botón de cerrar sesión
    lateinit var abriravt: CircleImageView // Variable para la imagen del avatar
    lateinit var topBar: ConstraintLayout // Variable para la barra superior del perfil

    private var avatarsFragment: AvatarsFragment? = null // Variable para el fragmento de selección de avatar

    // Método para inflar el layout del fragmento
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil_de_usuario, container, false)
    }

    // Método que se llama después de que la vista ha sido creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener las referencias a los elementos de la vista
        abriravt = view.findViewById(R.id.abrir_avt)
        cerrar_sesion = view.findViewById(R.id.cerrar_sesion)
        regresar = view.findViewById(R.id.devolver1)
        editar_nombre = view.findViewById(R.id.editar_nombre)
        editar_correo = view.findViewById(R.id.editar_correo)
        topBar = view.findViewById(R.id.constraintLayout)

        // Configurar el color de fondo del botón de cerrar sesión
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
        drawable?.mutate()?.colorFilter =
            PorterDuffColorFilter(Color.parseColor("#A15EDB"), PorterDuff.Mode.SRC_IN)
        cerrar_sesion.background = drawable

        // Obtener el tema actual guardado en la aplicación
        val temaActual = AppTheme.obtenerTema(requireActivity())

        // Función que aplica color a los botones manteniendo el background shape
        fun setButtonColor(button: Button) {
            val color = when (temaActual) {
                AppTheme.moradoClaro -> "#C0A1DB" // Color para el tema morado claro
                AppTheme.moradoOscuro -> "#A799E0"  // Color para el tema morado oscuro
                AppTheme.azul -> "#B6BADB"          // Color para el tema azul
                else -> "#CB69DB"                   // Color predeterminado
            }

            // Obtener el drawable existente y aplicar el color filtrado
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.shape_background)
            drawable?.mutate()?.colorFilter =
                PorterDuffColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
            button.background = drawable // Establecer el drawable modificado al botón
        }

        // Aplicar el color a todos los botones, en este caso solo al de cerrar sesión
        setButtonColor(cerrar_sesion)

        // Establecer el color del fondo de la barra superior
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Obtener el avatar guardado desde las preferencias
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val avatar = sharedPref.getInt(SAVED_AVATAR_PROFILE, 0)

        // Si el avatar no es 0, significa que hay uno guardado y se lo asignamos
        if (avatar != 0) {
            abriravt.setImageResource(avatar)
        }

        // Configurar el click listener para cambiar el avatar
        abriravt.setOnClickListener {
            avatarsFragment = AvatarsFragment(this) // Crear un nuevo fragmento de selección de avatar
            avatarsFragment?.show(requireActivity().supportFragmentManager, "AvatarsFragment") // Mostrar el fragmento
        }

        // Configurar el click listener para regresar al fragmento de inicio
        regresar.setOnClickListener {
            (activity as MainActivity).abrirHomeFragment() // Llamar a la función en MainActivity para abrir HomeFragment
        }

        // Configurar el click listener para cerrar sesión
        cerrar_sesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()  // Cerrar sesión en Firebase

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)  // Redirigir a LoginActivity

            requireActivity().finish() // Finalizar la actividad actual para evitar que el usuario regrese
        }
    }

    // Callback cuando un avatar es seleccionado
    override fun onAvatarClick(avatar: Int) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(SAVED_AVATAR_PROFILE, avatar)  // Guardar el avatar seleccionado
            apply()
        }

        abriravt.setImageResource(avatar)  // Establecer el nuevo avatar seleccionado

        avatarsFragment?.dismiss()  // Cerrar el fragmento de selección de avatar
    }
}
