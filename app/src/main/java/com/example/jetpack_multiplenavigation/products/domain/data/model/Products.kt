package com.example.jetpack_multiplenavigation.products.domain.data.model

data class Products(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)