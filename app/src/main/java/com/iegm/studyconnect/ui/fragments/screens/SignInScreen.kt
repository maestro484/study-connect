package com.iegm.studyconnect.ui.fragments.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iegm.studyconnect.AuthViewModel
import com.iegm.studyconnect.R
import com.iegm.studyconnect.ui.NavigationItem
import com.iegm.studyconnect.ui.theme.Purple40
import com.iegm.studyconnect.ui.theme.StudyConnectTheme

// Composable para la pantalla de inicio de sesión
@Composable
fun SignInScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {
    // Estado para el email, contraseña y si el botón debe estar habilitado
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    // Listener para habilitar el botón de inicio de sesión cuando el email y la contraseña no estén vacíos
    LaunchedEffect(email, password) {
        isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()
    }

    // Columna que contiene todos los elementos en el centro de la pantalla
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Imagen de bienvenida
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Imagen del inicio"
        )
        Spacer(modifier = Modifier.height(20.dp)) // Espaciado entre elementos

        // Título de bienvenida
        Text(text = "¡BIENVENIDO!", fontSize = 35.sp, fontWeight = FontWeight.Bold)

        // Campo de texto para el email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Ingresa tu email.") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "Icono del correo.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(20.dp)) // Espaciado entre elementos

        // Variable para controlar si la contraseña se muestra u oculta
        var showPassword by remember { mutableStateOf(false) }
        // Campo de texto para la contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Contraseña") },
            placeholder = { Text(text = "Ingresa tu contraseña.") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Icono de candado.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation() // Ocultar la contraseña
            },
            trailingIcon = {
                // Icono para mostrar/ocultar la contraseña
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (showPassword) "Icono mostrar contraseña" else "Icono ocultar contraseña"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp)) // Espaciado entre elementos

        // Texto para redirigir al registro si no tiene cuenta
        Text(text = "¿No tienes cuenta?", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        // Enlace para navegar a la pantalla de registro
        Text(
            text = "Regístrate",
            fontSize = 15.sp,
            color = Purple40,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navHostController.navigate(NavigationItem.SignUp.route) }
        )
        Spacer(modifier = Modifier.height(20.dp)) // Espaciado entre elementos

        // Botón de inicio de sesión
        Button(
            modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = { if (isButtonEnabled) authViewModel.SignIn(email, password) },
            enabled = isButtonEnabled // Habilitar o deshabilitar el botón según el estado
        ) {
            Text(text = "Iniciar sesión", color = Color.White) // Texto del botón
        }
    }
}

// Vista previa de la pantalla de inicio de sesión
@Preview(showBackground = true)
@Composable
fun SigninScreenPreview() {
    StudyConnectTheme {
        SignInScreen(rememberNavController(), authViewModel = AuthViewModel())
    }
}
