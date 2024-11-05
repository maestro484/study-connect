package com.iegm.studyconnect.ui

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iegm.studyconnect.AuthViewModel
import com.iegm.studyconnect.ui.fragments.screens.HomeScreen
import com.iegm.studyconnect.ui.fragments.screens.LoginScreen
import com.iegm.studyconnect.ui.fragments.screens.SignInScreen
import com.iegm.studyconnect.ui.fragments.screens.SignUpScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String,
    authViewModel: AuthViewModel,
    sharedPreferences: SharedPreferences
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.Login.route) {
            LoginScreen(navHostController, authViewModel)
        }
        composable(NavigationItem.SignIn.route) {
            SignInScreen(navHostController, authViewModel)
        }
        composable(NavigationItem.SignUp.route) {
            SignUpScreen(navHostController, authViewModel, sharedPreferences)
        }

        composable(NavigationItem.Home.route) {
            HomeScreen()
        }

    }
    //
}
