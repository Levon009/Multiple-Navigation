package com.example.jetpack_multiplenavigation.editor

import android.graphics.Bitmap
import android.graphics.Canvas

class ObjectView : BaseObject {
    private val bitmap: Bitmap

    constructor(bitmap: Bitmap, mX: Int, mY: Int, mWidth: Int, mHeight: Int) {
        this.mX = mX
        this.mY = mY
        this.mWidth = mWidth
        this.mHeight = mHeight
        this.bitmap = Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, true)
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, mX.toFloat(), mY.toFloat(), null)
    }
}