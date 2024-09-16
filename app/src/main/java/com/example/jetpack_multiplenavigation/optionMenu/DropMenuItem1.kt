package com.example.jetpack_multiplenavigation.optionMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

@Composable
fun DropMenuItem1(
    settings: String,
    onItemClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(
                text = settings,
                fontFamily = FontFamily.Serif,
            )
        },
        onClick = {
            onItemClick.invoke()
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play Arrow"
            )
        },
        colors = MenuDefaults.itemColors(
            trailingIconColor = Color.Green
        )
    )
}