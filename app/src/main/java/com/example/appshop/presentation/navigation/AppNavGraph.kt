package com.example.appshop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appshop.presentation.screens.animation.AnimationScreen
import com.example.appshop.presentation.screens.home.HomeScreen
import com.example.appshop.presentation.screens.home.search.SearchScreen
import com.example.appshop.presentation.screens.local.LocalDetailScreen
import com.example.appshop.presentation.screens.local.LocalListScreen
import com.example.appshop.presentation.screens.local.LocalMapScreen
import com.example.appshop.presentation.screens.login.LoginScreen

import com.example.appshop.presentation.screens.onboarding.OnboardingScreen
import com.example.appshop.presentation.screens.register.RegisterScreen
import com.example.appshop.presentation.screens.ventas.SucursalesFamosasScreen
import com.example.appshop.presentation.screens.ventas.VentasChartScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Animation) {
        composable(Routes.Animation) {
            AnimationScreen(navController = navController)
        }
        composable(Routes.Onboarding) {
            OnboardingScreen(navController = navController)
        }
        composable(Routes.Login) {
            val context = LocalContext.current
            LoginScreen(navController = navController, context = context)
        }
        composable(Routes.Register) {
            RegisterScreen(navController = navController)
        }
        composable(Routes.Home) {
            HomeScreen(navController = navController)
        }

        composable(Routes.Search) {
            SearchScreen(navController = navController)
        }
        composable(Routes.LocalList) {
            LocalListScreen(navController)
        }
        composable(Routes.LocalMap + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            LocalMapScreen(navController, id)
        }
        composable(Routes.LocalDetail + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            LocalDetailScreen(id)
        }

        composable(Routes.SucursalesFamosas) {
            SucursalesFamosasScreen(navController = navController)
        }


        composable(Routes.SucursalesFamosas) {
            SucursalesFamosasScreen(navController = navController) // ✅
        }

        composable(Routes.VentasChart) {
            VentasChartScreen()
        }




    }
}

