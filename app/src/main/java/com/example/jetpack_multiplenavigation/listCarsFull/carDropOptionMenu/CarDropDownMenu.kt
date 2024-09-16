package com.example.jetpack_multiplenavigation.listCarsFull.carDropOptionMenu

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

@Composable
fun CarDropDownMenu(
    carDropMenuItem: CarDropMenuItem,
    onItemClick: (String) -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(
                text = carDropMenuItem.title,
                fontFamily = FontFamily.Serif
            )
        },
        onClick = {
            onItemClick(carDropMenuItem.title)
        },
        leadingIcon = {
            Icon(
                imageVector = carDropMenuItem.leadingIcon,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                imageVector = carDropMenuItem.trailingIcon,
                contentDescription = null
            )
        },
        colors = MenuDefaults.itemColors(
            trailingIconColor = Color.Red
        )
    )
}