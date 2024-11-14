// Paquete principal de la aplicación, donde se gestionan las interacciones con la UI
package com.iegm.studyconnect

// Importaciones necesarias para manejar las vistas, permisos y fragmentos
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
import com.iegm.studyconnect.ui.fragments.*

// Actividad principal que gestiona la navegación entre los fragmentos de la aplicación
class MainActivity : AppCompatActivity() {

    // Método onCreate que se ejecuta cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Habilita el modo de pantalla completa (sin márgenes)
        setContentView(R.layout.activity_main)  // Establece la vista de la actividad

        // Aplica los márgenes necesarios al inicio para que el contenido no quede tapado por las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Método para cambiar el color de la barra de estado, barra de navegación y fondo
    fun cambiarColor(primaryDark: String, primary: String, background: String) {
        window.statusBarColor = Color.parseColor(primaryDark)  // Cambia el color de la barra de estado
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(primary)))  // Cambia el color de la acción de la barra
        window.setBackgroundDrawable(ColorDrawable(Color.parseColor(background)))  // Cambia el color de fondo
        window.navigationBarColor = Color.parseColor(primary)  // Cambia el color de la barra de navegación
    }

    // Otro método onCreate que maneja la aplicación del tema y permisos de almacenamiento
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        permisoAlmacenamiento()  // Solicita permisos para acceder a almacenamiento externo

        // Obtiene y aplica el tema de la aplicación desde preferencias
        val tema = AppTheme.obtenerTema(this)
        AppTheme.aplicarTema(tema, this)
    }

    // Métodos para abrir diferentes fragmentos en la actividad, usando transacciones de fragmentos

    fun abrirPdfFragment(nombre: String) {
        val pdfFragment: PdfFragment = PdfFragment(nombre)  // Crea un nuevo fragmento PDF
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, PdfFragment(nombre))  // Reemplaza el fragmento actual por el nuevo
            .commitAllowingStateLoss()
    }

    fun abrirApuntesFragment() {
        val apuntesFragment: ApuntesFragment = ApuntesFragment()  // Crea un nuevo fragmento de apuntes
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, apuntesFragment)  // Reemplaza el fragmento actual por el nuevo
            .commitAllowingStateLoss()
    }

    fun abrirTerminosCondicionesFragment() {
        val terminosCondicionesFragment: TerminosCondicionesFragment = TerminosCondicionesFragment()  // Crea un fragmento para los términos y condiciones
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, terminosCondicionesFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    fun abrirHomeFragment() {
        val homeFragment: HomeFragment = HomeFragment()  // Crea un nuevo fragmento para la pantalla de inicio
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, homeFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    fun abrirPeriodoFragment() {
        val periodoFragment: PeriodoFragment = PeriodoFragment()  // Crea un fragmento para el periodo
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, periodoFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    fun abrirLogin() {
        val intent = Intent(this, LoginActivity::class.java)  // Crea una nueva intención para ir a la actividad de login
        startActivity(intent)  // Inicia la actividad de login
    }

    fun abrirPerfilDeUsuarioFragment() {
        val perfilDeUsuarioFragment: PerfilDeUsuarioFragment = PerfilDeUsuarioFragment()  // Crea un fragmento para el perfil de usuario
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, perfilDeUsuarioFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    fun abrirConfiguracionFragment() {
        val configuracionFragment: ConfiguracionFragment = ConfiguracionFragment()  // Crea un fragmento para la configuración
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, configuracionFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    fun abrirThemeFragment() {
        val themeFragment: ThemeFragment = ThemeFragment()  // Crea un fragmento para cambiar el tema
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, themeFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    fun abrirComentariosFragment() {
        val comentariosFragment: ComentariosFragment = ComentariosFragment()  // Crea un fragmento para los comentarios
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, comentariosFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    fun abrirNotiFragment() {
        val notiFragment: NotiFragment = NotiFragment()  // Crea un fragmento para las notificaciones
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, notiFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    // Método que solicita permisos de almacenamiento dependiendo de la versión de Android
    private fun permisoAlmacenamiento() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE  // Permiso para acceder a medios de audio
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE  // Permiso para acceder a almacenamiento externo
            )
        }
    }

    // Método para abrir el fragmento de búsqueda
    fun abrirBusquedaFragment() {
        val busquedaFragment: BusquedaFragment = BusquedaFragment()  // Crea un fragmento de búsqueda
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, busquedaFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }

    // Método para abrir el fragmento de código QR
    fun abrirQrcodeFragment() {
        val abrirQrcodeFragment: QrcodeFragment = QrcodeFragment()  // Crea un fragmento para mostrar código QR
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, abrirQrcodeFragment)  // Reemplaza el fragmento actual
            .commitAllowingStateLoss()
    }
}

// Códigos de solicitud para permisos de almacenamiento
const val READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE = 2001
const val READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE = 207
