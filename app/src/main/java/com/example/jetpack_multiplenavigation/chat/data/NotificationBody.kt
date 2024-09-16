package com.example.jetpack_multiplenavigation.chat.data

import kotlinx.serialization.Serializable

@Serializable
data class NotificationBody(
    val title: String,
    val body: String
)
