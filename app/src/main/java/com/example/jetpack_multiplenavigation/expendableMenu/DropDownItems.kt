package com.example.jetpack_multiplenavigation.expendableMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings

val allDropDownItems = listOf(
    DropMenuItem(
        text = "Settings",
        leadingIcon = Icons.Default.Settings,
        trailingIcon = Icons.Default.PlayArrow
    ),
    DropMenuItem(
        text = "Favorite",
        leadingIcon = Icons.Default.Favorite,
        trailingIcon = Icons.Default.PlayArrow
    ),
    DropMenuItem(
        text = "Person",
        leadingIcon = Icons.Default.Person,
        trailingIcon = Icons.Default.PlayArrow
    ),
    DropMenuItem(
        text = "Log out",
        leadingIcon = Icons.AutoMirrored.Filled.ExitToApp,
        trailingIcon = Icons.Default.PlayArrow
    )
)