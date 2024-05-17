package com.iegm.studyconnect.model

import com.google.gson.annotations.SerializedName

data class Grado(val grado: Int = 0, val materias: ArrayList<Materia> = ArrayList())

data class Materia(
    val nombre: String = "",
    val periodos: ArrayList<Periodo> = ArrayList()
)

data class Periodo(
    @SerializedName("period_id") val periodId: Int = 0,
    val apuntes: ArrayList<Apunte> = ArrayList()
)


data class Apunte(
    @SerializedName("apunte_id") val apunteId: Int = 0,
    val descripcion: String = "",
    val archivos: ArrayList<Archivo> = ArrayList()
)

data class Archivo(
    @SerializedName("archivo_id") val archivoId: Int = 0,
    val descripcion: String = "",
    val ubicacion: String = ""
)