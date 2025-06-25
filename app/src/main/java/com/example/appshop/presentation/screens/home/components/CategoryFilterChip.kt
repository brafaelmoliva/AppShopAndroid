package com.example.appshop.presentation.screens.home.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryFilterChip(label: String, iconRes: Int? = null) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFE0F7FA),
        modifier = Modifier
            .height(32.dp)
            .padding(end = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            iconRes?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = label,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(text = label, fontSize = 14.sp)
        }
    }
}
