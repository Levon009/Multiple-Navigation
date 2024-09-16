package com.example.jetpack_multiplenavigation.bindedServiceTimer

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TimerService : Service() {
    private val _timerChannel = Channel<Int>()
    private var timerBinder: TimerBinder? = null
    private val timerThread = TimerThread()

    private var counter = 0
    private var isPaused = false

    override fun onBind(intent: Intent?): IBinder? {
        timerBinder = TimerBinder()
        return timerBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        runTimer()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun runTimer() {
        timerThread.start()
    }

    private fun pauseTimer() {
        isPaused = !isPaused
    }

    private fun restartTimer() {
        counter = 0
    }

    override fun onDestroy() {
        Log.e("Service state:", "DESTROY")
        super.onDestroy()
    }

    inner class TimerBinder : Binder() {
        val timerChannel = _timerChannel.receiveAsFlow()

        fun start() {
            runTimer()
        }

        fun pause() {
            pauseTimer()
        }

        fun restart() {
            restartTimer()
        }
    }

    inner class TimerThread : Thread() {
        override fun run() {
            super.run()
            while (true) {
                counter++
                while (isPaused);
                CoroutineScope(Dispatchers.IO).launch {
                    _timerChannel.send(counter)
                }
                sleep(1000)
            }
        }
    }
}