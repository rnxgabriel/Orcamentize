package com.gabrielaltruist.orcamentize.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.gabrielaltruist.orcamentize.home_feature.HomeScreen
import com.gabrielaltruist.orcamentize.material_feature.presentation.form.MaterialFormRoute

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                onNavigate = (navController::navigate)
            )
        }
        composable<MaterialFormRoute> { backStackEntry ->
            MaterialFormRoute(
                backStackEntry.toRoute<MaterialFormRoute>(),
                navController
            )
        }
        composable<MaterialListRoute> { }
    }
}