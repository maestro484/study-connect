package com.iegm.studyconnect.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable

fun StudyConnectLogin(navController: NavController) {
//True = Login;  False = Create
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    Surface(modifier = Modifier
    ){

    }
}

