package com.example.airfighers_jetpack.gamePack.views.missile

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import com.example.airfighers_jetpack.gamePack.baseObject.ObjectView
import com.example.airfighers_jetpack.gamePack.panel.GamePanel
import com.example.airfighers_jetpack.gamePack.views.airObject.AirObject
import com.example.jetpack_multiplenavigation.R
import java.util.Random

class MissileRelease(context: Context, airObject: AirObject) {
    private val random = Random()
    private var context: Context
    private var airObject: AirObject
    private var missiles: MutableList<Missile>? = null
    private var missileStartTime: Long = 0

    init {
        this.context = context
        this.airObject = airObject
        missiles = arrayListOf()
        missileStartTime = System.nanoTime()
    }

    open fun getMissiles() : MutableList<Missile> = this.missiles!!

    private fun collision(a: ObjectView, b: ObjectView) : Boolean = Rect.intersects(a.getRectangle(), b.getRectangle())

    open fun update() {
        val elapsed = (System.nanoTime() - missileStartTime) / 1000000
        if (elapsed > (2000 - airObject.getScore() / 4)) {
            missiles?.add(
                Missile(
                    BitmapFactory.decodeResource(context.resources, R.drawable.missile3),
                    GamePanel.WIDTH, (random.nextDouble() * GamePanel.HEIGHT).toInt(),
                    45, 15, airObject.getScore(), 13
                )
            )
            missileStartTime = System.nanoTime()
        }

        for (i in missiles!!.indices) {
            missiles!![i].update()

            if (collision(missiles!![i], airObject)) {
                missiles!!.removeAt(i)
                airObject.setFlying(false)
                break
            }

            if (missiles!![i].getX() < -100) {
                missiles!!.removeAt(i)
                break
            }
        }
    }

    open fun draw(canvas: Canvas) {
        for (m in missiles!!) m.draw(canvas)
    }

    open fun clear() {
        missiles?.clear()
    }
}