package com.iegm.studyconnect.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
//cambie loginViewModel por firebaseAuth
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false
    )
    //Impedir que se creen usuarios accidentales

    //Función para hacer Login
    fun signInWithEmailAndPassword(email: String, password:String,home: ()-> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                          Log.d("StudyConnect", "signInWithEmailAndPassword logueado!!")
                        home()
                    }else{
                        Log.d("StudyConnect", "signInWithEmailAndPassword: ${task.result.toString()}")

                    }
                }
        }
      catch (ex:Exception){
          Log.d("StudyConnect","signInWithEmailAndPassword: ${ex.message} ")

      }
    }

}
