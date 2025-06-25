package com.example.appshop.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appshop.R
import com.example.appshop.presentation.navigation.Routes

@Composable
fun DiscountBanner(
    title: String,
    subtitle: String,
    imageResId: Int
) {
    Box(
        modifier = Modifier
            .width(300.dp) // ancho fijo para carrusel
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFB2EBF2))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Discount Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}

@Composable
fun DiscountCarousel(navController: NavController) {
    val banners = listOf(
        Triple("Discount Up To", "50% For The Combo Package", R.drawable.img_1),
        Triple("¡Encuentranos!", "Click aqui para ver nuestros locales", R.drawable.img_2),
        Triple("Sucursales famosas", "Limited Time Only", R.drawable.img_3)
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(banners) { index, (title, subtitle, imageResId) ->
            Box(modifier = Modifier.clickable {
                when (title) {
                    "¡Encuentranos!" -> navController.navigate(Routes.LocalList)
                    // Puedes agregar más aquí si quieres
                }
            }) {
                DiscountBanner(title = title, subtitle = subtitle, imageResId = imageResId)
            }
        }
    }
}
