package com.gabrielaltruist.orcamentize.home_feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielaltruist.orcamentize.home_feature.components.HomeCard
import com.gabrielaltruist.orcamentize.navigation.AppRoute
import com.gabrielaltruist.orcamentize.navigation.BudgetListRoute
import com.gabrielaltruist.orcamentize.navigation.CreateServiceRoute
import com.gabrielaltruist.orcamentize.navigation.MaterialFormRoute
import com.gabrielaltruist.orcamentize.navigation.MaterialListRoute
import com.gabrielaltruist.orcamentize.navigation.NewBudgetRoute
import com.gabrielaltruist.orcamentize.navigation.ServiceListRoute
import com.gabrielaltruist.orcamentize.ui.theme.AppThemeProvider

data class Card(
    val route: AppRoute,
    val icon: ImageVector,
    val text: String
)

@Composable
fun HomeScreen(
    onNavigate: (AppRoute) -> Unit
) {
    val buttons = listOf(
        Card(NewBudgetRoute, icon = Icons.Filled.Add, text = "Novo Orçamento"),
        Card(CreateServiceRoute, icon = Icons.Filled.Add, text = "Criar Serviço"),
        Card(MaterialFormRoute(), icon = Icons.Filled.Add, text = "Adicionar Material"),
        Card(MaterialListRoute, icon = Icons.Filled.Add, text = "Ver Materiais"),
        Card(ServiceListRoute, icon = Icons.Filled.Add, text = "Ver Serviços"),
        Card(BudgetListRoute, icon = Icons.Filled.Add, text = "Ver de Orçamentos")
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            Arrangement.Center
        )
        {
            Text("Seja Bem-Vindo", style = MaterialTheme.typography.displayLarge)
        }
        HorizontalDivider(Modifier.padding(horizontal = 24.dp))
        LazyColumn {
            items(buttons) { card ->
                HomeCard(
                    onClick = { onNavigate(card.route) },
                    icon = card.icon,
                    text = card.text
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppThemeProvider {
        HomeScreen(onNavigate = {})
    }
}
