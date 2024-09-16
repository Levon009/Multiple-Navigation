package com.example.jetpack_multiplenavigation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_multiplenavigation.ui.theme.JetPack_MultipleNavigationTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPack_MultipleNavigationTheme {
                SplashScreen()
            }
        }
    }

    @Preview
    @Composable
    private fun SplashScreen() {
        val alpha = remember {
            Animatable(0f)
        }
        LaunchedEffect(key1 = true) {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(2500)
            )
            delay(2000)
            Intent(this@SplashActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E1D3))
        ) {
            Image(
                painter = painterResource(id = R.drawable.lion),
                contentDescription = "Lion",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .scale(1.5f)
                    .alpha(alpha.value)
            )
        }
    }
}