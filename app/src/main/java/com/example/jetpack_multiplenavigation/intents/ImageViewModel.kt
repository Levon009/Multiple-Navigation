package com.example.jetpack_multiplenavigation.intents

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {
    var isReceive by mutableStateOf(false)
        private set

    var uri: Uri? by mutableStateOf(null)
        private set

    fun updateUri(uri: Uri?) {
        this.uri = uri
    }

    fun updateIsReceive(value: Boolean) {
        this.isReceive = value
    }
}