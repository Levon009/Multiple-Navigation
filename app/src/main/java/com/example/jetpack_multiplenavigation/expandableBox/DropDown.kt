package com.example.jetpack_multiplenavigation.expandableBox

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropDown(
    modifier: Modifier = Modifier,
    text: String,
    initiallyOpened: Boolean = false,
    content: @Composable () -> Unit
) {
    var isOpened by remember {
        mutableStateOf(false)
    }
    val alpha = animateFloatAsState(
        targetValue = if (isOpened) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300
        ), label = ""
    )
    val rotateX = animateFloatAsState(
        targetValue = if (isOpened) 0f else -90f,
        animationSpec = tween(
            durationMillis = 300
        )
    )
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Arrow drop down",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        isOpened = !isOpened
                    }
                    .scale(1f, if (isOpened) -1f else 1f)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(
                    transformOrigin = TransformOrigin(0.5f, 0f),
                    rotationX = rotateX.value
                )
                .alpha(alpha.value)
        ) {
            content()
        }
    }
}