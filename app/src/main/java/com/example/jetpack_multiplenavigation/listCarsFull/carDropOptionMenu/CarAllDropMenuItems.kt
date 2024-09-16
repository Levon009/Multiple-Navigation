package com.example.jetpack_multiplenavigation.listCarsFull.carDropOptionMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings

fun getAllCarDropMenuItems() : List<CarDropMenuItem> {
    return listOf(
        CarDropMenuItem(
            title = "Settings",
            leadingIcon = Icons.Default.Settings,
            trailingIcon = Icons.Default.PlayArrow
        ),
        CarDropMenuItem(
            title = "Favorite",
            leadingIcon = Icons.Default.Favorite,
            trailingIcon = Icons.Default.PlayArrow
        ),
        CarDropMenuItem(
            title = "Person",
            leadingIcon = Icons.Default.Person,
            trailingIcon = Icons.Default.PlayArrow
        ),
        CarDropMenuItem(
            title = "Log out",
            leadingIcon = Icons.Default.ExitToApp,
            trailingIcon = Icons.Default.PlayArrow
        )
    )
}