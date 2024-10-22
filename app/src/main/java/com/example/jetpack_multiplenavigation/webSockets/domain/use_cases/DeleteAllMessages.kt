package com.example.jetpack_multiplenavigation.webSockets.domain.use_cases

import com.example.jetpack_multiplenavigation.webSockets.domain.repository.MessagesRepository

class DeleteAllMessages(
    private val repository: MessagesRepository
) {
    suspend operator fun invoke() {
        repository.deleteAllMessages()
    }
}