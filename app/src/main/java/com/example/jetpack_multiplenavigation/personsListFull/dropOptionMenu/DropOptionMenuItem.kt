package com.example.draf.personsListFull.dropOptionMenu

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DropOptionMenuItem(
    item: DropMenuItem,
    onItemClick: (String) -> Unit
) {
    DropdownMenuItem(
        text = { Text(text = item.title) },
        onClick = {
            onItemClick(item.title)
        },
        leadingIcon = {
            Icon(
                imageVector = item.leadingIcon,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                imageVector = item.trailingIcon,
                contentDescription = null
            )
        },
        colors = MenuDefaults.itemColors(
            trailingIconColor = Color.Red
        )
    )
}