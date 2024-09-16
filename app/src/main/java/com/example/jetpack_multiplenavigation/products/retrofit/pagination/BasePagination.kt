package com.example.jetpack_multiplenavigation.products.retrofit.pagination

interface BasePagination<Page, Item> {
    suspend fun loadNextItem()
    fun reset()
}