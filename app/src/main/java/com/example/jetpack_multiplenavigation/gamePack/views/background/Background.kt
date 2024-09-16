package com.example.airfighers_jetpack.gamePack.views.background

import android.graphics.Bitmap
import android.graphics.Canvas
import com.example.airfighers_jetpack.gamePack.panel.GamePanel

class Background(bitmap: Bitmap) {
    private var bitmap: Bitmap
    private var mX: Int = 0
    private var mY: Int = 0
    private var dx: Int = 0

    init {
        this.bitmap = bitmap
        dx = GamePanel.MOVE_SPEED
    }

    open fun update() {
        mX += dx

        if (mX < -GamePanel.WIDTH)
            mX = 0
    }

    open fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, mX.toFloat(), mY.toFloat(), null)

        if (mX < 0)
            canvas.drawBitmap(bitmap, mX.toFloat() + GamePanel.WIDTH, mY.toFloat(), null)
    }
}