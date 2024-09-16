package com.example.jetpack_multiplenavigation.chat.data

data class ChatState(
    val isEnteringToken: Boolean = true,
    val remoteToken: String = "",
    val messageText: String = ""
)
