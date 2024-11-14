// Paquete principal de la aplicación, donde se gestionan las funciones de autenticación
package com.iegm.studyconnect

// Importaciones necesarias para manejar autenticación, Google Sign-In y LiveData
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel para gestionar la lógica de autenticación de la aplicación
class AuthViewModel : ViewModel() {

    val firebaseAuth = FirebaseAuth.getInstance()  // Instancia de FirebaseAuth para autenticación de usuarios
    private val _authState = MutableStateFlow(firebaseAuth.currentUser)
    // Estado de autenticación que informa si el inicio de sesión es exitoso o no

    val authState: StateFlow<FirebaseUser?> = _authState  // Flujo de estado de autenticación pública

    private val _errorMessage = MutableLiveData<String>()  // Mensajes de error para mostrar al usuario
    val errorMessage: LiveData<String> get() = _errorMessage  // LiveData público de mensajes de error

    // Crea un nuevo usuario en Firebase con email y contraseña
    fun createUser(email: String, password: String) {
        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = firebaseAuth.currentUser  // Usuario creado correctamente
                    } else {
                        _authState.value = null  // Error en la creación del usuario
                    }
                }
        }
    }
    // Método para registrar usuario con verificación de correo y contraseña

    // Inicia sesión con email y contraseña
    fun SignIn(email: String, password: String) {
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = firebaseAuth.currentUser  // Inicio de sesión exitoso
                    } else {
                        _authState.value = null  // Error en el inicio de sesión
                    }
                }
        }
    }

    private val _googleSignInResult = MutableLiveData<Result<GoogleSignInAccount>>()  // Resultado del inicio con Google
    val googleSignInResult: LiveData<Result<GoogleSignInAccount>> = _googleSignInResult  // LiveData público del resultado

    private lateinit var googleSignInClient: GoogleSignInClient  // Cliente de Google Sign-In

    // Inicializa el cliente de Google Sign-In
    fun initGoogleSignInClient(client: GoogleSignInClient) {
        googleSignInClient = client
    }

    // Inicia el flujo de inicio de sesión con Google
    fun signInWithGoogle(activity: Activity, requestCode: Int) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(activity, gso)
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, requestCode)
    }

    // Maneja el resultado del inicio de sesión con Google
    fun handleGoogleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            _googleSignInResult.value = Result.success(account)  // Inicio de sesión con Google exitoso
        } catch (e: ApiException) {
            val errorMessage = when (e.statusCode) {
                CommonStatusCodes.CANCELED -> "El inicio de sesión fue cancelado. Inténtelo de nuevo."
                CommonStatusCodes.ERROR -> "Error al iniciar sesión. Verifique su conexión e inténtelo nuevamente."
                CommonStatusCodes.NETWORK_ERROR -> "Se produjo un error de red. Verifique su conexión a Internet."
                CommonStatusCodes.DEVELOPER_ERROR -> "Error del desarrollador. Verifique la configuración de su aplicación."
                else -> "Se produjo un error desconocido. Por favor inténtalo de nuevo."
            }
            // Actualiza el mensaje de error y el estado del resultado
            _errorMessage.value = errorMessage
            _googleSignInResult.value = Result.failure(Exception(errorMessage))
        }
    }

    // Autentica al usuario con Google en Firebase
    fun authWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = firebaseAuth.currentUser  // Usuario autenticado exitosamente
                } else {
                    _authState.value = null  // Error en la autenticación
                }
            }
    }

    // Cierra la sesión del usuario actual y lo establece como nulo
    fun signOut() {
        firebaseAuth.signOut()
        _authState.value = null
    }
}
