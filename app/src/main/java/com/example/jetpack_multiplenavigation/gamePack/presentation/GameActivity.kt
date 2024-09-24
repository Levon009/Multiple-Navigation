package com.example.jetpack_multiplenavigation.gamePack.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.airfighers_jetpack.gamePack.panel.GamePanel
import com.example.jetpack_multiplenavigation.ui.theme.JetPack_MultipleNavigationTheme

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        enableEdgeToEdge()
        setContent {
            JetPack_MultipleNavigationTheme {
                enableEdgeToEdge()
                AirFighters()
            }
        }
    }

    @Composable
    private fun AirFighters() {
        AndroidView(factory = { context ->
            GamePanel(context = context)
        })
    }

    private fun hideStatusBar() {
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}