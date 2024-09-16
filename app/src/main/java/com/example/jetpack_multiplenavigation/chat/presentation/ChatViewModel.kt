package com.example.jetpack_multiplenavigation.chat.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.chat.data.ChatState
import com.example.jetpack_multiplenavigation.chat.data.NotificationBody
import com.example.jetpack_multiplenavigation.chat.data.model.SendMessageDto
import com.example.jetpack_multiplenavigation.chat.retrofit.ChatFcmApi
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException
import java.io.IOException

class ChatViewModel(
    private val api: ChatFcmApi
) : ViewModel() {

    var state by mutableStateOf(ChatState())
        private set

    init {
        viewModelScope.launch {
            Firebase.messaging.subscribeToTopic("chat").await()
        }
    }

    fun onRemoteTokenChange(newToken: String) {
        state = state.copy(
            remoteToken = newToken
        )
    }

    fun onSubmitRemoteToken() {
        state = state.copy(
            isEnteringToken = false
        )
    }

    fun onMessageChange(newMessage: String) {
        state = state.copy(
            messageText = newMessage
        )
    }

    fun sendMessage(isBroadcast: Boolean) {
        viewModelScope.launch {
            val sendMessageDto = SendMessageDto(
                to = if (isBroadcast) null else state.remoteToken,
                notification = NotificationBody(
                    title = "New Message",
                    body = state.messageText
                )
            )

            try {
                if (isBroadcast) {
                    api.broadcast(sendMessageDto)
                } else {
                    api.sendMessage(sendMessageDto)
                }
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}