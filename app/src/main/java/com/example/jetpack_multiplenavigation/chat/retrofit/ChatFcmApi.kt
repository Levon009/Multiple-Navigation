package com.example.jetpack_multiplenavigation.chat.retrofit

import com.example.jetpack_multiplenavigation.chat.data.model.SendMessageDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatFcmApi {
    @POST("/send")
    suspend fun sendMessage(
        @Body body: SendMessageDto
    )

    @POST("/broadcast")
    suspend fun broadcast(
        @Body body: SendMessageDto
    )

    companion object {
        const val CHAT_BASE_URL = "http://10.0.2.2:8000/"
    }
}