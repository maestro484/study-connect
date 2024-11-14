package com.iegm.studyconnect.ui.fragments.screens

import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.iegm.studyconnect.ui.NavigationItem
import com.iegm.studyconnect.ui.theme.Purple40
import com.iegm.studyconnect.ui.theme.StudyConnectTheme
@Composable
fun SignUpScreen(
    navHostController: NavHostController, // Controlador de navegación
    authViewModel: AuthViewModel,         // ViewModel para autenticación
    sharedPreferences: SharedPreferences? // SharedPreferences para almacenamiento de preferencias
) {

    // Variables que almacenan los datos ingresados
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectGrado by remember { mutableStateOf("Seleccione su grado") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    // Variable para guardar el índice del grado seleccionado
    var gradoSeleccionado by remember { mutableStateOf(0) }

    // Habilita el botón de registro si todos los campos están llenos
    LaunchedEffect(nombre, email, password, selectGrado) {
        isButtonEnabled =
            nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && selectGrado != "Seleccione su grado"
    }

    // Diseño principal de la pantalla de registro
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Regístrate", fontSize = 35.sp, fontWeight = FontWeight.Bold)

        // Campo de texto para el nombre
        OutlinedTextField(value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            placeholder = { Text(text = "Nombre") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person, contentDescription = "Icono de la persona"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Lista de correos para identificar representantes
        val representantes = listOf(
            "valen.3010066@gmail.com", "perenguesestiven@gmail.com", "juanmg777vg@gmail.com"
        )

        var esRepresentante by remember { mutableStateOf(false) }

        // Campo de texto para el correo electrónico
        OutlinedTextField(value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Ingresa tu email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email, contentDescription = "Icono del correo"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Comprueba si el correo ingresado es de un representante y guarda el resultado en SharedPreferences
        esRepresentante = representantes.contains(email)
        sharedPreferences?.edit()?.putBoolean("REPRESENTANTE", esRepresentante)?.apply()

        Spacer(modifier = Modifier.height(20.dp))

        // Campo de texto para la contraseña con opción de mostrar u ocultar
        var showPassword by remember { mutableStateOf(false) }
        OutlinedTextField(value = password,
            onValueChange = { password = it },
            label = { Text(text = "Contraseña") },
            placeholder = { Text(text = "Ingresa tu contraseña") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock, contentDescription = "Icono de candado"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (showPassword) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            })

        Spacer(modifier = Modifier.height(20.dp))

        // Menú desplegable para seleccionar el grado
        var expanded2 by remember { mutableStateOf(false) }
        val optionGrado = listOf("Grado 11", "Grado 10", "Grado 9", "Grado 8")

        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded2 = true }
            .padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = selectGrado, modifier = Modifier.padding(start = 30.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Ícono de selección",
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
            DropdownMenu(expanded = expanded2, onDismissRequest = { expanded2 = false }) {
                optionGrado.forEachIndexed { index, option ->
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        gradoSeleccionado = index
                        selectGrado = option
                        expanded2 = false
                    })
                }
            }
        }

        // Texto de enlace para iniciar sesión en caso de ya tener una cuenta
        Text(text = "¿No tienes cuenta?", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Inicia sesión",
            fontSize = 15.sp,
            color = Purple40,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navHostController.navigate(NavigationItem.SignIn.route) })

        Spacer(modifier = Modifier.height(20.dp))

        // Botón de registro
        Button(
            modifier = Modifier
                .width(250.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = {
                // Guarda el grado seleccionado y registra al usuario si el botón está habilitado
                sharedPreferences!!.edit().putInt("GRADO_USUARIO", gradoSeleccionado).apply()
                if (isButtonEnabled) authViewModel.createUser(email, password)
            },
            enabled = isButtonEnabled // Habilita o deshabilita el botón
        ) {
            Text(text = "Registrarse", color = Color.White)
        }
    }
}

// Vista previa de la pantalla de registro
@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    StudyConnectTheme {
        SignUpScreen(rememberNavController(), AuthViewModel(), null)
    }
}
