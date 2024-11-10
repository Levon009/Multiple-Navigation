package com.example.jetpack_multiplenavigation.fruits.data.model

import androidx.compose.ui.graphics.Color
import com.example.jetpack_multiplenavigation.fruits.data.FruitCategory

data class Fruit(
    val color: Color,
    val name: String,
    val description: String,
    val category: FruitCategory,
    val isSelected: Boolean = false
)
