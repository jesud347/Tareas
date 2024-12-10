package com.example.tareas

data class Tarea(
    val id: Int,
    val descripcion: String,
    var prioridad: prioridad,
    var completada: Boolean
    )

enum class prioridad {
    BAJA, MEDIA, ALTA
}