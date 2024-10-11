package com.example.jetpack_multiplenavigation.gamePack.views.bomb

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import com.example.jetpack_multiplenavigation.gamePack.baseObject.ObjectView
import com.example.jetpack_multiplenavigation.gamePack.panel.GamePanel
import com.example.jetpack_multiplenavigation.gamePack.views.airObject.AirObject
import com.example.jetpack_multiplenavigation.R
import java.util.Random

class BombRelease(
    private val context: Context,
    private val airObject: AirObject
) {
    private val random = Random()
    private var bombs: MutableList<Bomb>? = null
    private var startTime: Long = 0

    init {
        bombs = arrayListOf()
        startTime = System.nanoTime()
    }

    fun getBombs() : MutableList<Bomb> = this.bombs!!

    private fun collision(a: ObjectView, b: ObjectView) : Boolean = Rect.intersects(a.getRectangle(), b.getRectangle())

    fun update() {
        val elapsed = (System.nanoTime() - startTime) / 1000000
        if (elapsed > (2000 - airObject.getScore() / 4)) {
            bombs?.add(
                Bomb(BitmapFactory.decodeResource(context.resources, R.drawable.bomb1),
                (GamePanel.WIDTH + 100), (random.nextDouble() * GamePanel.HEIGHT).toInt(),
                35, 35, 3
                )
            )
            startTime = System.nanoTime()
        }

        for (i in bombs!!.indices) {
            bombs!![i].update()

            if (collision(bombs!![i], airObject)) {
                bombs!!.removeAt(i)
                airObject.setFlying(false)
                break
            }

            if (bombs!![i].getX() < -100) {
                bombs!!.removeAt(i)
                break
            }
        }
    }

    fun draw(canvas: Canvas) {
        for (b in bombs!!) b.draw(canvas)
    }

    fun clear() {
        bombs?.clear()
    }
}