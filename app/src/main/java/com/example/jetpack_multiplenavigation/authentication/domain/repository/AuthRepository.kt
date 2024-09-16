package com.example.jetpack_multiplenavigation.authentication.domain.repository

import com.example.jetpack_multiplenavigation.authentication.data.model.UserDto
import com.example.jetpack_multiplenavigation.authentication.data.remote.request.AuthRequest
import com.example.jetpack_multiplenavigation.authentication.data.remote.response.AuthResponse
import com.example.jetpack_multiplenavigation.authentication.utils.AuthResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body

interface AuthRepository {
    suspend fun loginUser(
        @Body body: AuthRequest
    ) : AuthResult<AuthResponse>

    suspend fun registerUser(
        @Body body: AuthRequest
    ) : AuthResult<AuthResponse>

    suspend fun getUser() : Flow<AuthResult<UserDto>>
}