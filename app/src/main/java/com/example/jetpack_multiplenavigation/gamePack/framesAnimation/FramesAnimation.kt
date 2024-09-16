package com.example.airfighers_jetpack.gamePack.framesAnimation

import android.graphics.Bitmap

class FramesAnimation {
    private var frames: Array<Bitmap?>? = null
    private var startTime: Long = 0
    private var delay: Long = 0
    private var currentFrame: Int = 0
    private var playedOnce = false

    open fun setFrames(frames: Array<Bitmap?>?) {
        this.frames = frames
        currentFrame = 0
        startTime = System.nanoTime()
    }

    open fun getFrame() : Bitmap = frames!![currentFrame]!!

    open fun setCurrentFrame(currentFrame: Int) {
        this.currentFrame = currentFrame
    }

    open fun getCurrentFrame() : Int = this.currentFrame

    open fun setDelay(delay: Long) {
        this.delay = delay
    }

    open fun isPlayedOnce() : Boolean = this.playedOnce

    open fun update() {
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