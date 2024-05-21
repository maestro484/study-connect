package com.iegm.studyconnect

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
    private val STORAGE_PERMISSION_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
















        // Verificar si se tienen los permisos necesarios
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Si no se tienen los permisos, solicitarlos al usuario
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    STORAGE_PERMISSION_CODE
                )
            } else {
                // Los permisos ya están concedidos
                // Puedes realizar las operaciones de almacenamiento aquí
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes proceder con las operaciones de almacenamiento
            } else {
                // Permiso denegado, muestra un mensaje o toma alguna acción
                Toast.makeText(
                    this,
                    "Permiso de almacenamiento denegado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

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

            val homeFragment: HomeFragment = HomeFragment()
            supportFragmentManager.beginTransaction().add(R.id.root_layout, homeFragment)
                .commitAllowingStateLoss()
        }

        fun abrirPeriodoFragment() {
            val periodoFragment: PeriodoFragment = PeriodoFragment()
            supportFragmentManager.beginTransaction().add(R.id.root_layout, periodoFragment)
                .commitAllowingStateLoss()
        }
fun abrirApunteFragment() {
    val apunteFragment: ApunteFragment = ApunteFragment()
    supportFragmentManager.beginTransaction().add(R.id.root_layout, apunteFragment)
        .commitAllowingStateLoss()
}

