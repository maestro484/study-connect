package com.iegm.studyconnect.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iegm.studyconnect.AppTheme
import com.iegm.studyconnect.MainActivity
import com.iegm.studyconnect.R
// Fragmento que permite al usuario seleccionar un tema para la aplicación
class ThemeFragment : Fragment() {

    // Declaración de variables para la interfaz de usuario
    lateinit var topBar: ConstraintLayout // Barra superior del fragmento
    lateinit var predeterminado: RadioButton // Opción de tema predeterminado
    lateinit var azul: RadioButton // Opción de tema azul
    lateinit var oscuro: RadioButton // Opción de tema oscuro
    lateinit var flecha1: ImageView // Imagen que permite regresar a la pantalla anterior
    lateinit var claro: RadioButton // Opción de tema claro

    companion object {
        // Método para crear una nueva instancia del fragmento
        fun newInstance() = ThemeFragment()
    }

    // Inicialización del ViewModel
    private val viewModel: ThemeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla el diseño del fragmento de selección de tema
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa los componentes de la interfaz desde el layout
        topBar = view.findViewById(R.id.topBar)
        predeterminado = view.findViewById(R.id.predeterminado)
        azul = view.findViewById(R.id.azul)
        oscuro = view.findViewById(R.id.morado)
        flecha1 = view.findViewById(R.id.flecha1)
        claro = view.findViewById(R.id.claro)

        // Obtiene una referencia a la actividad principal y la convierte a MainActivity
        val mainActivity = (requireActivity() as MainActivity)

        // Establece el color de fondo de la barra superior según el tema actual
        topBar.setBackgroundColor(Color.parseColor(AppTheme.obtenerTema(requireActivity())))

        // Configura el listener para el tema oscuro
        oscuro.setOnClickListener {
            topBar.setBackgroundColor(Color.parseColor(AppTheme.moradoOscuro))
            AppTheme.guardarTema(requireActivity(), AppTheme.moradoOscuro)
            mainActivity.cambiarColor(
                AppTheme.moradoOscuro2,
                AppTheme.moradoOscuro2,
                AppTheme.gris
            )
        }

        // Configura el listener para el tema predeterminado
        predeterminado.setOnClickListener {
            topBar.setBackgroundColor(Color.parseColor(AppTheme.moradoClaro))
            AppTheme.guardarTema(requireActivity(), AppTheme.moradoClaro)
            mainActivity.cambiarColor(
                AppTheme.moradoClaro2,
                AppTheme.moradoClaro2,
                AppTheme.gris
            )
        }

        // Configura el listener para el tema azul
        azul.setOnClickListener {
            topBar.setBackgroundColor(Color.parseColor(AppTheme.azul))
            AppTheme.guardarTema(requireActivity(), AppTheme.azul)
            mainActivity.cambiarColor(AppTheme.azulClaro, AppTheme.azulClaro, AppTheme.gris)
        }

        // Configura el listener para el tema claro
        claro.setOnClickListener {
            topBar.setBackgroundColor(Color.parseColor(AppTheme.moradoClaro))
            AppTheme.guardarTema(requireActivity(), AppTheme.moradoClaro)
            mainActivity.cambiarColor(
                AppTheme.moradoClaro2,
                AppTheme.moradoClaro2,
                AppTheme.gris
            )
        }

        // Configura el listener para regresar a la pantalla de configuración
        flecha1.setOnClickListener {
            (activity as MainActivity).abrirConfiguracionFragment() // Abre el fragmento de configuración
        }
    }
}

// Constante para la clave de las preferencias del tema guardado
const val SAVED_THEME = "saved_theme"
