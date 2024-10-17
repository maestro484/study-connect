package com.iegm.studyconnect.ui

enum class Screen{

    LOGIN,
    SIGN_IN,
    SIGN_UP

}

sealed class NavigationItem(val route: String){
    object Login : NavigationItem(Screen.LOGIN.name)
    object SignUp : NavigationItem(Screen.SIGN_UP.name)
    object SignIn : NavigationItem(Screen.SIGN_IN.name)
    sealed class NavigationItem(val route: String) {
        object Login : NavigationItem("login")
        object Home : NavigationItem("HomeFragment") // Asegúrate de que esto esté presente
    }
}