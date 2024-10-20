package com.example.jetpack_multiplenavigation.webSockets.data.repository

import com.example.jetpack_multiplenavigation.webSockets.data.data_source.MessagesDao
import com.example.jetpack_multiplenavigation.webSockets.domain.repository.MessagesRepository
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto
import kotlinx.coroutines.flow.Flow

class MessagesRepositoryImpl(
    private val dao: MessagesDao
) : MessagesRepository {

    override fun getMessages(): Flow<List<MessageDto>> {
        return dao.getAllMessages()
    }

    override suspend fun upsertMessage(message: MessageDto) {
        dao.upsertMessage(message)
    }

    override suspend fun deleteMessage(message: MessageDto) {
        dao.deleteMessage(message)
    }
}