package com.example.jetpack_multiplenavigation.circleTouchFlag

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CircleTouchScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { context ->
            CircleTouch(context)
        })
    }
}