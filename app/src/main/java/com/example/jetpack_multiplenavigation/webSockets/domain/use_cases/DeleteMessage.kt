package com.example.jetpack_multiplenavigation.webSockets.domain.use_cases

import com.example.jetpack_multiplenavigation.webSockets.domain.repository.MessagesRepository
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto

class DeleteMessage(
    private val repository: MessagesRepository
) {
    suspend operator fun invoke(message: MessageDto) {
        repository.deleteMessage(message)
    }
}