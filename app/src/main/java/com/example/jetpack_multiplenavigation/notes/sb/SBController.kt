package com.example.jetpack_multiplenavigation.notes.sb

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object SBController {
    private val _eventChannel = Channel<SBEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    suspend fun sendEvent(event: SBEvent) {
        _eventChannel.send(event)
    }
}