package com.example.jetpack_multiplenavigation.webSockets.domain.use_cases

import com.example.jetpack_multiplenavigation.webSockets.domain.model.InvalidMessageException
import com.example.jetpack_multiplenavigation.webSockets.domain.repository.MessagesRepository
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto

class AddMessage(
    private val repository: MessagesRepository
) {

    @Throws(InvalidMessageException::class)
    suspend operator fun invoke(message: MessageDto) {

        if (message.text.isBlank()) {
            throw InvalidMessageException("The content of message can't be empty!")
        }

        repository.upsertMessage(message)
    }
}