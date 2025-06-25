package com.example.appshop.presentation.screens.register

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appshop.presentation.navigation.Routes

@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: RegisterViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return RegisterViewModel(context.applicationContext as Application) as T
            }
        }
    )

    RegisterContent(
        viewModel = viewModel,
        onRegisterClick = {
            viewModel.registerUser {
                navController.navigate(Routes.Login) {
                    popUpTo(Routes.Register) { inclusive = true }
                }
            }
        },
        onLoginClick = {
            navController.navigate(Routes.Login) {
                popUpTo(Routes.Register) { inclusive = true }
            }
        }
    )
}
