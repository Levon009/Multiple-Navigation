package com.example.airfighers_jetpack.gamePack.views.airObject

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.airfighers_jetpack.gamePack.baseObject.ObjectView
import com.example.airfighers_jetpack.gamePack.framesAnimation.FramesAnimation
import com.example.airfighers_jetpack.gamePack.panel.GamePanel

class AirObject(bitmap: Bitmap, mWidth: Int, mHeight: Int, framesCount: Int) : ObjectView() {
    private val framesAnimation = FramesAnimation()
    private var bitmap: Bitmap
    private var startTime: Long = 0
    private var score: Int = 0
    private var coins: Int = 0
    private var up = false
    private var flying = false

    init {
        this.bitmap = bitmap
        this.mX = 100
        this.mY = GamePanel.HEIGHT / 2
        this.dy = 0
        this.mWidth = mWidth
        this.mHeight = mHeight
        this.coins = 0
        this.score = 0
        this.startTime = System.nanoTime()

        val bitmaps = arrayOfNulls<Bitmap>(framesCount)

        for (i in bitmaps.indices) {
            bitmaps[i] = Bitmap.createBitmap(bitmap, mWidth * i, 0, mWidth, mHeight)
        }

        framesAnimation.setFrames(bitmaps)
        framesAnimation.setDelay(100)
    }

    open fun addCoins() {
        coins++
    }

    open fun getCoins() : Int {
        return this.coins
    }

    open fun resetCoins() {
        this.coins = 0
    }

    open fun setUp(up: Boolean) {
        this.up = up
    }

    open fun setFlying(flying: Boolean) {
        this.flying = flying
    }

    open fun isFlying() : Boolean = this.flying

    open fun resetScore() {
        this.score = 0
    }

    open fun getScore() : Int = this.score

    open fun resetDY() {
        this.dy = 0
    }

    open fun update() {
        val elapsed = (System.nanoTime() - startTime) / 1000000
        if (elapsed > 100) {
            score++
            startTime = System.nanoTime()
        }

        framesAnimation.update()

        if (up) dy -= 1 else dy += 2
        if (dy > 6) dy = 6
        if (dy < -4) dy = -4

        mY += dy
    }

    open fun draw(canvas: Canvas) {
        canvas.drawBitmap(framesAnimation.getFrame(), mX.toFloat(), mY.toFloat(), null)
    }
}