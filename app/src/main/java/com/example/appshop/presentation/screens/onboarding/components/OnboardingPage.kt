package com.example.appshop.presentation.screens.onboarding.components

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class OnboardingPage(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String,
    val backgroundColor: Color = Color.White
)
