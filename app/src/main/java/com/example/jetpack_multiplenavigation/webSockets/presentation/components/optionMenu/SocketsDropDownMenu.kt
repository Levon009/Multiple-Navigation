package com.example.jetpack_multiplenavigation.webSockets.presentation.components.optionMenu

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

@Composable
fun SocketsDropDownMenu(
    socketsOptionMenuItem: SocketsOptionMenuItem,
    onItemClick: (Int) -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(
                text = socketsOptionMenuItem.title,
                fontFamily = FontFamily.Serif
            )
        },
        onClick = {
            onItemClick(socketsOptionMenuItem.id)
        },
        leadingIcon = {
            Icon(
                imageVector = socketsOptionMenuItem.leadingIcon,
                contentDescription = socketsOptionMenuItem.title
            )
        },
        trailingIcon = {
            socketsOptionMenuItem.trailingIcon?.apply {
                Icon(
                    imageVector = this,
                    contentDescription = socketsOptionMenuItem.title
                )
            }
        },
        colors = MenuDefaults.itemColors(
            trailingIconColor = Color.Red
        )
    )
}