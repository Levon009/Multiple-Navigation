package com.example.jetpack_multiplenavigation.gamePack.framesAnimation

import android.graphics.Bitmap

class FramesAnimation {
    private var frames: Array<Bitmap?>? = null
    private var startTime: Long = 0
    private var delay: Long = 0
    private var currentFrame: Int = 0
    private var playedOnce = false

    fun setFrames(frames: Array<Bitmap?>?) {
        this.frames = frames
        currentFrame = 0
        startTime = System.nanoTime()
    }

    fun getFrame() : Bitmap = frames!![currentFrame]!!

    fun setCurrentFrame(currentFrame: Int) {
        this.currentFrame = currentFrame
    }

    fun getCurrentFrame() : Int = this.currentFrame

    fun setDelay(delay: Long) {
        this.delay = delay
    }

    fun isPlayedOnce() : Boolean = this.playedOnce

    fun update() {
        val elapsed: Long = (System.nanoTime() - startTime) / 1000000
        if (elapsed > delay) {
            currentFrame++
            startTime = System.nanoTime()
        }

        if (currentFrame == frames!!.size) {
            currentFrame = 0
            playedOnce = true
        }
    }
}