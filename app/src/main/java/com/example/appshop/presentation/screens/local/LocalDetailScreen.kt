package com.example.appshop.presentation.screens.local

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.appshop.presentation.theme.ui.GreenButton
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LocalDetailScreen(localId: Int?) {

    //uso el view model para acceder a los datos y buscar
    //el local con el id recibido
    val viewModel: LocalesViewModel = viewModel()
    val local = viewModel.locales.collectAsState().value.find { it.id == localId }

    local?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = it.nombre,
                style = MaterialTheme.typography.headlineLarge,
                color = GreenButton,
                fontWeight = FontWeight.ExtraBold
            )

            Divider(color = GreenButton.copy(alpha = 0.3f), thickness = 2.dp)

            Text(
                text = it.descripcion,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                lineHeight = 22.sp
            )

            AsyncImage(
                model = it.foto,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp, max = 400.dp),
                contentScale = ContentScale.Fit
            )

            Button(
                onClick = { /* Acción extra si quieres */ },
                colors = ButtonDefaults.buttonColors(containerColor = GreenButton),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Text(text = "Reservar Ahora", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
