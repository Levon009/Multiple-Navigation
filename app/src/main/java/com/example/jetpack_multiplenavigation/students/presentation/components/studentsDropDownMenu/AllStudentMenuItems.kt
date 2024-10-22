package com.example.jetpack_multiplenavigation.students.presentation.components.studentsDropDownMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh

fun getAllStudentMenuItems() : List<StudentDropMenuItem> {
    return listOf(
        StudentDropMenuItem(
            title = "Update",
            leadingIcon = Icons.Default.Refresh,
            trailingIcon = Icons.Default.PlayArrow
        ),
        StudentDropMenuItem(
            title = "Languages",
            leadingIcon = Icons.Default.Language,
            trailingIcon = Icons.Default.PlayArrow
        ),
        StudentDropMenuItem(
            title = "Marks",
            leadingIcon = Icons.Default.Bookmark,
            trailingIcon = Icons.Default.PlayArrow
        ),
        StudentDropMenuItem(
            title = "Log out",
            leadingIcon = Icons.Default.ExitToApp,
            trailingIcon = Icons.Default.PlayArrow
        )
    )
}