package com.iegm.studyconnect.model

data class Resultados(val resultado: String, val tipo: Tipo, val periodo: Int = 0)

enum class Tipo {
    MATERIA,
    APUNTE,
    FECHA,
    PROFESOR
}
