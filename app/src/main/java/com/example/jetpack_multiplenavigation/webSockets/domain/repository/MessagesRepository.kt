package com.example.jetpack_multiplenavigation.webSockets.domain.repository

import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {

    fun getMessages() : Flow<List<MessageDto>>

    suspend fun upsertMessage(message: MessageDto)

    suspend fun deleteMessage(message: MessageDto)
}