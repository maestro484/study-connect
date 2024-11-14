package com.iegm.studyconnect.ui

// Enum que define las pantallas de la aplicación
enum class Screen {
    LOGIN,  // Pantalla de inicio de sesión
    SIGN_IN,  // Pantalla para iniciar sesión
    SIGN_UP  // Pantalla para registrarse
}

// Clase sellada que representa los elementos de navegación
sealed class NavigationItem(val route: String) {
    // Objeto para la pantalla de inicio de sesión (LOGIN)
    object Login : NavigationItem(Screen.LOGIN.name)

    // Objeto para la pantalla de registro (SIGN_UP)
    object SignUp : NavigationItem(Screen.SIGN_UP.name)

    // Objeto para la pantalla de inicio de sesión con Google o similar (SIGN_IN)
    object SignIn : NavigationItem(Screen.SIGN_IN.name)

    // Objeto para la pantalla de inicio (Home)
    object Home : NavigationItem("Home") // Cambia el nombre si es necesario
}
