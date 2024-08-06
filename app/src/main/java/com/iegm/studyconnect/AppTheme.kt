package com.iegm.studyconnect

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

    var temaElegido = "#683781"

    fun aplicarTema(color: String){
        temaElegido = color
    }
}