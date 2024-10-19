package com.example.jetpack_multiplenavigation.webSockets.presentation

import androidx.lifecycle.ViewModel
import com.example.jetpack_multiplenavigation.webSockets.presentation.model.MessageDto
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WebSocketsViewModel : ViewModel() {

    private val _socketStatus = MutableStateFlow(false)
    val socketStatus = _socketStatus.asStateFlow()

    private val _message = MutableStateFlow<Pair<Boolean, String>>(Pair(false, ""))
    val message = _message.asStateFlow()

    private val _messages = MutableStateFlow<MutableList<MessageDto>>(mutableListOf())
    val messages = _messages.asStateFlow()

    @OptIn(DelicateCoroutinesApi::class)
    fun setStatus(status: Boolean) = GlobalScope.launch(Dispatchers.Main) {
        _socketStatus.update { status }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun setMessage(message: Pair<Boolean, String>) = GlobalScope.launch(Dispatchers.Main) {
        if (_socketStatus.value) {
            _message.update { message }
        }
    }
}