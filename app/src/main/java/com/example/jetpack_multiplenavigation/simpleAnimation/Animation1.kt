package com.example.jetpack_multiplenavigation.simpleAnimation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Animation1(modifier: Modifier = Modifier) {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size by animateDpAsState(
        targetValue = sizeState,
        animationSpec = tween(
            durationMillis = 3000,
            delayMillis = 300,
            easing = LinearOutSlowInEasing
        ),
        label = ""
    )
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .background(color)
    ) {
        ElevatedButton(
            onClick = {
                sizeState += 50.dp
            },
            elevation = ButtonDefaults.buttonElevation(25.dp)
        ) {
            Text(text = "Increase Me")
        }
    }
}