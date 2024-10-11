package com.example.jetpack_multiplenavigation.gamePack.views.missile.fireMissile

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.jetpack_multiplenavigation.gamePack.baseObject.ObjectView
import com.example.jetpack_multiplenavigation.gamePack.framesAnimation.FramesAnimation

class FireMissile(
    bitmap: Bitmap,
    mX: Int,
    mY: Int,
    mWidth: Int,
    mHeight: Int,
    framesCount: Int
) : ObjectView() {

    private val framesAnimation = FramesAnimation()
    private var startTime: Long = 0

    init {
        this.mX = mX
        this.mY = mY
        this.mWidth = mWidth
        this.mHeight = mHeight
        dx = 35
        startTime = System.nanoTime()

        val bitmaps = arrayOfNulls<Bitmap>(framesCount)

        for (i in bitmaps.indices) {
            bitmaps[i] = Bitmap.createBitmap(bitmap, 0, mHeight * i, mWidth, mHeight)
        }

        framesAnimation.setFrames(bitmaps)
        framesAnimation.setDelay(100)
    }

    fun update() {
        mX += dx
        framesAnimation.update()
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(framesAnimation.getFrame(), mX.toFloat(), mY.toFloat(), null)
    }
}