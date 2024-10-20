package com.example.jetpack_multiplenavigation.webSockets.presentation.messages

import com.example.jetpack_multiplenavigation.webSockets.domain.util.MessagesOrder
import com.example.jetpack_multiplenavigation.webSockets.domain.util.MessagesOrderType
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto

data class MessageState(
    val messages: List<MessageDto> = emptyList(),
    val messagesOrder: MessagesOrder = MessagesOrder.Date(MessagesOrderType.Ascending)
)
