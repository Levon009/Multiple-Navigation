package com.example.jetpack_multiplenavigation.products.data.retrofit

import com.example.jetpack_multiplenavigation.products.data.retrofit.data.ProductsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val productsApi: ProductsApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ProductsApi.BASE_URL)
        .client(okHttpClient)
        .build()
        .create(ProductsApi::class.java)

    val postApi: ProductsApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(ProductsApi.BASE_URL2)
        .build()
        .create(ProductsApi::class.java)
}