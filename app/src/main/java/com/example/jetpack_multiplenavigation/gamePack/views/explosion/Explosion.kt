package com.example.airfighers_jetpack.gamePack.views.explosion

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.airfighers_jetpack.gamePack.framesAnimation.FramesAnimation

class Explosion(bitmap: Bitmap, mX: Int, mY: Int, mWidth: Int, mHeight: Int, framesCount: Int) {
    private val framesAnimation = FramesAnimation()
    private var bitmap: Bitmap
    private var mX: Int = 0
    private var mY: Int = 0
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var row: Int = 0

    init {
        this.bitmap = bitmap
        this.mX = mX
        this.mY = mY
        this.mWidth = mWidth
        this.mHeight = mHeight

        val bitmaps = arrayOfNulls<Bitmap>(framesCount)

        for (i in bitmaps.indices) {
            if (i % 5 == 0 && i > 0) row++
            bitmaps[i] = Bitmap.createBitmap(bitmap, (i - row * 5) * mWidth, mHeight * row, mWidth, mHeight)
        }

        framesAnimation.setFrames(bitmaps)
        framesAnimation.setDelay(10)
    }

    open fun update() {
        if (!framesAnimation.isPlayedOnce())
            framesAnimation.update()
    }

    open fun draw(canvas: Canvas) {
        if (!framesAnimation.isPlayedOnce())
            canvas.drawBitmap(framesAnimation.getFrame(), mX.toFloat(), mY.toFloat(), null)
    }
}