package com.example.jetpack_multiplenavigation.magnifierSection

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

fun mediumColoredPath(width: Int, height: Int) : Path {
    val mediumColoredPoint1 = Offset(0f, height * 0.3f)
    val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
    val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
    val mediumColoredPoint4 = Offset(width * 0.7f, height * 0.75f)
    val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

    return Path().apply {
        moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
        standardQuadToMag(mediumColoredPoint1, mediumColoredPoint2)
        standardQuadToMag(mediumColoredPoint2, mediumColoredPoint3)
        standardQuadToMag(mediumColoredPoint3, mediumColoredPoint4)
        standardQuadToMag(mediumColoredPoint4, mediumColoredPoint5)
        lineTo(width + 100f, height + 100f)
        lineTo(-100f, height + 100f)
        close()
    }
}

fun lightColoredPath(width: Int, height: Int) : Path {
    val lightColoredPoint1 = Offset(0f, height * 0.35f)
    val lightColoredPoint2 = Offset(width * 0.1f, height * 0.4f)
    val lightColoredPoint3 = Offset(width * 0.35f, height * 0.65f)
    val lightColoredPoint4 = Offset(width * 0.65f, height.toFloat())
    val lightColoredPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

    return Path().apply {
        moveTo(lightColoredPoint1.x, lightColoredPoint1.y)
        standardQuadToMag(lightColoredPoint1, lightColoredPoint2)
        standardQuadToMag(lightColoredPoint2, lightColoredPoint3)
        standardQuadToMag(lightColoredPoint3, lightColoredPoint4)
        standardQuadToMag(lightColoredPoint4, lightColoredPoint5)
        lineTo(width + 100f, height + 100f)
        lineTo(-100f, height + 100f)
        close()
    }
}