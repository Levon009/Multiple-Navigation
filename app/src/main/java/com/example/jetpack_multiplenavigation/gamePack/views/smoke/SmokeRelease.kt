package com.example.airfighers_jetpack.gamePack.views.smoke

import android.content.Context
import android.graphics.Canvas
import com.example.airfighers_jetpack.gamePack.views.airObject.AirObject

class SmokeRelease(context: Context, airObject: AirObject) {
    private var context: Context
    private var airObject: AirObject
    private var smoke: MutableList<Smoke>? = null
    private var smokeStartTime: Long = 0

    init {
        this.context = context
        this.airObject = airObject
        smoke = arrayListOf()
        smokeStartTime = System.nanoTime()
    }

    open fun update() {
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

    open fun draw(canvas: Canvas) {
        for (s in smoke!!) s.draw(canvas)
    }

    open fun clear() {
        smoke?.clear()
    }
}