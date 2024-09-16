package com.example.jetpack_multiplenavigation.editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun EditorScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = {
            Editor(context = it)
        })
    }
}