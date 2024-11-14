// Importaciones necesarias para el uso de Jetpack Compose y otras funciones
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

// Composable principal para la pantalla de registro
@Composable
fun SignUpScreen(
    navHostController: NavHostController, // Controlador de navegación
    authViewModel: AuthViewModel, // ViewModel de autenticación
    sharedPreferences: SharedPreferences? // Preferencias compartidas para almacenar datos
) {

    // Definición de los estados locales para cada campo de entrada
    var nombre by remember { mutableStateOf("") } // Estado para el nombre del usuario
    var email by remember { mutableStateOf("") } // Estado para el email del usuario
    var password by remember { mutableStateOf("") } // Estado para la contraseña del usuario
    var selectGrado by remember { mutableStateOf("Seleccione su grado") } // Estado para el grado seleccionado
    var isButtonEnabled by remember { mutableStateOf(false) } // Estado para habilitar o deshabilitar el botón de registro

    var gradoSeleccionado by remember { mutableStateOf(0) } // Estado para almacenar el índice del grado seleccionado

    // Lógica para habilitar el botón de registro solo cuando todos los campos estén completos
    LaunchedEffect(nombre, email, password, selectGrado) {
        isButtonEnabled =
            nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && selectGrado != "Seleccione su grado"
    }

    // Contenedor principal de la pantalla de registro
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título principal de la pantalla
        Text(text = "Regístrate", fontSize = 35.sp, fontWeight = FontWeight.Bold)

        // Campo de texto para el nombre del usuario
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

        // Espaciador para separar los elementos
        Spacer(modifier = Modifier.height(20.dp))

        // Lista de correos electrónicos de representantes para validación
        val representantes = listOf(
            "valen.3010066@gmail.com", "perenguesestiven@gmail.com", "juanmg777vg@gmail.com"
        )

        // Estado para verificar si el correo pertenece a un representante
        var esRepresentante by remember { mutableStateOf(false) }
        // Campo de texto para el correo electrónico del usuario
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

        // Verificar si el correo ingresado está en la lista de representantes
        esRepresentante = representantes.contains(email)
        sharedPreferences?.edit()?.putBoolean("REPRESENTANTE", esRepresentante)?.apply() // Guardar el estado de representante en SharedPreferences

        // Espaciador
        Spacer(modifier = Modifier.height(20.dp))

        // Estado para mostrar u ocultar la contraseña
        var showPassword by remember { mutableStateOf(false) }
        // Campo de texto para la contraseña
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
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(), // Lógica para mostrar/ocultar la contraseña
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (showPassword) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            })

        // Espaciador
        Spacer(modifier = Modifier.height(20.dp))

        // Estado para controlar la expansión del menú desplegable de grados
        var expanded2 by remember { mutableStateOf(false) }
        // Lista de grados disponibles para seleccionar
        val optionGrado = listOf("Grado 11", "Grado 10", "Grado 9", "Grado 8")

        // Menú desplegable para seleccionar el grado
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
            // Acción cuando se selecciona un grado
            DropdownMenu(expanded = expanded2, onDismissRequest = { expanded2 = false }) {
                optionGrado.forEachIndexed { index, option -> // Iterar sobre las opciones de grado
                    DropdownMenuItem(text = { Text(text = option) }, onClick = {
                        gradoSeleccionado = index // Guardar el índice del grado seleccionado
                        selectGrado = option // Actualizar el grado seleccionado
                        expanded2 = false
                    })
                }
            }
        }

        // Texto para navegar a la pantalla de inicio de sesión
        Text(text = "¿No tienes cuenta?", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Inicia sesión",
            fontSize = 15.sp,
            color = Purple40,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navHostController.navigate(NavigationItem.SignIn.route) })

        // Espaciador
        Spacer(modifier = Modifier.height(20.dp))

        // Botón de registro
        Button(
            modifier = Modifier
                .width(250.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = {
                sharedPreferences!!.edit().putInt("GRADO_USUARIO", gradoSeleccionado).apply() // Guardar el grado seleccionado en SharedPreferences
                if (isButtonEnabled) authViewModel.createUser(email, password) // Crear el usuario si el botón está habilitado
            },
            enabled = isButtonEnabled // Habilitar o deshabilitar el botón según la validación de los campos
        ) {
            Text(text = "Registrarse", color = Color.White)
        }
    }
}

// Función de previsualización para la pantalla de registro
@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    StudyConnectTheme {
        SignUpScreen(rememberNavController(), AuthViewModel(), sharedPreferences = null)
    }
}
