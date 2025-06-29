package com.example.appshop.presentation.screens.ventas

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appshop.dao.ventas.Venta
import com.example.appshop.presentation.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SucursalesFamosasScreen(navController: NavController) {
    val context = LocalContext.current


    // Se usa el ViewModel que maneja la lógica de Room

    val viewModel: SucursalesFamosasViewModel = viewModel(
        factory = SucursalesFamosasViewModel.Factory(context.applicationContext as Application)
    )

    val ventas by viewModel.ventas.collectAsState(initial = emptyList())

    var ciudad by remember { mutableStateOf("") }
    var totalVentas by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sucursales Famosas") })
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = ciudad,
                onValueChange = { ciudad = it },
                label = { Text("Ciudad") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = totalVentas,
                onValueChange = { totalVentas = it },
                label = { Text("Total Ventas") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (ciudad.isNotBlank() && totalVentas.isNotBlank()) {
                        // Llamo al ViewModel para usar el metodo insertar para insertar en la base de datos Room

                        viewModel.insertarVenta(
                            Venta(ciudad = ciudad, totalVentas = totalVentas.toIntOrNull() ?: 0)
                        )
                        ciudad = ""
                        totalVentas = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Registrar Venta")
            }

            Spacer(modifier = Modifier.height(16.dp))


            //En el lazy column muestros los datos obtenidos por el viewmodel
            LazyColumn {
                items(ventas) { venta ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Ciudad: ${venta.ciudad}")
                            Text("Total Ventas: ${venta.totalVentas}")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.VentasChart)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Ver gráfica de ventas")
            }
        }
    }
}

