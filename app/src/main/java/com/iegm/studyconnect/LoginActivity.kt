package com.iegm.studyconnect

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.iegm.studyconnect.ui.AppNavHost
import com.iegm.studyconnect.ui.NavigationItem
import com.iegm.studyconnect.ui.theme.StudyConnectTheme

class LoginActivity : ComponentActivity() {

    val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences(
            getString(R.string.app_name), Context.MODE_PRIVATE)

        enableEdgeToEdge()
        setContent {
            StudyConnectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = rememberNavController(),
                        startDestination = NavigationItem.Login.route,
                        authViewModel,
                        sharedPref
                    )
                }
            }
        }
    }
}