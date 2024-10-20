package com.example.jetpack_multiplenavigation.webSockets.presentation.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "messages")
data class MessageDto(
    val text: String,
    val isOwner: Boolean,
    val owner: String,
    val favorite: Boolean,
    val timestamp: Long,
    val date: String,
    @PrimaryKey
    val id: Int? = null
)
