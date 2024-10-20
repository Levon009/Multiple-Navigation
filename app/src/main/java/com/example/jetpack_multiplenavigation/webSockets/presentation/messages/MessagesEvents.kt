package com.example.jetpack_multiplenavigation.webSockets.presentation.messages

import com.example.jetpack_multiplenavigation.webSockets.domain.util.MessagesOrder
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto

sealed class MessagesEvents {

    data class SaveMessage(val message: MessageDto) : MessagesEvents()
    data class Order(val messagesOrder: MessagesOrder) : MessagesEvents()
    data class DeleteMessage(val messageDto: MessageDto) : MessagesEvents()
}