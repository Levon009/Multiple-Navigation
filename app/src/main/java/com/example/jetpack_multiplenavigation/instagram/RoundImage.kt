package com.example.jetpack_multiplenavigation.instagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun RoundImage(
    modifier: Modifier = Modifier,
    painter: Painter
) {
    Image(
        painter = painter,
        contentDescription = "",
        contentScale = ContentScale.FillHeight,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}