package com.iegm.studyconnect

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
import com.iegm.studyconnect.ui.fragments.ApunteFragment
import com.iegm.studyconnect.ui.fragments.ApuntesFragment
import com.iegm.studyconnect.ui.fragments.BusquedaFragment
import com.iegm.studyconnect.ui.fragments.ComentariosFragment
import com.iegm.studyconnect.ui.fragments.ConfiguracionFragment
import com.iegm.studyconnect.ui.fragments.HomeFragment
import com.iegm.studyconnect.ui.fragments.MateriaFragment
import com.iegm.studyconnect.ui.fragments.NotiFragment
import com.iegm.studyconnect.ui.fragments.PerfilDeUsuarioFragment
import com.iegm.studyconnect.ui.fragments.PeriodoFragment
import com.iegm.studyconnect.ui.fragments.QrcodeFragment
import com.iegm.studyconnect.ui.fragments.TerminosCondicionesFragment
import com.iegm.studyconnect.ui.fragments.ThemeFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun cambiarColor(primaryDark: String, primary: String, background: String) {
        window.statusBarColor = Color.parseColor(primaryDark)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(primary)))
        window.setBackgroundDrawable(ColorDrawable(Color.parseColor(background)))
        window.navigationBarColor = Color.parseColor(primary)
    }


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        permisoAlmacenamiento()

        val tema = AppTheme.obtenerTema(this)
        AppTheme.aplicarTema(tema, this)
    }

    fun abrirApunteFragment() {
        val apunteFragment: ApunteFragment = ApunteFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, apunteFragment)
            .commitAllowingStateLoss()

    }

    fun abrirApuntesFragment() {
        val apuntesFragment: ApuntesFragment = ApuntesFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, apuntesFragment)
            .commitAllowingStateLoss()

    }

    fun abrirTerminosCondicionesFragment() {
        val terminosCondicionesFragment: TerminosCondicionesFragment = TerminosCondicionesFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, terminosCondicionesFragment)
            .commitAllowingStateLoss()
    }


    fun abrirHomeFragment() {
        val homeFragment: HomeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, homeFragment)
            .commitAllowingStateLoss()
    }

    fun abrirPeriodoFragment() {
        val periodoFragment: PeriodoFragment = PeriodoFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, periodoFragment)
            .commitAllowingStateLoss()

    }


    fun abrirPerfilDeUsuarioFragment() {
        val perfilDeUsuarioFragment: PerfilDeUsuarioFragment = PerfilDeUsuarioFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, perfilDeUsuarioFragment)
            .commitAllowingStateLoss()
    }

    fun abrirConfiguracionFragment() {
        val configuracionFragment: ConfiguracionFragment = ConfiguracionFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, configuracionFragment)
            .commitAllowingStateLoss()
    }

    fun abrirThemeFragment() {
        val themeFragment: ThemeFragment = ThemeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, themeFragment)
            .commitAllowingStateLoss()
    }

    fun abrirComentariosFragment() {
        val comentariosFragment: ComentariosFragment = ComentariosFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, comentariosFragment)
            .commitAllowingStateLoss()
    }

    fun abrirNotiFragment() {
        val notiFragment: NotiFragment = NotiFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, notiFragment)
            .commitAllowingStateLoss()
    }

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

    fun abrirBusquedaFragment() {
        val busquedaFragment: BusquedaFragment = BusquedaFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, busquedaFragment)
            .commitAllowingStateLoss()
    }

    fun abrirMateriaFragment() {
        val abrirMateriaFragment: MateriaFragment = MateriaFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, abrirMateriaFragment)
            .commitAllowingStateLoss()
    }

    fun abrirQrcodeFragment() {
        val abrirQrcodeFragment : QrcodeFragment = QrcodeFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, abrirQrcodeFragment)
            .commitAllowingStateLoss()
    }

}

const val READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE = 2001
const val READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE = 207
