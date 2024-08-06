package com.iegm.studyconnect

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.iegm.studyconnect.ui.fragments.SAVED_THEME

/**
 * Created by Carlos Jiménez on 31-Jul-24.
 */
object AppTheme {
    val moradoOscuro = "#550363" /*Barra*/
    val moradoOscuro2 = "#720685"
    val moradoClaro2 = "#683781"
    val moradoClaro = "#A766C9" /*Barra*/
    val gris = "#181819" /*Fondo*/
    val azulClaro = "#A0ACEC"
    val azul = "#8190DE" /*Barra*/

    fun guardarTema(activity: Activity, color: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(SAVED_THEME, color)
            apply()
        }
        aplicarTema(color, (activity as MainActivity))
    }

    fun aplicarTema(tema: String, activity: MainActivity){
        activity.apply {
            when(tema){
                azul ->{
                    window.statusBarColor = Color.parseColor(azul)
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(azulClaro)))
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor(gris)))
                    window.navigationBarColor = Color.parseColor(azulClaro)
                }
                moradoOscuro ->{
                    window.statusBarColor = Color.parseColor(moradoOscuro)
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(
                        moradoOscuro2)))
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor(gris)))
                    window.navigationBarColor = Color.parseColor(moradoOscuro2)
                }
                moradoClaro ->{
                    window.statusBarColor = Color.parseColor(moradoClaro)
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(
                        moradoClaro2)))
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor(gris)))
                    window.navigationBarColor = Color.parseColor(moradoClaro)
                }
            }
        }
    }

    fun obtenerTema(activity: Activity): String {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(SAVED_THEME, moradoClaro)!!
    }
}