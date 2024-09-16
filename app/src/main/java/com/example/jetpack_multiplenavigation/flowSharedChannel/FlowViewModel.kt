package com.example.jetpack_multiplenavigation.flowSharedChannel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlowViewModel : ViewModel() {
    private val _textState = MutableStateFlow("")
    val textState = _textState.asStateFlow()

    private val _shareState = MutableSharedFlow<Int>()
    val shareState = _shareState.asSharedFlow()

    private val _channelState = Channel<Int>()
    val channelState =_channelState.receiveAsFlow()

    init {
//        viewModelScope.launch {
//            var num = 0
//            while (num < 1000) {
//                _channelState.send(num)
//                num++
//                delay(500)
//            }
//        }
        viewModelScope.launch {
            var num = 0
            while (num < 1000) {
                _shareState.emit(num)
                num++
                delay(500)
            }
        }
    }

    fun changeText(text: String) {
        _textState.update { text }
    }
}