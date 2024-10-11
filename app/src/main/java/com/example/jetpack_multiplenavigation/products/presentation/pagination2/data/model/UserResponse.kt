package com.example.jetpack_multiplenavigation.products.presentation.pagination2.data.model

data class UserResponse(
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<User>,
    var support: Support
)
