package com.example.jetpack_multiplenavigation.snackBarSB.snackBar

data class SnackBarAction(
    val name: String,
    val action: () -> Unit
)
