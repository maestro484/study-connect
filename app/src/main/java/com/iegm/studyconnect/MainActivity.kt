package com.iegm.studyconnect

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iegm.studyconnect.ui.fragments.AdmFragment
import com.iegm.studyconnect.ui.fragments.NotiFragment
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


    fun abrirAdmFragment() {
        val admFragment: AdmFragment = AdmFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, admFragment)
            .commitAllowingStateLoss()
    }

    fun abrirNotiFragment() {
        val notiFragment: NotiFragment = NotiFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, notiFragment)
            .commitAllowingStateLoss()

    }

    fun abrirThemeFragment() {
        val themeFragment: ThemeFragment = ThemeFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, themeFragment)
            .commitAllowingStateLoss()


    }

    fun abrirhomeFragment() {

    }
    /*
    * */
}