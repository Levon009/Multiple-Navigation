package com.example.jetpack_multiplenavigation.webSockets.presentation.components.optionMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Update

val socketsMenuItems = listOf(
    SocketsOptionMenuItem(
        id = 0,
        title = "Update",
        leadingIcon = Icons.Default.Update,
    ),
    SocketsOptionMenuItem(
        id = 1,
        title = "Delete All",
        leadingIcon = Icons.Default.DeleteForever,
    )
)