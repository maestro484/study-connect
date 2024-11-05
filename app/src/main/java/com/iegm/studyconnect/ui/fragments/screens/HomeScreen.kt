package com.iegm.studyconnect.ui.fragments.screens

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.iegm.studyconnect.MainActivity

@Composable
fun HomeScreen(){
    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)

}
