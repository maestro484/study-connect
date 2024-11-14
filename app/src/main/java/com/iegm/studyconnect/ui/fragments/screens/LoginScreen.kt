package com.iegm.studyconnect.ui.fragments.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.iegm.studyconnect.AuthViewModel
import com.iegm.studyconnect.R
import com.iegm.studyconnect.ui.NavigationItem
import com.iegm.studyconnect.ui.theme.StudyConnectTheme
import androidx.compose.ui.platform.LocalContext

 // Asegúrate de que esta constante esté declarada aquí.
 const val RC_SIGN_IN = 9001
@Composable
fun LoginScreen(navHostController: NavHostController, authViewModel: AuthViewModel) {

    val context = LocalContext.current as Activity  // Obtener el contexto de la actividad

    val googleSignInResult by authViewModel.googleSignInResult.observeAsState()

    googleSignInResult?.let { result ->
        result.fold(
            onSuccess = { account ->
                authViewModel.authWithGoogle(account)
            },
            onFailure = { e ->
                Toast.makeText(context, "Google sign in failed: $e", Toast.LENGTH_SHORT).show()
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = R.drawable.logou_foreground),
            contentDescription = "Imagen del login"
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "¡HOLA!", fontSize = 35.sp, fontWeight = FontWeight.Bold, color = Color.Black)


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.padding(horizontal = 50.dp),
            textAlign = TextAlign.Center,
            text = "¡BIENVENIDO A STUDY CONNECT, DISFRUTA Y ORGANIZA TUS APUNTES!",
            color = Color.Black
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
            onClick = { navHostController.navigate(NavigationItem.SignUp.route) }) {
            Text(text = "Registrarse", color = Color.Black)
        }
        ElevatedButton(onClick = {
            authViewModel.signInWithGoogle(context, RC_SIGN_IN)
        }, modifier = Modifier.width(250.dp)) {
            Image(
                painter = painterResource(id = com.google.android.gms.base.R.drawable.googleg_standard_color_18),
                contentDescription = "Google Icon"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Continua con Google", color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    StudyConnectTheme {
        LoginScreen(rememberNavController(), authViewModel = AuthViewModel())
    }
}
