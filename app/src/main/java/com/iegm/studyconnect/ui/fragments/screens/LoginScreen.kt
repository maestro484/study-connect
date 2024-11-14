// Importaciones necesarias para trabajar con Composable y otras funcionalidades.
package com.iegm.studyconnect.ui.fragments.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iegm.studyconnect.AuthViewModel
import com.iegm.studyconnect.R
import com.iegm.studyconnect.ui.NavigationItem
import com.iegm.studyconnect.ui.theme.StudyConnectTheme
import androidx.compose.ui.platform.LocalContext

// Constante para el código de solicitud de inicio de sesión de Google
const val RC_SIGN_IN = 9001

// Composable que representa la pantalla de inicio de sesión
@Composable
fun LoginScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {

    val context = LocalContext.current as Activity  // Obtener el contexto de la actividad

    // Observar el resultado de la autenticación con Google desde el ViewModel
    val googleSignInResult by authViewModel.googleSignInResult.observeAsState()

    // Manejar el resultado del inicio de sesión con Google
    googleSignInResult?.let { result ->
        result.fold(
            onSuccess = { account ->
                // Si el inicio de sesión es exitoso, autenticar al usuario con Google
                authViewModel.authWithGoogle(account)
            },
            onFailure = { e ->
                // Si falla, mostrar un mensaje de error
                Toast.makeText(context, "Google sign in failed: $e", Toast.LENGTH_SHORT).show()
            }
        )
    }

    // Contenedor principal con una columna de elementos centrados
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Imagen de logo en la parte superior
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.logou_foreground),
            contentDescription = "Imagen del login"
        )

        // Espaciado entre elementos
        Spacer(modifier = Modifier.height(20.dp))

        // Título principal en la pantalla
        Text(text = "¡HOLA!", fontSize = 35.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        // Espaciado entre el título y el siguiente texto
        Spacer(modifier = Modifier.height(20.dp))

        // Texto introductorio de bienvenida
        Text(
            modifier = Modifier.padding(horizontal = 50.dp),
            textAlign = TextAlign.Center,
            text = "¡BIENVENIDO A STUDY CONNECT, DISFRUTA Y ORGANIZA TUS APUNTES!",
            color = Color.Black
        )

        // Espaciado adicional
        Spacer(modifier = Modifier.height(20.dp))

        // Botón para iniciar sesión que navega a la pantalla de inicio de sesión
        Button(modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = { navHostController.navigate(NavigationItem.SignIn.route) }) {
            Text(text = "Iniciar sesión")
        }

        // Botón para registrarse que navega a la pantalla de registro
        Button(
            modifier = Modifier.width(250.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(),
            onClick = { navHostController.navigate(NavigationItem.SignUp.route) }) {
            Text(text = "Registrarse", color = Color.Black)
        }

        // Botón para iniciar sesión con Google
        ElevatedButton(onClick = {
            // Llamada al ViewModel para manejar el inicio de sesión con Google
            authViewModel.signInWithGoogle(context, RC_SIGN_IN)
        }, modifier = Modifier.width(250.dp)) {
            // Imagen del logo de Google
            Image(
                painter = painterResource(id = com.google.android.gms.base.R.drawable.googleg_standard_color_18),
                contentDescription = "Google Icon"
            )
            Spacer(modifier = Modifier.width(10.dp)) // Espacio entre la imagen y el texto
            // Texto del botón
            Text(text = "Continua con Google", color = Color.Black)
        }
    }
}

// Función de previsualización de la pantalla de login
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    StudyConnectTheme {
        // Se pasa un controlador de navegación y un ViewModel a la pantalla de inicio de sesión
        LoginScreen(rememberNavController(), authViewModel = AuthViewModel())
    }
}
