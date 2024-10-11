package com.example.jetpack_multiplenavigation.products.domain.repository

import com.example.jetpack_multiplenavigation.products.domain.data.model.Product
import com.example.jetpack_multiplenavigation.products.data.repository.ProductsResult
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductsList() : Flow<ProductsResult<List<Product>>>
    suspend fun getProducts(page: Int, pageSize: Int) : Result<List<Product>>
}