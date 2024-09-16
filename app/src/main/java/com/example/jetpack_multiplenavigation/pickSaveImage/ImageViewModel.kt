package com.example.jetpack_multiplenavigation.pickSaveImage

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

    var uris by mutableStateOf<List<Uri>>(emptyList())
        private set

    fun updateUri(uri: Uri?) {
        this.uri = uri
    }

    fun updateUris(uris: List<Uri>) {
        this.uris = uris
    }

    fun updateIsReceive(value: Boolean) {
        this.isReceive = value
    }
}