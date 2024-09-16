package com.example.jetpack_multiplenavigation.daggerCustom.authentication.retrofit

import com.example.jetpack_multiplenavigation.daggerCustom.authentication.data.remote.AuthApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val authApi: AuthApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(AuthApi.BASE_URL)
        .build()
        .create(AuthApi::class.java)
}