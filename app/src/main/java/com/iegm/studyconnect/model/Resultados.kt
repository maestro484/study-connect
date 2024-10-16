package com.iegm.studyconnect.model

data class Resultados(val resultado: String, val tipo: Tipo)

enum class Tipo {
    MATERIA,
    APUNTE,
    FECHA,
    PROFESOR
}
