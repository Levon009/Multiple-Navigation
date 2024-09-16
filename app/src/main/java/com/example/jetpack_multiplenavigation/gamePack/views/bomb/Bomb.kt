package com.example.airfighers_jetpack.gamePack.views.bomb

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.airfighers_jetpack.gamePack.baseObject.ObjectView
import com.example.airfighers_jetpack.gamePack.framesAnimation.FramesAnimation
import com.example.airfighers_jetpack.gamePack.panel.GamePanel

class Bomb(bitmap: Bitmap, mX: Int, mY: Int, mWidth: Int, mHeight: Int, framesCount: Int) : ObjectView() {
    private val framesAnimation = FramesAnimation()
    private var bitmap: Bitmap

    init {
        this.bitmap = bitmap
        this.mX = mX
        this.mY = mY
        this.mWidth = mWidth
        this.mHeight = mHeight
        this.dx = GamePanel.MOVE_SPEED

        val bitmaps = arrayOfNulls<Bitmap>(framesCount)

        for (i in bitmaps.indices) {
            bitmaps[i] = Bitmap.createBitmap(bitmap, mWidth * i, 0, mWidth, mHeight)
        }

        framesAnimation.setFrames(bitmaps)
        framesAnimation.setDelay(100)
    }

    open fun update() {
        mX += dx
        framesAnimation.update()
    }

    open fun draw(canvas: Canvas) {
        canvas.drawBitmap(framesAnimation.getFrame(), mX.toFloat(), mY.toFloat(), null)
    }
}