package com.iegm.studyconnect

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iegm.studyconnect.model.Resultados
import com.iegm.studyconnect.ui.fragments.ApuntesFragment
import com.iegm.studyconnect.ui.fragments.BusquedaFragment
import com.iegm.studyconnect.ui.fragments.ComentariosFragment
import com.iegm.studyconnect.ui.fragments.ConfiguracionFragment
import com.iegm.studyconnect.ui.fragments.HomeFragment
import com.iegm.studyconnect.ui.fragments.NotiFragment
import com.iegm.studyconnect.ui.fragments.PdfFragment
import com.iegm.studyconnect.ui.fragments.PerfilDeUsuarioFragment
import com.iegm.studyconnect.ui.fragments.PeriodoFragment
import com.iegm.studyconnect.ui.fragments.QrcodeFragment
import com.iegm.studyconnect.ui.fragments.TerminosCondicionesFragment
import com.iegm.studyconnect.ui.fragments.ThemeFragment
class MainActivity : AppCompatActivity() {

    // Método principal que se ejecuta cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Activa el modo de visualización "edge-to-edge" (sin márgenes) en la interfaz
        setContentView(R.layout.activity_main) // Configura el diseño principal de la actividad

        // Configura el margen de las barras del sistema (barras de estado y navegación)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Cambia los colores de la barra de estado, la barra de navegación y el fondo
    fun cambiarColor(primaryDark: String, primary: String, background: String) {
        window.statusBarColor = Color.parseColor(primaryDark) // Color de la barra de estado
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(primary))) // Color de la ActionBar
        window.setBackgroundDrawable(ColorDrawable(Color.parseColor(background))) // Color de fondo de la ventana
        window.navigationBarColor = Color.parseColor(primary) // Color de la barra de navegación
    }

    // Método alternativo de onCreate que se llama al restaurar el estado persistente
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        permisoAlmacenamiento() // Solicita permisos de almacenamiento

        // Aplica el tema seleccionado por el usuario
        val tema = AppTheme.obtenerTema(this)
        AppTheme.aplicarTema(tema, this)
    }

    // Abre el fragmento de visualización de PDF con el nombre del archivo especificado
    fun abrirPdfFragment(nombre: String) {
        val pdfFragment: PdfFragment = PdfFragment(nombre)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, PdfFragment(nombre))
            .commitAllowingStateLoss()
    }

    // Abre el fragmento de apuntes
    fun abrirApuntesFragment() {
        val apuntesFragment: ApuntesFragment = ApuntesFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, apuntesFragment)
            .commitAllowingStateLoss()
    }

    // Abre el fragmento de Términos y Condiciones
    fun abrirTerminosCondicionesFragment() {
        val terminosCondicionesFragment: TerminosCondicionesFragment = TerminosCondicionesFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, terminosCondicionesFragment)
            .commitAllowingStateLoss()
    }

    // Abre el fragmento principal de la pantalla de inicio
    fun abrirHomeFragment() {
        val homeFragment: HomeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, homeFragment)
            .commitAllowingStateLoss()
    }

    // Abre el fragmento de periodos
    fun abrirPeriodoFragment() {
        val periodoFragment: PeriodoFragment = PeriodoFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, periodoFragment)
            .commitAllowingStateLoss()
    }

    // Abre la actividad de inicio de sesión (LoginActivity)
    fun abrirLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    // Abre el fragmento del perfil de usuario
    fun abrirPerfilDeUsuarioFragment() {
        val perfilDeUsuarioFragment: PerfilDeUsuarioFragment = PerfilDeUsuarioFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, perfilDeUsuarioFragment)
            .commitAllowingStateLoss()
    }

    // Abre el fragmento de configuración
    fun abrirConfiguracionFragment() {
        val configuracionFragment: ConfiguracionFragment = ConfiguracionFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, configuracionFragment)
            .commitAllowingStateLoss()
    }

    // Abre el fragmento de selección de tema
    fun abrirThemeFragment() {
        val themeFragment: ThemeFragment = ThemeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, themeFragment)
            .commitAllowingStateLoss()
    }

    // Abre el fragmento de comentarios
    fun abrirComentariosFragment() {
        val comentariosFragment: ComentariosFragment = ComentariosFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, comentariosFragment)
            .commitAllowingStateLoss()
    }

    // Abre el fragmento de notificaciones
    fun abrirNotiFragment() {
        val notiFragment: NotiFragment = NotiFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, notiFragment)
            .commitAllowingStateLoss()
    }

    // Solicita permisos de almacenamiento dependiendo de la versión de Android
    private fun permisoAlmacenamiento() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE
            )
        }
    }

    // Abre el fragmento de búsqueda
    fun abrirBusquedaFragment() {
        val busquedaFragment: BusquedaFragment = BusquedaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, busquedaFragment)
            .commitAllowingStateLoss()
    }

    // Abre el fragmento para escanear códigos QR
    fun abrirQrcodeFragment() {
        val abrirQrcodeFragment: QrcodeFragment = QrcodeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, abrirQrcodeFragment)
            .commitAllowingStateLoss()
    }
}

// Constantes para los códigos de solicitud de permisos
const val READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE = 2001
const val READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE = 207
