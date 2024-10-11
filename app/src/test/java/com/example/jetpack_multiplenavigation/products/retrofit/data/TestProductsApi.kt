package com.example.jetpack_multiplenavigation.products.retrofit.data

import com.example.jetpack_multiplenavigation.products.domain.data.model.Post
import retrofit2.http.GET

interface TestProductsApi {
    @GET("posts")
    suspend fun getProductsTest() : List<Post>
}