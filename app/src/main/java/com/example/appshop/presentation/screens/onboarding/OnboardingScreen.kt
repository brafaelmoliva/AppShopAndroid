package com.example.appshop.presentation.screens.onboarding


import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun OnboardingScreen(navController: NavController) {
    OnboardingContent(
        onNextClick = {
            navController.navigate("login") {
                popUpTo("onboarding") { inclusive = true }
            }
        }
    )
}
