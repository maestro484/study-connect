package com.iegm.studyconnect

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iegm.studyconnect.ui.fragments.AdmFragment
import com.iegm.studyconnect.ui.fragments.PeriodoFragment
import com.iegm.studyconnect.ui.fragments.apuntesFragment

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


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        permisoAlmacenamiento()


        fun abrirAdmFragment() {
            val admFragment: AdmFragment = AdmFragment()
            supportFragmentManager.beginTransaction().add(R.id.root_layout, admFragment)
                .commitAllowingStateLoss()
        }

        fun abrirApuntesFragment() {

            val apuntesFragment: apuntesFragment = apuntesFragment()
            supportFragmentManager.beginTransaction().add(R.id.root_layout, apuntesFragment)
                .commitAllowingStateLoss()

        }

        fun abrirHomeFragment() {

           /* val homeFragment: HomeFragment = HomeFragment()
            supportFragmentManager.beginTransaction().add(R.id.root_layout, homeFragment)
                .commitAllowingStateLoss()*/
        }

        fun abrirPeriodoFragment() {
            val periodoFragment: PeriodoFragment = PeriodoFragment()
            supportFragmentManager.beginTransaction().add(R.id.root_layout, periodoFragment)
                .commitAllowingStateLoss()

        }

    }
    /*fun abrirAvatarsFragment() {
        val avatarsFragment: AvatarsFragment = AvatarsFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, avatarsFragment)
            .commitAllowingStateLoss()

    } */
    fun abrirApunteFragment() {
        /*val apunteFragment: ApunteFragment = ApunteFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, apunteFragment)
            .commitAllowingStateLoss()*/

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