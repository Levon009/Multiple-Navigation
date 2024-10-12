package com.example.jetpack_multiplenavigation.products.domain.use_cases

data class ProductsUseCases(
    val getProducts: GetProducts,
    val getPosts: GetPosts,
    val cratePost: CreatePost,
    val updatePost: UpdatePost,
    val deletePost: DeletePost
)
