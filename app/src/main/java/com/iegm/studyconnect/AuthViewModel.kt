package com.iegm.studyconnect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    val firebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow(firebaseAuth.currentUser)
    //Para informarnos si el inicio fue exitoso o no, registra el estado de autenticacion

    val authState: StateFlow<FirebaseUser?> = _authState


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
    fun signOut(){
        firebaseAuth.signOut()
        _authState.value = null
    }
    //Cerrar sesión y volver un usuario nulo.




}