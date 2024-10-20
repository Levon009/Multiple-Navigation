package com.example.jetpack_multiplenavigation.webSockets.domain.util

sealed class MessagesOrderType {
    data object Ascending : MessagesOrderType()
    data object Descending : MessagesOrderType()
}