package com.iegm.studyconnect

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.iegm.studyconnect.ui.fragments.SAVED_THEME
import android.widget.Button

object AppTheme {
    val moradoOscuro = "#7D30BF" /*Barra*/
    val moradoOscuro2 = "#A799E0"
    val moradoClaro2 = "#C0A1DB"
    val moradoClaro = "#CB69DB" /*Barra*/
    val gris = "#494745" /*Fondo*/
    val azulClaro = "#B6BADB"
    val azul = "#4B65F2" /*Barra*/

    fun guardarTema(activity: Activity, color: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(SAVED_THEME, color)
            apply()
        }
        aplicarTema(color, (activity as MainActivity))
    }


    fun aplicarTema(tema: String, activity: MainActivity) {
        activity.apply {
            when (tema) {
                azul -> {
                    window.statusBarColor = Color.parseColor(azul)
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(azulClaro)))
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor(gris)))
                    window.navigationBarColor = Color.parseColor(azulClaro)
                }

                moradoOscuro -> {
                    window.statusBarColor = Color.parseColor(moradoOscuro)
                    supportActionBar?.setBackgroundDrawable(
                        ColorDrawable(
                            Color.parseColor(
                                moradoOscuro2
                            )
                        )
                    )
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor(gris)))
                    window.navigationBarColor = Color.parseColor(moradoOscuro2)
                }

                moradoClaro -> {
                    window.statusBarColor = Color.parseColor(moradoClaro)
                    supportActionBar?.setBackgroundDrawable(
                        ColorDrawable(
                            Color.parseColor(
                                moradoClaro2
                            )
                        )
                    )
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

    fun aplicarTemaAElementos(temaActual: String, listOf: List<Button>) {

    }
}
