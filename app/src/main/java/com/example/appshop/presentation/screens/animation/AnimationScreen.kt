package com.example.appshop.presentation.screens.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.appshop.presentation.navigation.Routes
import kotlinx.coroutines.delay
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.appshop.R

@Composable
fun AnimationScreen(navController: NavController) {
    val offsetY = remember { Animatable(300f) } // empieza 300px más abajo

    LaunchedEffect(true) {
        // Primero, animación del logo
        offsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000)
        )

        // Luego espera un poco y navega
        delay(2000)
        navController.navigate(Routes.Onboarding) {
            popUpTo(Routes.Animation) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2FAA7A)), // 👈 fondo verde aquí
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .offset(y = offsetY.value.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.applogo), // tu logo aquí
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Bienvenido a AppShop",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White // opcional: texto blanco sobre fondo verde
            )
        }
    }
}
