package com.example.jetpack_multiplenavigation.magnifierSection

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardQuadToMag(from: Offset, to: Offset) {
    quadraticTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2,
        abs(from.y + to.y) / 2
    )
}