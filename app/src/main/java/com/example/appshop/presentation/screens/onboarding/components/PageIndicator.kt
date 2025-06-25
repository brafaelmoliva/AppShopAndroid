package com.example.appshop.presentation.screens.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(size: Int, currentPage: Int) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        repeat(size) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (index == currentPage) 12.dp else 8.dp)
                    .background(
                        if (index == currentPage) Color(0xFF4CAF50) else Color.LightGray,
                        shape = CircleShape
                    )
            )
        }
    }
}
