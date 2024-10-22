package com.example.jetpack_multiplenavigation.webSockets.presentation.components.optionMenu

import androidx.compose.ui.graphics.vector.ImageVector

data class SocketsOptionMenuItem(
    val id: Int,
    val title: String,
    val leadingIcon: ImageVector,
    val trailingIcon: ImageVector? = null
)
