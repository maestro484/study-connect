// Paquete principal de la interfaz de usuario de la aplicación
package com.iegm.studyconnect.ui

// Importaciones necesarias para el funcionamiento de la navegación y las pantallas de la aplicación
import SignUpScreen
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iegm.studyconnect.AuthViewModel
import com.iegm.studyconnect.ui.fragments.screens.HomeScreen
import com.iegm.studyconnect.ui.fragments.screens.LoginScreen
import com.iegm.studyconnect.ui.fragments.screens.SignInScreen

// Función principal que configura el sistema de navegación de la aplicación
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,  // Modificador de UI para aplicar propiedades visuales opcionales
    navHostController: NavHostController,  // Controlador de navegación para gestionar el flujo de pantallas
    startDestination: String,  // Pantalla inicial a mostrar cuando se carga la app
    authViewModel: AuthViewModel,  // ViewModel para gestionar la lógica de autenticación
    sharedPreferences: SharedPreferences  // Almacenamiento de preferencias compartidas para guardar datos localmente
) {
    // Configuración del sistema de navegación que contiene las rutas de las pantallas
    NavHost(
        modifier = modifier,  // Modificador visual para personalizar el NavHost
        navController = navHostController,  // Controlador de navegación para el NavHost
        startDestination = startDestination  // Pantalla inicial configurada para la navegación
    ) {

        // Definición de la ruta y comportamiento de la pantalla de inicio de sesión
        composable(NavigationItem.Login.route) {
            LoginScreen(navHostController, authViewModel)  // Llama a la pantalla de inicio de sesión con el controlador de navegación y ViewModel de autenticación
        }

        // Definición de la ruta y comportamiento de la pantalla de registro de usuarios
        composable(NavigationItem.SignIn.route) {
            SignInScreen(navHostController, authViewModel)  // Llama a la pantalla de registro con el controlador de navegación y ViewModel de autenticación
        }

        // Definición de la ruta y comportamiento de la pantalla de registro adicional (Sign Up)
        composable(NavigationItem.SignUp.route) {
            SignUpScreen(navHostController, authViewModel, sharedPreferences)  // Llama a la pantalla de registro con el controlador de navegación, ViewModel y preferencias compartidas
        }

        // Definición de la ruta y comportamiento de la pantalla de inicio de la app
        composable(NavigationItem.Home.route) {
            HomeScreen()  // Llama a la pantalla de inicio de la aplicación
        }

    }
    //
}
