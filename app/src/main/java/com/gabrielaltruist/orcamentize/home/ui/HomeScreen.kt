package com.example.gessolider.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gessolider.core.ui.theme.AppThemeProvider
import com.example.gessolider.home.ui.components.HomeCard
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Composable
fun HomeScreen() {
    Column(
        Modifier.Companion
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            Modifier.Companion
                .padding(8.dp)
                .fillMaxWidth(),
            Arrangement.Center
        )
        {
            Text("Seja Bem-Vindo", fontSize = 28.sp)
        }
        HorizontalDivider(Modifier.Companion.padding(horizontal = 24.dp))

        HomeCard({}, icon = Icons.Filled.Add, text = "Novo Orçamento")
        HomeCard({}, icon = Icons.Filled.Add, text = "Criar Serviço")
        HomeCard({}, icon = Icons.Filled.Add, text = "Adicionar Material")
        HomeCard({}, icon = Icons.Filled.Add, text = "Ver Materiais")
        HomeCard({}, icon = Icons.Filled.Add, text = "Ver Serviços")
        HomeCard({}, icon = Icons.Filled.Add, text = "Ver de Orçamentos")
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppThemeProvider {
        HomeScreen()
    }
}