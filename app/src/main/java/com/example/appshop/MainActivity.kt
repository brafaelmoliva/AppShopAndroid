package com.example.appshop

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.appshop.presentation.navigation.AppNavGraph
import com.example.appshop.presentation.theme.ui.AppshopTheme // 👈 AÑADIR ESTE IMPORT



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Quitar la barra de título de ventana
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        super.onCreate(savedInstanceState)

        setContent {
            AppshopTheme { // 👈 ENVOLVER TODO
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)

                }
            }
        }
    }
}
