package com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.repository

import com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.remote.AuthApi
import com.example.jetpack_multiplenavigation.daggerCustom.authentication.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        try {
            println("Logging in...")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}