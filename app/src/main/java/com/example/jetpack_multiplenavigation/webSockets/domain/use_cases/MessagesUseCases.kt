package com.example.jetpack_multiplenavigation.webSockets.domain.use_cases

data class MessagesUseCases(
    val addMessage: AddMessage,
    val deleteMessage: DeleteMessage,
    val getMessages: GetMessages
)