package com.example.jetpack_multiplenavigation.products.retrofit.data

import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Post
import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Products
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ProductsApi {

    @GET("products/{type}")
    suspend fun getProductsList(
        @Path("type") type: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ) : Products

    @GET("products")
    suspend fun getProductsList() : Products

    @GET("products/{id}")
    suspend fun getProducts(
        @Path("id") id: Int
    ) : Call<Products>

    @GET("products/{id}")
    suspend fun getProducts(
        @Path("id") id: Int,
        @Query("_sort") sort: String?,
        @Query("_order") order: String?
    ) : Call<Products>

    @GET("posts")
    fun getProducts(@QueryMap parameters: MutableMap<String?, String?>?) : Call<List<Post>>

    @GET("posts")
    suspend fun getProductsTest() : List<Post>

    @POST("posts")
    fun createProduct(@Body post: Post) : Call<Post>

    @POST("posts")
    fun createProduct(
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("description") description: String
    ) : Call<Post>

    @POST("posts")
    fun createProduct(@QueryMap parameters: MutableMap<String?, String?>?) : Call<Post>

    @PUT("posts/{id}")
    fun putProduct(
        @Path("id") id: Int,
        @Body product: Post
    ) : Call<Post>

    @Headers("Static-Header: 123", "Static-Header: 456")
    @PUT("posts/{id}")
    fun putProduct(
        @Header("Dynamic-Header") header: String,
        @Path("id") id: Int,
        @Body product: Post
    ) : Call<Post>

    @PATCH("posts/{id}")
    fun patchProduct(
        @Path("id") id: Int,
        @Body product: Post
    ) : Call<Post>

    @PATCH("posts/{id}")
    fun patchProduct(
        @HeaderMap headers: Map<String?, String?>?,
        @Path("id") id: Int,
        @Body product: Post?
    ) : Call<Post>

    @DELETE("posts/{id}")
    fun deleteProduct(@Path("id") id: Int) : Call<Void>

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
        const val BASE_URL2 = "https://jsonplaceholder.typicode.com/"
    }
}