package com.example.jetpack_multiplenavigation.webSockets.presentation.model

data class MessageDto(
    val text: String,
    val isOwner: Boolean,
    val owner: String,
    val favorite: Boolean,
    val timestamp: Long,
    val date: String,
    val id: Int? = null
)
