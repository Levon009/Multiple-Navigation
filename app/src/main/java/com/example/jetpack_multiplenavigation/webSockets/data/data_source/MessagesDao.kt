package com.example.jetpack_multiplenavigation.webSockets.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {

    @Upsert
    suspend fun upsertMessage(message: MessageDto)

    @Delete
    suspend fun deleteMessage(message: MessageDto)

    @Query("SELECT * FROM messages ORDER BY timestamp")
    fun getAllMessages() : Flow<List<MessageDto>>

    companion object {
        const val MESSAGES_DB = "Messages.db"
    }
}