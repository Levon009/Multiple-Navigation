package com.example.jetpack_multiplenavigation.chat.data.model

import com.example.jetpack_multiplenavigation.chat.data.NotificationBody
import kotlinx.serialization.Serializable

@Serializable
data class SendMessageDto(
    val to: String?,
    val notification: NotificationBody
)
