package com.example.jetpack_multiplenavigation.uploadFileRetrofit.database

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient3 {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val api: FileUploadApi = Retrofit.Builder()
        .baseUrl(FileUploadApi.UPLOAD_FILE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(FileUploadApi::class.java)
}