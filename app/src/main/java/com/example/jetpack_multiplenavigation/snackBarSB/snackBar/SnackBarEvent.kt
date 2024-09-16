package com.example.jetpack_multiplenavigation.snackBarSB.snackBar

data class SnackBarEvent(
    val message: String,
    val action: SnackBarAction? = null
)
