package com.example.jetpack_multiplenavigation.webSockets.domain.use_cases

import com.example.jetpack_multiplenavigation.webSockets.domain.repository.MessagesRepository
import com.example.jetpack_multiplenavigation.webSockets.domain.util.MessagesOrder
import com.example.jetpack_multiplenavigation.webSockets.domain.util.MessagesOrderType
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMessages(
    private val repository: MessagesRepository
) {

    operator fun invoke(
        messagesOrder: MessagesOrder = MessagesOrder.Date(MessagesOrderType.Ascending)
    ) : Flow<List<MessageDto>> {

        return repository.getMessages().map { message ->
            when(messagesOrder.messagesOrderType) {
                is MessagesOrderType.Ascending -> {
                    message.sortedBy { it.timestamp }
                }
                is MessagesOrderType.Descending -> {
                    message.sortedByDescending { it.timestamp }
                }
            }
        }
    }
}