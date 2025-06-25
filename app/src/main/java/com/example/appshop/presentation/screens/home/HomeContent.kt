package com.example.appshop.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appshop.R
import com.example.appshop.presentation.navigation.Routes
import com.example.appshop.presentation.screens.home.components.*
import com.example.appshop.presentation.theme.ui.GreenButton

@Composable
fun HomeContent(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    val products by viewModel.products.collectAsState()
    val selectedIndex = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(
                selectedIndex = selectedIndex.value,
                onItemSelected = { selected ->
                    selectedIndex.value = selected
                    when (selected) {
                        0 -> navController.navigate(Routes.Home) { launchSingleTop = true }
                        1 -> { /* Favorites */ }
                        2 -> { /* List */ }
                        3 -> navController.navigate(Routes.Search) { launchSingleTop = true }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* acción */ },
                containerColor = GreenButton,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape,
                modifier = Modifier.offset(y = 20.dp) // 👈 Eleva el FAB desde la parte inferior
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,

    )
    { paddingValues ->

    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Abrir menú */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = { /* Ver notificaciones */ }) {
                    Icon(Icons.Filled.NotificationsNone, contentDescription = "Notifications")
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Your Pocket-Friendly",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = GreenButton
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = " Grocery Shopping", fontSize = 30.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Search") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .clickable {
                            navController.navigate(Routes.Search)
                        },
                    shape = RoundedCornerShape(12.dp),
                    enabled = false
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { /* Filtrar */ }) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filtrar")
                    }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CategoryFilterChip("All")
                CategoryFilterChip("Vegetable", R.drawable.img_1)
                CategoryFilterChip("Fruit Item", R.drawable.img_2)
                CategoryFilterChip("profe20", R.drawable.img_2)
            }

            Spacer(modifier = Modifier.height(30.dp))

        DiscountCarousel(navController = navController)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Beast Offer", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(
                    "View All",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(products) { product ->
                    BeastOfferItem(product)
                }
            }
        }
    }
}

@Composable
fun CustomBottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        Icons.Filled.Home to "Home",               // Ícono clásico y sólido de Home
        Icons.Filled.FavoriteBorder to "Favorites",// Favoritos con borde, más moderno
        Icons.Filled.FilterList to "List",          // Más relacionado a “lista” o filtrado
        Icons.Filled.PersonOutline to "Profile"     // Perfil con contorno, elegante y claro
    )


    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp,
        modifier = Modifier.height(70.dp) // 👈 Aumenta altura para más espacio visual
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.take(2).forEachIndexed { index, pair ->
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { onItemSelected(index) },
                    icon = { Icon(pair.first, contentDescription = pair.second) },
                    label = { Text(pair.second, fontSize = 10.sp) }
                )
            }

            Spacer(Modifier.width(60.dp)) // 👈 Espacio simulado para el FAB

            items.drop(2).forEachIndexed { index, pair ->
                NavigationBarItem(
                    selected = selectedIndex == index + 2,
                    onClick = { onItemSelected(index + 2) },
                    icon = { Icon(pair.first, contentDescription = pair.second) },
                    label = { Text(pair.second, fontSize = 10.sp) }
                )
            }
        }
    }

}
