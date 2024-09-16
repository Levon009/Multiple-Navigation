package com.example.jetpack_multiplenavigation.authentication.retrofit

import com.example.jetpack_multiplenavigation.authentication.data.remote.AuthApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val authApi: AuthApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(AuthApi.BASE_URL)
        .build()
        .create(AuthApi::class.java)
}