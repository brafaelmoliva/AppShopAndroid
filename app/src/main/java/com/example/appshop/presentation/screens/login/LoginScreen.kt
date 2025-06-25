package com.example.appshop.presentation.screens.login

import LoginViewModel
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.appshop.presentation.navigation.Routes

@Composable
fun LoginScreen(navController: NavController, context: Context) {
    val viewModel = remember { LoginViewModel(context) }

    LoginContent(
        viewModel = viewModel,
        onLoginSuccess = {
            navController.navigate(Routes.Home) {
                popUpTo(Routes.Login) { inclusive = true }
            }
        },
        onRegisterClick = {
            navController.navigate(Routes.Register)
        }
    )
}
