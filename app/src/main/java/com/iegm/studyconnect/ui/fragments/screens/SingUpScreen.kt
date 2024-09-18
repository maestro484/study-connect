package com.iegm.studyconnect.ui.fragments.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.layout.getDefaultLazyLayoutKey
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
import com.iegm.studyconnect.R
import com.iegm.studyconnect.ui.theme.StudyConnectTheme

@Composable
fun SignUpScreen(navHostController: NavHostController, authViewModel: AuthViewModel, sharedPreferences: SharedPreferences?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Regístrate", fontSize = 35.sp, fontWeight = FontWeight.Bold)

        var nombre by remember { mutableStateOf("") }
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

        val representantes = listOf(
            "valen.3010066@gmail.com", "perenguesestiven@gmail.com", "juanmg777vg@gmail.com"
        )


        var email by remember { mutableStateOf("") }
        var esRepresentante by remember { mutableStateOf(false) }
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

        esRepresentante = representantes.contains(email)

        sharedPreferences?.edit()?.putBoolean("REPRESENTANTE", esRepresentante)?.apply()

        Spacer(modifier = Modifier.height(20.dp))

        var password by remember { mutableStateOf("") }
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

        var expanded2 by remember { mutableStateOf(false) }
        var selectGrado by remember { mutableStateOf("Seleccione su grado") }
        val optionGrado = listOf("8", "9", "10", "11")

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
                optionGrado.forEach { option ->
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        selectGrado = option
                        expanded2 = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(modifier = Modifier
            .width(250.dp)
            .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = { /*TODO*/ }) {
            Text(text = "Registrarse", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    StudyConnectTheme {
        SignUpScreen(rememberNavController(), AuthViewModel(), null)
    }
}
