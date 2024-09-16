package com.example.jetpack_multiplenavigation.authentication.data.remote

import com.example.jetpack_multiplenavigation.authentication.data.model.UserDto
import com.example.jetpack_multiplenavigation.authentication.data.remote.request.AuthRequest
import com.example.jetpack_multiplenavigation.authentication.data.remote.response.AuthResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("api/login")
    suspend fun loginUser(
        @Body body: AuthRequest
    ) : Call<AuthResponse>

    @POST("api/register")
    suspend fun registerUser(
        @Body body: AuthRequest
    ) : Call<AuthResponse>

    @GET("api/user/{id}")
    suspend fun getUser() : UserDto

    companion object {
        const val BASE_URL = "https://reqres.in/"
    }
}