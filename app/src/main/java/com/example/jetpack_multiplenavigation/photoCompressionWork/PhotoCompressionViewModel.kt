package com.example.jetpack_multiplenavigation.photoCompressionWork

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID

class PhotoCompressionViewModel : ViewModel() {
    var uncompressedUri by mutableStateOf<Uri?>(null)
        private set

    var compressedBitmap by mutableStateOf<Bitmap?>(null)
        private set

    var workId by mutableStateOf<UUID?>(null)
        private set

    fun updateUncompressedUri(uri: Uri?) {
        uncompressedUri = uri
    }

    fun updateCompressedBitmap(bitmap: Bitmap?) {
        compressedBitmap = bitmap
    }

    fun updateWorkId(id: UUID?) {
        workId = id
    }
}