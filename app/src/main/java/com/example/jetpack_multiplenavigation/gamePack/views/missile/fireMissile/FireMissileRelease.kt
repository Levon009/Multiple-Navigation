package com.example.jetpack_multiplenavigation.gamePack.views.missile.fireMissile

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import com.example.jetpack_multiplenavigation.gamePack.baseObject.ObjectView
import com.example.jetpack_multiplenavigation.gamePack.panel.GamePanel
import com.example.jetpack_multiplenavigation.gamePack.views.airObject.AirObject
import com.example.jetpack_multiplenavigation.gamePack.views.bomb.BombRelease
import com.example.jetpack_multiplenavigation.gamePack.views.missile.MissileRelease
import com.example.jetpack_multiplenavigation.R

class FireMissileRelease(
    private val context: Context,
    private val airObject: AirObject,
    missileRelease: MissileRelease,
    bombRelease: BombRelease
) {
    private var fireMissiles: MutableList<FireMissile>? = null
    private var missileRelease: MissileRelease? = null
    private var bombRelease: BombRelease? = null
    private var startTime: Long = 0
    private var missilesCollision = false
    private var bombsCollision = false

    init {
        this.fireMissiles = arrayListOf()
        this.missileRelease = missileRelease
        this.bombRelease = bombRelease
        startTime = System.nanoTime()
    }

    private fun collision(a: ObjectView, b: ObjectView) : Boolean = Rect.intersects(a.getRectangle(), b.getRectangle())

    fun update() {
        val elapsed = (System.nanoTime() - startTime) / 1000000
        if (elapsed > (2100 - airObject.getScore() / 4)) {
            fireMissiles?.add(
                FireMissile(BitmapFactory.decodeResource(context.resources, R.drawable.missile4),
                    airObject.getX(), airObject.getY(),
                    45, 15, 13)
            )
            missilesCollision = false
            bombsCollision = false
            startTime = System.nanoTime()
        }

        implementMissilesCollision()
    }

    private fun implementMissilesCollision() {
        for (i in fireMissiles!!.indices) {
            fireMissiles!![i].update()

            for (k in missileRelease!!.getMissiles().indices) {
                if (collision(fireMissiles!![i], missileRelease!!.getMissiles()[k])) {
                    fireMissiles!!.removeAt(i)
                    missileRelease!!.getMissiles().removeAt(k)
                    missilesCollision = true
                    break
                }
            }
            if (missilesCollision) break

            for (j in bombRelease!!.getBombs().indices) {
                if (collision(fireMissiles!![i], bombRelease!!.getBombs()[j])) {
                    fireMissiles!!.removeAt(i)
                    bombRelease!!.getBombs().removeAt(j)
                    bombsCollision = true
                    break
                }
            }
            if (bombsCollision) break

            if (fireMissiles!![i].getX() > GamePanel.WIDTH + 10){
                fireMissiles!!.removeAt(i)
                break
            }
        }
    }

    fun draw(canvas: Canvas) {
        for (fm in fireMissiles!!) fm.draw(canvas)
    }

    fun clear() {
        fireMissiles?.clear()
    }
}