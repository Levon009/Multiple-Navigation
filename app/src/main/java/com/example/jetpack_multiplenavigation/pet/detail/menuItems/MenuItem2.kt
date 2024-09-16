package com.example.jetpack_multiplenavigation.pet.detail.menuItems

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

@Composable
fun MenuItem2(onItemClick: () -> Unit) {
    DropdownMenuItem(
        text = {
            Text(
                text = "Log out",
                fontFamily = FontFamily.Serif,
            )
        },
        onClick = {
            onItemClick.invoke()
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                contentDescription = "Exit to app"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play Arrow"
            )
        },
        colors = MenuDefaults.itemColors(
            trailingIconColor = Color.Red
        )
    )
}