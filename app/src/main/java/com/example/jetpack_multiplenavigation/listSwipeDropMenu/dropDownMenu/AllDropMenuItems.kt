package com.example.jetpack_multiplenavigation.listSwipeDropMenu.dropDownMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings

fun getAllDropMenuItems() : List<DropMenuItem> {
    return listOf(
        DropMenuItem(
            title = "Settings",
            leadingIcon = Icons.Default.Settings,
            trailingIcon = Icons.Default.PlayArrow
        ),
        DropMenuItem(
            title = "Favorite",
            leadingIcon = Icons.Default.Favorite,
            trailingIcon = Icons.Default.PlayArrow
        ),
        DropMenuItem(
            title = "Person",
            leadingIcon = Icons.Default.Person,
            trailingIcon = Icons.Default.PlayArrow
        ),
        DropMenuItem(
            title = "Log out",
            leadingIcon = Icons.AutoMirrored.Default.ExitToApp,
            trailingIcon = Icons.Default.PlayArrow
        )
    )
}