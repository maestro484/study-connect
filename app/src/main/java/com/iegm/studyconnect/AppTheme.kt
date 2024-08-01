package com.iegm.studyconnect

/**
 * Created by Carlos Jiménez on 31-Jul-24.
 */
object AppTheme {
    val moradoOscuro = "#550363"
    val moradoClaro = "#683781"
    val azul = "#7A8EFA"
    val moradoOscuro2 = "#A766C9"
    val gris = "#181819"

    var temaElegido = "#683781"

    fun aplicarTema(color: String){
        temaElegido = color
    }
}