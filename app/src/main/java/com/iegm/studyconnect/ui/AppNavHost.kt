package com.iegm.studyconnect.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iegm.studyconnect.ui.fragments.screens.LoginScreen
import com.iegm.studyconnect.ui.fragments.screens.SignInScreen
import com.iegm.studyconnect.ui.fragments.screens.SignUpScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.Login.route) {
            LoginScreen(navHostController)
        }
        composable(NavigationItem.SignIn.route) {
            SignInScreen()
        }
        composable(NavigationItem.SignUp.route) {
            SignUpScreen()
        }
    }
}