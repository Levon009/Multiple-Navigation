package com.example.jetpack_multiplenavigation.notes.core.sb

import androidx.compose.material3.SnackbarDuration

data class SBEvent(
    val message: String,
    val action: SBAction? = null,
    val duration: SnackbarDuration = SnackbarDuration.Long
)
