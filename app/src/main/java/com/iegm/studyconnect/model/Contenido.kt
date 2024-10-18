package com.iegm.studyconnect.model

import com.google.gson.annotations.SerializedName


data class SchoolData(
    val grados: List<Grado>
)
data class Grado(val grado: Int = 0, val materias: ArrayList<Materia> = ArrayList())

data class Materia(
    val nombre: String = "",
    val profesor: String = "",
    val periodos: ArrayList<Periodo> = ArrayList(),
    val comentarios: ArrayList<Comentario> = ArrayList()
)

data class Comentario(
    val usuario: String = "",
    val avatar: Int = 0,
    val descripcion: String = ""
)


data class Periodo(
    @SerializedName("period_id") val periodId: Int = 0,
    val apuntes: ArrayList<Apunte> = ArrayList()
)

data class Apunte(

    @SerializedName("apunte_id") val apunteId: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val mes : String = "",
    val archivos: ArrayList<Archivo> = ArrayList()
)

data class Archivo(
    @SerializedName("archivo_id") val archivoId: Int = 0,
    val descripcion: String = "",
    val ubicacion: String = ""
)

