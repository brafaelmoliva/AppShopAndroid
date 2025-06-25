package com.example.appshop.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.appshop.domain.model.Product

@Composable
fun BeastOfferItem(product: Product) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Imagen con etiqueta de descuento
        Box(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                model = product.image_url,
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize()
            )

            // Etiqueta de descuento
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(Color(0xFFE53935), shape = RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "50% OFF",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        // Nombre del producto
        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            maxLines = 1
        )

        // Precio
        Text(
            text = "$${product.price}",
            fontSize = 13.sp,
            color = Color(0xFF4CAF50),
            fontWeight = FontWeight.Medium
        )
    }
}

