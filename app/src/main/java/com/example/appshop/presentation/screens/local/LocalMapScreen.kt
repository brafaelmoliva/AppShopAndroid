package com.example.appshop.presentation.screens.local

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appshop.presentation.navigation.Routes
import com.example.appshop.presentation.screens.local.LocalesViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.ui.graphics.Color

@Composable
fun LocalMapScreen(navController: NavController, localId: Int?) {
  //uso el view model para obtener los datos de la api
   val viewModel: LocalesViewModel = viewModel()
    val local = viewModel.locales.collectAsState().value.find { it.id == localId }

    local?.let { item ->
        val cameraPositionState = rememberCameraPositionState {
            position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
               //aqui accedo a los datos
                LatLng(item.latitud, item.longitud),
                16f
            )
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Circle(
                // aqui los uso
                center = LatLng(item.latitud, item.longitud),
                radius = 100.0,
                fillColor = Color(0x5532CD32),
                strokeColor = Color.Green
            )
            Marker(
                state = MarkerState(position = LatLng(item.latitud, item.longitud)),
                title = item.nombre,
                onClick = {
                    navController.navigate("${Routes.LocalDetail}/${item.id}")
                    true
                }
            )
        }
    }
}
