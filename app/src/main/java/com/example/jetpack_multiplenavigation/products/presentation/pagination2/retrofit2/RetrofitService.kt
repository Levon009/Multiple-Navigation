package com.example.jetpack_multiplenavigation.products.presentation.pagination2.retrofit2

import com.example.jetpack_multiplenavigation.products.presentation.pagination2.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("users")
    suspend fun getUsersList(@Query("page") page: Int): UserResponse

    companion object {
        const val BASE_URL3 = "https://reqres.in/api/"
    }
}