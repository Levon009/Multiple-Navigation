package com.example.jetpack_multiplenavigation.products.retrofit.data

import androidx.room.Dao
import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Post
import retrofit2.http.GET

@Dao
interface TestProductsApi {
    @GET("posts")
    suspend fun getProductsTest() : List<Post>
}