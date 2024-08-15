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
import androidx.navigation.compose.rememberNavController
import com.iegm.studyconnect.ui.fragments.AvatarsFragment
import com.iegm.studyconnect.ui.fragments.PerfilDeUsuarioFragment
import com.iegm.studyconnect.ui.fragments.ApuntesFragment
import com.iegm.studyconnect.ui.fragments.ConfiguracionFragment
import com.iegm.studyconnect.ui.fragments.NotiFragment
import com.iegm.studyconnect.ui.fragments.PeriodoFragment
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

    }

    fun abrirApuntesFragment() {
        val apuntesFragment: ApuntesFragment = ApuntesFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, apuntesFragment)
            .commitAllowingStateLoss()

    }

    fun abrirTerminosCondicionesFragment() {
        val terminosCondicionesFragment: TerminosCondicionesFragment = TerminosCondicionesFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, terminosCondicionesFragment)
            .commitAllowingStateLoss()
    }


    /*fun abrirHomeFragment() {
   val homeFragment: HomeFragment = HomeFragment()
       supportFragmentManager.beginTransaction().add(R.id.root_layout, homeFragment)
           .commitAllowingStateLoss()
} */

    fun abrirPeriodoFragment() {
        val periodoFragment: PeriodoFragment = PeriodoFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, periodoFragment)
            .commitAllowingStateLoss()

    }


    fun abrirPerfilDeUsuarioFragment() {
        val perfilDeUsuarioFragment: PerfilDeUsuarioFragment = PerfilDeUsuarioFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, perfilDeUsuarioFragment)
            .commitAllowingStateLoss()
    }

    fun abrirConfiguracionFragment() {
        val configuracionFragment: ConfiguracionFragment = ConfiguracionFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, configuracionFragment)
            .commitAllowingStateLoss()
    }

    fun abrirThemeFragment() {
        val themeFragment: ThemeFragment = ThemeFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, themeFragment)
            .commitAllowingStateLoss()
    }



    fun abrirNotiFragment() {
        val notiFragment: NotiFragment = NotiFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, notiFragment)
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

}

const val READ_MEDIA_AUDIO_PERMISSION_REQUEST_CODE = 2001
const val READ_EXTERNAL_STORAGE_IMAGES_PERMISSION_REQUEST_CODE = 207
