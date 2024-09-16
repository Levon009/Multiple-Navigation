package com.example.jetpack_multiplenavigation.contentProvider.media.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpack_multiplenavigation.contentProvider.media.data.Image

class MediaContentViewModel : ViewModel() {

    var images by mutableStateOf(emptyList<Image>())
        private set

    fun updateImages(images: List<Image>) {
        this.images = images
    }
}