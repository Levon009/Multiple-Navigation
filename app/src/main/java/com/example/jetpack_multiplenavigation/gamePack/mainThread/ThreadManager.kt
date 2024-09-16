package com.example.airfighers_jetpack.gamePack.mainThread

import android.view.SurfaceHolder
import com.example.airfighers_jetpack.gamePack.panel.GamePanel

class ThreadManager(surfaceHolder: SurfaceHolder, gamePanel: GamePanel) {
    private var surfaceHolder: SurfaceHolder
    private var gamePanel: GamePanel
    private var mainThread: MainThread? = null

    init {
        this.surfaceHolder = surfaceHolder
        this.gamePanel = gamePanel
        this.mainThread = MainThread(surfaceHolder, gamePanel)
        mainThread?.setRunning(true)
        mainThread?.run()
    }

    open fun destroy() {
        var retry = true
        var counter = 0

        while (retry && counter < 1000) {
            counter++

            try {
                mainThread?.setRunning(false)
                mainThread?.cancelJob()
                mainThread = null
                retry = false
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}