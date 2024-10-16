package com.iegm.studyconnect.ui.fragments

// Interfaz que define un callback para manejar la selección de avatares
interface OnAvatarSelected {
    // Método que se llama cuando un avatar es seleccionado, pasando el ID del avatar
    fun onAvatarClick(avatar: Int)
}