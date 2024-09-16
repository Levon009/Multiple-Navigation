package com.example.jetpack_multiplenavigation.meditation.feature

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val mediumColor: Color,
    val lightColor: Color,
    val darkColor: Color
)
