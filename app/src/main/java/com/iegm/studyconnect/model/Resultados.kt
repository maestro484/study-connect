package com.iegm.studyconnect.model

// Definición de la clase de datos Resultados, que representa un resultado con tres propiedades.
data class Resultados(
    val resultado: String, // El resultado en formato de texto (por ejemplo, un nombre de materia o apunte)
    val tipo: Tipo, // El tipo de resultado (Materia, Apunte, Fecha o Profesor)
    val periodo: Int = 0 // El periodo al que pertenece el resultado (valor predeterminado es 0 si no se especifica)
)

// Enum que define los posibles tipos para el campo "tipo" de la clase Resultados.
enum class Tipo {
    MATERIA,  // Representa un tipo de resultado relacionado con una materia
    APUNTE,   // Representa un tipo de resultado relacionado con un apunte
    FECHA,    // Representa un tipo de resultado relacionado con una fecha
    PROFESOR  // Representa un tipo de resultado relacionado con un profesor
}

