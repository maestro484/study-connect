package com.iegm.studyconnect

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.iegm.studyconnect.ui.fragments.SAVED_THEME
import android.widget.Button

// Objeto que gestiona los temas y colores de la aplicación
object AppTheme {

    // Definiciones de colores utilizados en la aplicación
    val moradoOscuro = "#7D30BF" /* Color de la barra de estado */
    val moradoOscuro2 = "#A799E0" /* Color secundario para el tema oscuro */
    val moradoClaro2 = "#C0A1DB" /* Color secundario para el tema claro */
    val moradoClaro = "#CB69DB" /* Color de la barra para el tema claro */
    val gris = "#494745" /* Color de fondo general */
    val azulClaro = "#B6BADB" /* Color claro para el tema azul */
    val azul = "#4B65F2" /* Color de la barra para el tema azul */

    // Guarda el tema seleccionado en las preferencias de la actividad
    fun guardarTema(activity: Activity, color: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return // Obtiene las preferencias
        with(sharedPref.edit()) {
            putString(SAVED_THEME, color) // Guarda el color del tema
            apply() // Aplica los cambios
        }
        aplicarTema(color, (activity as MainActivity)) // Aplica el tema a la actividad
    }

    // Aplica el tema especificado a la actividad
    fun aplicarTema(tema: String, activity: MainActivity) {
        activity.apply {
            when (tema) {
                azul -> {
                    // Configura los colores para el tema azul
                    window.statusBarColor = Color.parseColor(azul) // Color de la barra de estado
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(azulClaro))) // Color de la barra de acción
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor(gris))) // Color de fondo
                    window.navigationBarColor = Color.parseColor(azulClaro) // Color de la barra de navegación
                }

                moradoOscuro -> {
                    // Configura los colores para el tema oscuro
                    window.statusBarColor = Color.parseColor(moradoOscuro)
                    supportActionBar?.setBackgroundDrawable(
                        ColorDrawable(Color.parseColor(moradoOscuro2))
                    )
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor(gris)))
                    window.navigationBarColor = Color.parseColor(moradoOscuro2)
                }

                moradoClaro -> {
                    // Configura los colores para el tema claro
                    window.statusBarColor = Color.parseColor(moradoClaro)
                    supportActionBar?.setBackgroundDrawable(
                        ColorDrawable(Color.parseColor(moradoClaro2))
                    )
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor(gris)))
                    window.navigationBarColor = Color.parseColor(moradoClaro)
                }
            }
        }
    }

    // Obtiene el tema guardado en las preferencias de la actividad
    fun obtenerTema(activity: Activity): String {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(SAVED_THEME, moradoClaro)!! // Devuelve el tema guardado o el tema claro por defecto
    }

    // Método para aplicar el tema a elementos específicos (aún no implementado)
    fun aplicarTemaAElementos(temaActual: String, listOf: List<Button>) {
        // Implementación pendiente
    }
}
