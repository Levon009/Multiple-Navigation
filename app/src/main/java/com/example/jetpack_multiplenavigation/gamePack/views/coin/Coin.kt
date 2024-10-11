package com.example.jetpack_multiplenavigation.gamePack.views.coin

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.jetpack_multiplenavigation.gamePack.baseObject.ObjectView
import com.example.jetpack_multiplenavigation.gamePack.framesAnimation.FramesAnimation
import com.example.jetpack_multiplenavigation.gamePack.panel.GamePanel

class Coin(
    bitmap: Bitmap,
    mX: Int,
    mY: Int,
    mWidth: Int,
    mHeight: Int,
    framesCount: Int
) : ObjectView() {
    private val framesAnimation = FramesAnimation()

    init {
        this.mX = mX
        this.mY = mY
        this.mWidth = mWidth
        this.mHeight = mHeight
        this.dy = 2
        this.dx = GamePanel.MOVE_SPEED

        val bitmaps = arrayOfNulls<Bitmap>(framesCount)

        for (i in bitmaps.indices) {
            bitmaps[i] = Bitmap.createBitmap(bitmap, mWidth * i, 0, mWidth, mHeight)
        }

        framesAnimation.setFrames(bitmaps)
        framesAnimation.setDelay(100)
    }

    fun update() {
        mY += dy
        mX += dx
        framesAnimation.update()
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(framesAnimation.getFrame(), mX.toFloat(), mY.toFloat(), null)
    }
}