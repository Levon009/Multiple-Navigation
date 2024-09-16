package com.example.jetpack_multiplenavigation.optionMenu

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
fun DropMenuItem2(
    logOut: String,
    onItemClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(
                text = logOut,
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