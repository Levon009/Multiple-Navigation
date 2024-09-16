package com.example.jetpack_multiplenavigation.authentication.utils

sealed class AuthResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : AuthResult<T>(data)
    class Error<T>(data: T? = null, message: String) : AuthResult<T>(data, message)
}