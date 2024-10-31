package com.iegm.studyconnect

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

class LoginActivity : ComponentActivity() {

    val authViewModel: AuthViewModel by viewModels()

    lateinit var googleSignInClient: GoogleSignInClient

    private val RC_SIGN_IN = 86
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(this, gso)
        authViewModel.initGoogleSignInClient(googleSignInClient)

        enableEdgeToEdge()

        val sharedPref = getSharedPreferences(
            "Study Connect", Context.MODE_PRIVATE
        )
        setContent {
            StudyConnectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val authState by authViewModel.authState.collectAsState()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            authViewModel.handleGoogleSignInResult(data)
        }
    }
}

