package com.iegm.studyconnect.ui.fragments.screens

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
import com.iegm.studyconnect.ui.theme.StudyConnectTheme

@Composable
fun SignUpScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Regístrate", fontSize = 35.sp, fontWeight = FontWeight.Bold)

        var nombre by remember { mutableStateOf("") }
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            placeholder = { Text(text = "Nombre") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "Icono de la persona") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Ingresa tu email") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "Icono del correo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(20.dp))

        var password by remember { mutableStateOf("") }
        var showPassword by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Contraseña") },
            placeholder = { Text(text = "Ingresa tu contraseña") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Icono de candado") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (showPassword) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        var expanded by remember { mutableStateOf(false) }
        var selectStatus by remember { mutableStateOf("Seleccione su status") }
        val optionStatus = listOf("Representante", "Estudiante")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween, // Align items with space between
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = selectStatus,
                    modifier = Modifier.padding(start = 30.dp)

                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Ícono de selección",
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                optionStatus.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectStatus = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .width(250.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Registrarse", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    StudyConnectTheme {
        SignUpScreen()
    }
}
