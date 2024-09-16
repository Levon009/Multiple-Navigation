package com.example.jetpack_multiplenavigation.instagram.optionMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings

val optionMenuItems = listOf(
    OptionMenuItem(
        title = "DownloadManager",
        leadingIcon = Icons.Default.Download,
        trailingIcon = Icons.Default.PlayArrow
    ),
    OptionMenuItem(
        title = "Employee",
        leadingIcon = Icons.Default.Favorite,
        trailingIcon = Icons.Default.PlayArrow
    ),
    OptionMenuItem(
        title = "Koin",
        leadingIcon = Icons.Default.Refresh,
        trailingIcon = Icons.Default.PlayArrow
    ),
    OptionMenuItem(
        title = "Log out",
        leadingIcon = Icons.AutoMirrored.Filled.ExitToApp,
        trailingIcon = Icons.Default.PlayArrow
    ),
)