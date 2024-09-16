package com.example.airfighers_jetpack.gamePack.views.explosion

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.airfighers_jetpack.gamePack.views.airObject.AirObject
import com.example.jetpack_multiplenavigation.R

class ExplosionRelease(context: Context, airObject: AirObject) {
    private var context: Context
    private var airObject: AirObject
    private var explosion: Explosion

    init {
        this.context = context
        this.airObject = airObject
        this.explosion = Explosion(BitmapFactory.decodeResource(context.resources, R.drawable.explosion1),
            airObject.getX(), airObject.getY() - 10, 100, 100, 25)
    }

    open fun update() {
        explosion.update()
    }

    open fun draw(canvas: Canvas) {
        explosion.draw(canvas)
    }
}