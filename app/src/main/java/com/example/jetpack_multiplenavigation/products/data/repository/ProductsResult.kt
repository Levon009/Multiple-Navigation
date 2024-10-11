package com.example.jetpack_multiplenavigation.products.data.repository

sealed class ProductsResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : ProductsResult<T>(data)
    class Error<T>(data: T? = null, message: String) : ProductsResult<T>(data, message)
}