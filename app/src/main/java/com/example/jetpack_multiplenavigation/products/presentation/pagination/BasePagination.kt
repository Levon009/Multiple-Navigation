package com.example.jetpack_multiplenavigation.products.presentation.pagination

interface BasePagination<Page, Item> {
    suspend fun loadNextItem()
    fun reset()
}