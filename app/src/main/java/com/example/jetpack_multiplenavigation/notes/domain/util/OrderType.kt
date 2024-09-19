package com.example.jetpack_multiplenavigation.notes.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}