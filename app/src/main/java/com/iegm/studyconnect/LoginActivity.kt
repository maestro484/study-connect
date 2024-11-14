// Paquete principal de la aplicación, donde se maneja la lógica de la pantalla de inicio de sesión
package com.iegm.studyconnect

// Importaciones necesarias para la actividad de inicio de sesión, Google Sign-In y navegación en Compose
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.iegm.studyconnect.ui.AppNavHost
import com.iegm.studyconnect.ui.NavigationItem
import com.iegm.studyconnect.ui.theme.StudyConnectTheme

// Actividad principal para gestionar el inicio de sesión de la aplicación
class LoginActivity : ComponentActivity() {

    val authViewModel: AuthViewModel by viewModels()  // Vista-modelo que gestiona la autenticación de usuarios

    lateinit var googleSignInClient: GoogleSignInClient  // Cliente de Google Sign-In

    private val RC_SIGN_IN = 9001  // Código de solicitud para Google Sign-In

    // Método onCreate que se ejecuta cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configura las opciones para Google Sign-In, incluyendo la solicitud de ID de token y correo electrónico
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Inicializa el cliente de Google Sign-In con las opciones definidas
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        authViewModel.initGoogleSignInClient(googleSignInClient)  // Pasa el cliente de Google Sign-In al ViewModel

        enableEdgeToEdge()  // Habilita la visualización a pantalla completa, eliminando los márgenes

        // Obtiene las preferencias compartidas para la configuración del usuario
        val sharedPref = getSharedPreferences(
            packageName, Context.MODE_PRIVATE
        )

        // Establece el contenido de la interfaz de usuario utilizando Jetpack Compose
        setContent {
            StudyConnectTheme {
                // Usamos Scaffold para construir la estructura de la pantalla
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Observa el estado de autenticación del usuario
                    val authState by authViewModel.authState.collectAsState()

                    // Configura el host de navegación y el destino inicial basado en el estado de autenticación
                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = rememberNavController(),
                        startDestination = if (authState == null) NavigationItem.Login.route else  NavigationItem.Home.route,
                        authViewModel = authViewModel,
                        sharedPref
                    )
                }
            }
        }
    }

    // Método que maneja el resultado de la actividad de inicio de sesión con Google
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            authViewModel.handleGoogleSignInResult(data)  // Procesa el resultado del inicio de sesión con Google
        }
    }
}
