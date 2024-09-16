package com.example.airfighers_jetpack.gamePack.views.smoke

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.airfighers_jetpack.gamePack.baseObject.ObjectView

class Smoke(mX: Int, mY: Int) : ObjectView() {
    private var r = 0

    init {
        this.mX = mX
        this.mY = mY
        r = 5
    }

    open fun update() {
        mX -= 10
    }

    open fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.color = Color.GRAY
        paint.style = Paint.Style.FILL

        canvas.drawCircle((mX - r).toFloat(), (mY - r).toFloat(), r.toFloat(), paint)
        canvas.drawCircle((mX - r + 2).toFloat(), (mY - r - 1).toFloat(), r.toFloat(), paint)
        canvas.drawCircle((mX - r + 4).toFloat(), (mY - r + 2).toFloat(), r.toFloat(), paint)
    }
}