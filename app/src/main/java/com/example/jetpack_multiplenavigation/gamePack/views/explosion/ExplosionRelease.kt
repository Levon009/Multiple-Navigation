package com.example.jetpack_multiplenavigation.gamePack.views.explosion

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.airfighers_jetpack.gamePack.views.explosion.Explosion
import com.example.jetpack_multiplenavigation.gamePack.views.airObject.AirObject
import com.example.jetpack_multiplenavigation.R

class ExplosionRelease(
    context: Context,
    airObject: AirObject
) {
    private var explosion: Explosion = Explosion(BitmapFactory.decodeResource(context.resources, R.drawable.explosion1),
        airObject.getX(), airObject.getY() - 10, 100, 100, 25)

    fun update() {
        explosion.update()
    }

    fun draw(canvas: Canvas) {
        explosion.draw(canvas)
    }
}