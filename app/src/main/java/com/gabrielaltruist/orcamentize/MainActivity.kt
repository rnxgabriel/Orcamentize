package com.gabrielaltruist.orcamentize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gabrielaltruist.orcamentize.navigation.Navigation
import com.gabrielaltruist.orcamentize.ui.theme.AppThemeProvider


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppThemeProvider {
                Navigation()
            }
        }
    }
}
