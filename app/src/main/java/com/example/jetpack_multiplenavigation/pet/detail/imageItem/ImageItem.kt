package com.example.jetpack_multiplenavigation.pet.detail.imageItem

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    painter: Painter
) {
    Image(
        painter = painter,
        contentDescription = null,
        alignment = Alignment.CenterStart,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}