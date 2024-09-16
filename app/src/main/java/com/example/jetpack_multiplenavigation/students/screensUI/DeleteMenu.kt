package com.example.draf.students.screensUI

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun DeleteMenu(onClick: () -> Unit) {
    DropdownMenuItem(
        text = {
            Text(
                text = "Delete",
                fontFamily = FontFamily.Serif
            )
        },
        onClick = {
            onClick()
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh",
                modifier = Modifier.padding(end = 4.dp)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "PlayArrow",
                modifier = Modifier.padding(start = 4.dp)
            )
        },
        colors = MenuDefaults.itemColors(
            trailingIconColor = Color.Red
        )
    )
}