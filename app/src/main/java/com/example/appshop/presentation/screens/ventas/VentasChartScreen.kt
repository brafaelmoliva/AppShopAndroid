package com.example.appshop.presentation.screens.ventas

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appshop.dao.ventas.VentasDatabaseProvider
@Composable
fun VentasChartScreen() {
    val context = LocalContext.current
    val dao = remember {
        VentasDatabaseProvider.getDatabase(context).ventasDao()
    }
    val ventas by dao.obtenerVentas().collectAsState(initial = emptyList())
    val textMeasurer = rememberTextMeasurer()

    val maxValor = ventas.maxOfOrNull { it.totalVentas } ?: 1

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val anchoTotal = size.width
        val altoBarra = 40.dp.toPx()
        val espacioEntreBarras = 24.dp.toPx()
        val espacioEtiqueta = 160f  // espacio para etiqueta ciudad
        val margenNumero = 12f       // margen para el número fuera de barra

        // Preparamos Paint para medir texto numérico
        val paint = Paint().apply {
            textSize = 30f
            isFakeBoldText = true
            isAntiAlias = true
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.LEFT
        }

        // Para evitar recalcular el ancho del texto de cada número cada vez,
        // medimos el texto más largo (el número máximo)
        val textoMax = maxValor.toString()
        val anchoTextoMax = paint.measureText(textoMax)

        // Calculamos el ancho máximo de la barra, restando:
        // espacio para etiqueta + margen + ancho del texto numérico + margen final de 16dp
        val margenFinal = 16.dp.toPx()
        val anchoMaximo = anchoTotal - espacioEtiqueta - margenNumero - anchoTextoMax - margenFinal

        val razon = anchoMaximo / maxValor

        ventas.forEachIndexed { i, venta ->
            val yOffset = i * (altoBarra + espacioEntreBarras)
            val ancho = venta.totalVentas * razon

            val colorRelleno = Color(0xFF4CAF50).copy(alpha = 0.8f)
            val colorBorde = Color(0xFF2E7D32)

            // Dibuja la barra
            drawRect(
                color = colorRelleno,
                topLeft = Offset(espacioEtiqueta, yOffset),
                size = androidx.compose.ui.geometry.Size(ancho, altoBarra)
            )

            drawRect(
                color = colorBorde,
                topLeft = Offset(espacioEtiqueta, yOffset),
                size = androidx.compose.ui.geometry.Size(ancho, altoBarra),
                style = Stroke(width = 3f)
            )

            // Dibuja etiqueta ciudad
            val label = textMeasurer.measure(
                text = venta.ciudad,
                style = TextStyle(fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            )
            drawText(
                textLayoutResult = label,
                topLeft = Offset(espacioEtiqueta - label.size.width - 12f, yOffset + altoBarra / 2 - label.size.height / 2)
            )

            // Número fuera de la barra, a la derecha, con margen
            val xPos = espacioEtiqueta + ancho + margenNumero
            val yPos = yOffset + altoBarra / 2 + paint.textSize / 3 // para centrar verticalmente

            drawContext.canvas.nativeCanvas.drawText(
                venta.totalVentas.toString(),
                xPos,
                yPos,
                paint
            )
        }
    }
}

