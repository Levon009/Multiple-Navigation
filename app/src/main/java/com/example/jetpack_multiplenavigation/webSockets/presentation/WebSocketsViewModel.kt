package com.example.jetpack_multiplenavigation.webSockets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_multiplenavigation.webSockets.domain.use_cases.MessagesUseCases
import com.example.jetpack_multiplenavigation.webSockets.domain.util.MessagesOrder
import com.example.jetpack_multiplenavigation.webSockets.domain.util.MessagesOrderType
import com.example.jetpack_multiplenavigation.webSockets.presentation.messages.MessageState
import com.example.jetpack_multiplenavigation.webSockets.presentation.messages.MessagesEvents
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WebSocketsViewModel(
    private val messagesUseCases: MessagesUseCases
) : ViewModel() {

    private val _socketStatus = MutableStateFlow(false)
    val socketStatus = _socketStatus.asStateFlow()

    private val _message = MutableStateFlow<Pair<Boolean, String>>(Pair(false, ""))
    val message = _message.asStateFlow()

    private val _state = MutableStateFlow(MessageState())
    val state = _state.asStateFlow()

    private var getMessagesJob: Job? = null

    init {
        getMessages(MessagesOrder.Date(MessagesOrderType.Ascending))
    }

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

    fun onEvent(events: MessagesEvents) {
        when(events) {
            is MessagesEvents.SaveMessage -> {
                viewModelScope.launch {
                    if (events.message.text.isNotEmpty()) {
                        messagesUseCases.addMessage(events.message)
                    }
                }
            }
            is MessagesEvents.DeleteMessage -> {
                viewModelScope.launch {
                    messagesUseCases.deleteMessage(events.messageDto)
                }
            }
            is MessagesEvents.Order -> {
                if (state.value.messagesOrder::class == events.messagesOrder::class &&
                    state.value.messagesOrder.messagesOrderType == events.messagesOrder.messagesOrderType) {
                    return
                }

                getMessages(events.messagesOrder)
            }

            MessagesEvents.DeleteAllMessages -> {
                viewModelScope.launch {
                    messagesUseCases.deleteAllMessages.invoke()
                }
            }
        }
    }

    private fun getMessages(messagesOrder: MessagesOrder) {
        getMessagesJob?.cancel()

        getMessagesJob = messagesUseCases.getMessages(messagesOrder)
            .onEach { messages ->
                _state.value = state.value.copy(
                    messages = messages,
                    messagesOrder = messagesOrder
                )
        }.launchIn(viewModelScope)
    }
}