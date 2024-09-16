package com.example.jetpack_multiplenavigation.meditation.feature

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardQuadTo(from: Offset, to: Offset) {
    quadraticTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2,
        abs(from.y + to.y) / 2
    )
}