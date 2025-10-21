package com.gabrielaltruist.orcamentize.features.material.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.gabrielaltruist.orcamentize.ui.theme.AppThemeProvider
import kotlinx.serialization.Serializable

@Serializable
object MaterialListRoute

@Composable
fun MaterialListRoute(navController: NavHostController) {
    MaterialListScreen() { }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialListScreen(
    onAddMaterial: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Materiais") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddMaterial) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Material")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Lista de materiais (TODO)")
        }
    }
}

@Preview
@Composable
fun MaterialListScreenPreview() {
    AppThemeProvider {
        MaterialListScreen(onAddMaterial = {})
    }
}
