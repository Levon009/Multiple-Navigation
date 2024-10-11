package com.example.jetpack_multiplenavigation.gamePack.mainThread

import android.graphics.Canvas
import android.view.SurfaceHolder
import com.example.jetpack_multiplenavigation.gamePack.panel.GamePanel
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

class MainThread(
    private val surfaceHolder: SurfaceHolder,
    private val gamePanel: GamePanel
) {
    companion object {
        private const val FPS = 30
        private var canvas: Canvas? = null
    }

    private var job: CompletableJob? = null
    private var running = false

    init {
        initializeJob()
    }

    fun setRunning(running: Boolean) {
        this.running = running
    }

    private fun initializeJob() {
        job = Job()
        job?.invokeOnCompletion { it ->
            it?.message.let {
                var msg = it
                if (msg.isNullOrBlank()) {
                    msg = "Unknown error. Result: - $msg"
                }
            }
        }
    }

    fun cancelJob() {
        if (job!!.isActive || job!!.isCompleted) {
            job?.cancel(CancellationException("Cancel job."))
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    fun run() {
        CoroutineScope(Dispatchers.IO + job!!).launch {
            val targetTime: Long = (1000 / FPS).toLong()
            var startTime: Long
            var waitTime: Long
            var timeMillis: Long
            var framesCount = 0

            while (running) {
                startTime = System.nanoTime()
                canvas = null

                try {
                    canvas = surfaceHolder.lockCanvas()
                    synchronized(surfaceHolder) {
                        gamePanel.update()
                        gamePanel.draw(canvas!!)
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                } finally {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                timeMillis = (System.nanoTime() - startTime) / 1000000
                waitTime = targetTime - timeMillis

                delay(waitTime)

                framesCount++

                if (framesCount == FPS)
                    framesCount = 0
            }
        }
    }
}