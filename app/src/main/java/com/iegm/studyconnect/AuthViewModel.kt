 package com.iegm.studyconnect

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
import kotlin.math.log


class AuthViewModel : ViewModel() {

    val firebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow(firebaseAuth.currentUser)
    //Para informarnos si el inicio fue exitoso o no, registra el estado de autenticacion

    val authState: StateFlow<FirebaseUser?> = _authState

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    fun createUser(email: String, password: String){
        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        _authState.value = firebaseAuth.currentUser
                    } else{
                        _authState.value = null
                    }
                }
        }
    }
    //Para verificar el correo y la contraseña. crear el user. AKI TENGO K HACER LO DEL NULO Y AUTENTICAR

    fun SignIn(email: String, password: String){
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        _authState.value = firebaseAuth.currentUser
                    }else{
                        _authState.value = null
                    }

                }
        }
    }


    private val _googleSignInResult = MutableLiveData<Result<GoogleSignInAccount>>()
    val googleSignInResult: LiveData<Result<GoogleSignInAccount>> = _googleSignInResult

    private lateinit var googleSignInClient: GoogleSignInClient

    fun initGoogleSignInClient(client: GoogleSignInClient) {
        googleSignInClient = client
    }

    fun signInWithGoogle(activity: Activity, requestCode: Int, ) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(activity, gso)
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, requestCode)
    }

    fun handleGoogleSignInResult(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            _googleSignInResult.value = Result.success(account)
        } catch (e: ApiException) {
            val errorMessage = when (e.statusCode) {

                CommonStatusCodes.CANCELED -> "El inicio de sesión fue cancelado. Inténtelo de nuevo."
                CommonStatusCodes.ERROR -> "Error al iniciar sesión. Verifique su conexión e inténtelo nuevamente."
                CommonStatusCodes.NETWORK_ERROR -> "Se produjo un error de red. Verifique su conexión a Internet."
                CommonStatusCodes.DEVELOPER_ERROR -> "Error del desarrollador. Verifique la configuración de su aplicación."
                else -> "Se produjo un error desconocido. Por favor inténtalo de nuevo."
            }
            // Mostrar el mensaje al usuario
            _errorMessage.value = errorMessage
            _googleSignInResult.value = Result.failure(Exception(errorMessage))


        }
    }

    fun authWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = firebaseAuth.currentUser
                } else {
                    _authState.value = null
                }
            }
    }
    fun signOut(){
        firebaseAuth.signOut()
        _authState.value = null
    }
    //Cerrar sesión y volver un usuario nulo.


}