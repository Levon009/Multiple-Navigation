package com.example.jetpack_multiplenavigation.webSockets.domain.util

sealed class MessagesOrder(val messagesOrderType: MessagesOrderType) {
    class Date(orderType: MessagesOrderType) : MessagesOrder(orderType)

    fun copy(orderType: MessagesOrderType) : MessagesOrder {
        return when(this) {
            is Date -> Date(orderType)
        }
    }
}