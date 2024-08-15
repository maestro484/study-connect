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
import com.iegm.studyconnect.ui.fragments.AvatarsFragment
import com.iegm.studyconnect.ui.fragments.PerfilDeUsuarioFragment
import com.iegm.studyconnect.ui.fragments.ApuntesFragment
import com.iegm.studyconnect.ui.fragments.BusquedaFragment
import com.iegm.studyconnect.ui.fragments.ComentariosFragment
import com.iegm.studyconnect.ui.fragments.MateriaFragment
import com.iegm.studyconnect.ui.fragments.PeriodoFragment

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
        supportFragmentManager.beginTransaction().add(R.id.root_layout, apuntesFragment)
            .commitAllowingStateLoss()

    }


    /*fun abrirHomeFragment() {

   val homeFragment: HomeFragment = HomeFragment()
       supportFragmentManager.beginTransaction().add(R.id.root_layout, homeFragment)
           .commitAllowingStateLoss()
} */

fun abrirPeriodoFragment() {
   val periodoFragment: PeriodoFragment = PeriodoFragment()
   supportFragmentManager.beginTransaction().add(R.id.root_layout, periodoFragment)
       .commitAllowingStateLoss()

}

    fun abrirAvatarsFragment() {
        val avatarsFragment: AvatarsFragment = AvatarsFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, avatarsFragment)
            .commitAllowingStateLoss()
    }


    fun abrirPerfilDeUsuarioFragment() {
        val perfilDeUsuarioFragment: PerfilDeUsuarioFragment = PerfilDeUsuarioFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, perfilDeUsuarioFragment)
            .commitAllowingStateLoss()


    }

    fun abriComentariosFragment(){
        val comentariosFragment: ComentariosFragment = ComentariosFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, comentariosFragment)
            .commitAllowingStateLoss()

    }

    fun abrirBusquedaFragment(){
        val busquedaFragment: BusquedaFragment = BusquedaFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, busquedaFragment)
            .commitAllowingStateLoss()
    }

    /* fun abrirApunteFragment() {
   val apunteFragment: ApunteFragment = ApunteFragment()
   supportFragmentManager.beginTransaction().add(R.id.root_layout, apunteFragment)
       .commitAllowingStateLoss() } */

    fun abrirMateriaFragment(){
        val materiaFragment: MateriaFragment = MateriaFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, materiaFragment)
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