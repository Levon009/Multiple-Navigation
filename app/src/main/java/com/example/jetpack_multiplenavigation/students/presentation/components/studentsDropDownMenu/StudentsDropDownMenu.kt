package com.example.jetpack_multiplenavigation.students.presentation.components.studentsDropDownMenu

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

@Composable
fun StudentsDropDownMenu(
    studentDropMenuItem: StudentDropMenuItem,
    onItemClick: (String) -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(
                text = studentDropMenuItem.title,
                fontFamily = FontFamily.Serif
            )
        },
        onClick = {
            onItemClick(studentDropMenuItem.title)
        },
        leadingIcon = {
            Icon(
                imageVector = studentDropMenuItem.leadingIcon,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                imageVector = studentDropMenuItem.trailingIcon,
                contentDescription = null
            )
        },
        colors = MenuDefaults.itemColors(
            trailingIconColor = Color.Red
        )
    )
}