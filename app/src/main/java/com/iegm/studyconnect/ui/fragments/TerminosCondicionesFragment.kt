package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
// Fragmento que muestra los términos y condiciones de la aplicación
class TerminosCondicionesFragment : Fragment() {

    lateinit var flecha4: ImageView // Imagen que permite regresar a la pantalla anterior
    lateinit var topBar: ConstraintLayout // Contenedor de la barra superior

    // Inicialización del ViewModel
    private val viewModel: TerminosCondicionesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama al método de la superclase
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño del fragmento de términos y condiciones
        return inflater.inflate(R.layout.fragment_terminos_condiciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa la flecha de regreso y la barra superior desde el layout
        flecha4 = view.findViewById(R.id.flecha4)
        topBar = view.findViewById(R.id.topBar3)

        // Obtiene las preferencias compartidas de la actividad
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        // Establece el color de fondo de la barra superior según el tema actual
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Configura el listener para regresar a la pantalla de configuración
        flecha4.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment() // Abre el fragmento de configuración
        }
    }
}
