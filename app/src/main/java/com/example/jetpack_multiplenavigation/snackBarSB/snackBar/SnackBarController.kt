package com.example.jetpack_multiplenavigation.snackBarSB.snackBar

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object SnackBarController {
    private val _eventsChannel = Channel<SnackBarEvent>()
    val eventsChannel = _eventsChannel.receiveAsFlow()

    suspend fun sendEvent(event: SnackBarEvent) {
        _eventsChannel.send(event)
    }
}