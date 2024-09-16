package com.example.airfighers_jetpack.gamePack.views.coin

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import com.example.airfighers_jetpack.gamePack.baseObject.ObjectView
import com.example.airfighers_jetpack.gamePack.panel.GamePanel
import com.example.airfighers_jetpack.gamePack.views.airObject.AirObject
import com.example.jetpack_multiplenavigation.R
import java.util.Random

class CoinRelease(context: Context, airObject: AirObject) {
    private val random = Random()
    private var context: Context
    private var airObject: AirObject
    private var coins: MutableList<Coin>? = null
    private var startTime: Long = 0

    init {
        this.context = context
        this.airObject = airObject
        coins = arrayListOf()
        startTime = System.nanoTime()
    }

    private fun collision(a: ObjectView, b: ObjectView) : Boolean = Rect.intersects(a.getRectangle(), b.getRectangle())

    open fun update() {
        val elapsed = (System.nanoTime() - startTime) / 1000000
        if (elapsed > (1000 - airObject.getScore() / 4)) {
            coins?.add(
                Coin(BitmapFactory.decodeResource(context.resources, R.drawable.coins),
                    (random.nextDouble() * (GamePanel.WIDTH / 2 * 3 + GamePanel.WIDTH)).toInt(),
                    0, 29, 38, 3

                )
            )

            startTime = System.nanoTime()
        }

        for (i in coins!!.indices) {
            coins!![i].update()

            if (collision(coins!![i], airObject)) {
                coins!!.removeAt(i)
                airObject.addCoins()
                break
            }

            if (coins!![i].getY() > GamePanel.HEIGHT - 40) {
                coins!!.removeAt(i)
                break
            }
        }
    }

    open fun draw(canvas: Canvas) {
        for (c in coins!!) c.draw(canvas)
    }
}