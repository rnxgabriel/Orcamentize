package com.gabrielaltruist.orcamentize.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabrielaltruist.orcamentize.home.ui.HomeRoute
import com.gabrielaltruist.orcamentize.home.ui.HomeScreen
import com.gabrielaltruist.orcamentize.material.ui.MaterialRoute

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HomeRoute) {
        composable<HomeRoute> { HomeScreen() }
        composable<MaterialRoute> { MaterialRoute(navController) }
    }
}