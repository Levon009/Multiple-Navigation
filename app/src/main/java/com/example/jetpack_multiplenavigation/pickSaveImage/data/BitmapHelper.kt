package com.example.jetpack_multiplenavigation.pickSaveImage.data

import android.graphics.Bitmap

class BitmapHelper {
    private var bitmap: Bitmap? = null

    fun setBitmap(bitmap: Bitmap) {
        this.bitmap = bitmap
    }

    fun getBitmap() = this.bitmap

    companion object {
        private val instance = BitmapHelper()

        fun getInstance() = instance
    }
}