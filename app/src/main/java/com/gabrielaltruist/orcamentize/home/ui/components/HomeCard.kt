package com.example.gessolider.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gessolider.core.ui.theme.AppThemeProvider


@Composable
fun HomeCard(onClick: () -> Unit, modifier: Modifier = Modifier, icon: ImageVector, text: String) {
    Card(
        onClick,
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .then(modifier),
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(32.dp),
            Arrangement.spacedBy(8.dp),
            Alignment.CenterVertically
        ) {
            Icon(
                icon,
                text,
                Modifier.size(24.dp)
            )
            Text(text, fontSize = 16.sp)
        }
    }
}

@Preview
@Composable
private fun HomeCardPreview() {
    AppThemeProvider {
        HomeCard({}, icon = Icons.Filled.Add, text = "Adicionar Material")
    }
}