package com.iegm.studyconnect.ui.fragments.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iegm.studyconnect.R
import com.iegm.studyconnect.ui.NavigationItem
import com.iegm.studyconnect.ui.theme.StudyConnectTheme

@Composable
fun LoginScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Imagen del login"
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "¡HOLA!", fontSize = 35.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.padding(horizontal = 50.dp),
            textAlign = TextAlign.Center,
            text = "¡HOLA! Descubre un mundo de posibilidades en Playground.Explora, conecta y disfruta. ¡Registrate ya!"
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(modifier = Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = { navHostController.navigate(NavigationItem.SignIn.route) }) {
            Text(text = "Iniciar sesión")
        }

        Button(
            modifier = Modifier.width(250.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(),
            onClick = {navHostController.navigate(NavigationItem.SignUp.route) }) {
            Text(text = "Registrarse", color = Color.Black)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    StudyConnectTheme {
        LoginScreen(rememberNavController())
    }
}