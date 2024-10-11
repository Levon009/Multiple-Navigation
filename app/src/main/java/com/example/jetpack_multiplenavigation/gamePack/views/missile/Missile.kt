package com.example.jetpack_multiplenavigation.gamePack.views.missile

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.jetpack_multiplenavigation.gamePack.baseObject.ObjectView
import com.example.jetpack_multiplenavigation.gamePack.framesAnimation.FramesAnimation
import java.util.Random

class Missile(
    bitmap: Bitmap,
    mX: Int,
    mY: Int,
    mWidth: Int,
    mHeight: Int,
    score: Int,
    framesCount: Int
) : ObjectView() {

    private val framesAnimation = FramesAnimation()
    private val random = Random()
    private var score: Int = 0
    private var speed: Int = 0

    init {
        this.mX = mX
        this.mY = mY
        this.mWidth = mWidth
        this.mHeight = mHeight
        this.score = score
        this.speed = 7 + (random.nextDouble() * score / 4).toInt()
        if (speed > 40) speed = 40

        val bitmaps = arrayOfNulls<Bitmap>(framesCount)

        for (i in bitmaps.indices) {
            bitmaps[i] = Bitmap.createBitmap(bitmap, 0, mHeight * i, mWidth, mHeight)
        }

        framesAnimation.setFrames(bitmaps)
        framesAnimation.setDelay(100 - speed.toLong())
    }

    fun update() {
        mX -= speed
        framesAnimation.update()
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(framesAnimation.getFrame(), mX.toFloat(), mY.toFloat(), null)
    }
}