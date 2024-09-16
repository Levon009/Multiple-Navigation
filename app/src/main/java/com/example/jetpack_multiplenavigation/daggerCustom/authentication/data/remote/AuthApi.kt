package com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.remote

import com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.model.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(
        @Body body: LoginRequest
    )

    companion object {
        const val BASE_URL = "https://my-url.com/"
    }
}