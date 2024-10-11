package com.example.jetpack_multiplenavigation.products.presentation.pagination

data class PaginationState(
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val page: Int = 0,
    val error: String? = null,
    val items: List<Any> = emptyList()
)
