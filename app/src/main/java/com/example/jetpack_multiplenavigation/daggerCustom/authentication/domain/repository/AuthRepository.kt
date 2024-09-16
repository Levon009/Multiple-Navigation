package com.example.jetpack_multiplenavigation.daggerCustom.authentication.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String)
}