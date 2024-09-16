package com.example.jetpack_multiplenavigation.products.retrofit.data.repository

import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductsList() : Flow<ProductsResult<List<Product>>>
    suspend fun getProducts(page: Int, pageSize: Int) : kotlin.Result<List<Product>>
}