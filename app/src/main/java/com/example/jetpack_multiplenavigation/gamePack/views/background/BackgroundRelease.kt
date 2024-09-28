package com.example.airfighers_jetpack.gamePack.views.background

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.example.airfighers_jetpack.gamePack.views.airObject.AirObject
import com.example.jetpack_multiplenavigation.R

open class BackgroundRelease(context: Context, airObject: AirObject) {
    private var context: Context
    private var background: Background
    private var airObject: AirObject
    private var change = false

    init {
        this.context = context
        this.airObject = airObject
        this.background = Background(BitmapFactory.decodeResource(context.resources, R.drawable.forest2))
    }

    open fun changeBackground() {
        if (!change) {
            this.background = Background(BitmapFactory.decodeResource(context.resources, R.drawable.desert1))
            change = true
        }
    }

    open fun update() {
        background.update()
    }

    open fun draw(canvas: Canvas) {
        background.draw(canvas)
    }
}