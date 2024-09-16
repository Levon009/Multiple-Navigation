package com.example.jetpack_multiplenavigation.textPrinter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TextPrinterViewModel : ViewModel() {
    private val _textChannel = Channel<String>()
    val textChannel = _textChannel.receiveAsFlow()

    var characters by mutableStateOf("")
        private set

    fun updateCharacters(ch: String) {
        characters += ch
    }

    fun typeText(textMessage: String) {
        var c = 0
        viewModelScope.launch {
            while (c < textMessage.toCharArray().size) {
                _textChannel.send(textMessage.toCharArray()[c].toString())
                delay(150)
                c++
            }
        }
    }
}