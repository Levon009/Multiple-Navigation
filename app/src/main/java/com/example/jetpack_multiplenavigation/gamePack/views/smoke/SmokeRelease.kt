package com.example.jetpack_multiplenavigation.gamePack.views.smoke

import android.graphics.Canvas
import com.example.jetpack_multiplenavigation.gamePack.views.airObject.AirObject

class SmokeRelease(private val airObject: AirObject) {
    private var smoke: MutableList<Smoke>? = null
    private var smokeStartTime: Long = 0

    init {
        smoke = arrayListOf()
        smokeStartTime = System.nanoTime()
    }

    fun update() {
        val elapsed = (System.nanoTime() - smokeStartTime) / 1000000
        if (elapsed > 120) {
            smoke?.add(
                Smoke(
                    airObject.getX(), airObject.getY()
                )
            )
            smokeStartTime = System.nanoTime()
        }

        for (i in smoke!!.indices) {
            smoke!![i].update()

            if (smoke!![i].getX() <= 10) {
                smoke!!.removeAt(i)
                break
            }
        }
    }

    fun draw(canvas: Canvas) {
        for (s in smoke!!) s.draw(canvas)
    }

    fun clear() {
        smoke?.clear()
    }
}