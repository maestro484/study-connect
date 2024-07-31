package com.iegm.studyconnect

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.iegm.studyconnect.ui.fragments.AdmFragment
import com.iegm.studyconnect.ui.fragments.ApunteFragment

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
  /*  fun abrirAdmFragment() {
        val admFragment: AdmFragment = AdmFragment()
        supportFragmentManager.beginTransaction().add(R.id.root_layout, admFragment)
            .commitAllowingStateLoss()
    }*/

  /*  fun abrirApuntesFragment() {
        val apunteFragment: ApunteFragment = ApunteFragment()
        supportFragmentManager.begimTransaction().add(R.id.root_layout, apunteFragment)
            .commitAllowingStateLoss()
    }*/

}