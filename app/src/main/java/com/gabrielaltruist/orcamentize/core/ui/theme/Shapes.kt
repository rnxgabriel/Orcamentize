package com.example.yourappname.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    // Formas para componentes muito pequenos, como chips.
    extraSmall = RoundedCornerShape(4.dp),

    // Formas para componentes pequenos, como botões e campos de texto.
    small = RoundedCornerShape(8.dp),

    // Formas para componentes de tamanho médio, como cards.
    medium = RoundedCornerShape(12.dp),

    // Formas para componentes grandes, como a parte inferior de modais e painéis de navegação.
    large = RoundedCornerShape(16.dp),

    // Formas para componentes muito grandes, como o Navigation Drawer.
    extraLarge = RoundedCornerShape(28.dp)
)