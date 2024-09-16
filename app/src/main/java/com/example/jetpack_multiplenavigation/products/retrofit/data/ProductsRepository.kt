package com.example.jetpack_multiplenavigation.products.retrofit.data

import com.example.jetpack_multiplenavigation.products.retrofit.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductsList() : Flow<ProductsResult<List<Product>>>
}