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

@Composable
fun SignInScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    // Listener para habilitar el botón
    LaunchedEffect(email, password) {
        isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Imagen del inicio"
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "¡BIENVENIDO!", fontSize = 35.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Ingresa tu email.") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "Icono del correo.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(20.dp))

        var showPassword by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Contraseña") },
            placeholder = { Text(text = "Ingresa su contraseña.") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Icono de candado.") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (showPassword) "Icono mostrar contraseña" else "Icono ocultar contraseña"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "¿No tienes cuenta?", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Regístrate",
            fontSize = 15.sp,
            color = Purple40,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navHostController.navigate(NavigationItem.SignUp.route) }
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = { if (isButtonEnabled) authViewModel.SignIn(email, password) },
            enabled = isButtonEnabled
        ) {
            Text(text = "Iniciar sesión", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SigninScreenPreview() {
    StudyConnectTheme {
        SignInScreen(rememberNavController(), authViewModel = AuthViewModel())
    }
}