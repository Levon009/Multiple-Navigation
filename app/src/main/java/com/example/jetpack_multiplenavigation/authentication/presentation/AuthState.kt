package com.example.jetpack_multiplenavigation.authentication.presentation

data class AuthState(
    val isLoading: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
)
