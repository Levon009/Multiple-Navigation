package com.example.jetpack_multiplenavigation.gamePack.views.background

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.jetpack_multiplenavigation.gamePack.panel.GamePanel

class Background(private val bitmap: Bitmap) {
    private var mX: Int = 0
    private var mY: Int = 0
    private var dx: Int = 0

    init {
        dx = GamePanel.MOVE_SPEED
    }

    fun update() {
        mX += dx

        if (mX < -GamePanel.WIDTH)
            mX = 0
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, mX.toFloat(), mY.toFloat(), null)

        if (mX < 0)
            canvas.drawBitmap(bitmap, mX.toFloat() + GamePanel.WIDTH, mY.toFloat(), null)
    }
}