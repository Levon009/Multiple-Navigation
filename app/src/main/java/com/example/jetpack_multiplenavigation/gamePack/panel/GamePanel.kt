package com.example.airfighers_jetpack.gamePack.panel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.airfighers_jetpack.gamePack.mainThread.ThreadManager
import com.example.airfighers_jetpack.gamePack.views.airObject.AirObject
import com.example.airfighers_jetpack.gamePack.views.background.BackgroundRelease
import com.example.airfighers_jetpack.gamePack.views.bomb.BombRelease
import com.example.airfighers_jetpack.gamePack.views.coin.CoinRelease
import com.example.airfighers_jetpack.gamePack.views.explosion.ExplosionRelease
import com.example.airfighers_jetpack.gamePack.views.missile.MissileRelease
import com.example.airfighers_jetpack.gamePack.views.missile.fireMissile.FireMissileRelease
import com.example.airfighers_jetpack.gamePack.views.smoke.SmokeRelease
import com.example.jetpack_multiplenavigation.R

class GamePanel : SurfaceView, SurfaceHolder.Callback {
    private var threadManager: ThreadManager? = null
    private var backgroundRelease: BackgroundRelease? = null
    private var airObject: AirObject? = null
    private var smokeRelease: SmokeRelease? = null
    private var explosionRelease: ExplosionRelease? = null
    private var missileRelease: MissileRelease? = null
    private var fireMissileRelease: FireMissileRelease? = null
    private var coinRelease: CoinRelease? = null
    private var bombRelease: BombRelease? = null

    private var newGameCreated = false
    private var started = false
    private var reset = false
    private var disappear = false
    private var startReset: Long = 0
    private var best: Int = 0
    private var coin: Int = 0

    constructor(context: Context) : super(context) {
        holder.addCallback(this)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        holder.addCallback(this)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        airObject = AirObject(BitmapFactory.decodeResource(resources, R.drawable.helicopter1), 65, 35, 3)
        backgroundRelease = BackgroundRelease(context, airObject!!)
        smokeRelease = SmokeRelease(context, airObject!!)
        missileRelease = MissileRelease(context, airObject!!)
        coinRelease = CoinRelease(context, airObject!!)
        bombRelease = BombRelease(context, airObject!!)
        fireMissileRelease = FireMissileRelease(context, airObject!!, missileRelease!!, bombRelease!!)
        threadManager = ThreadManager(holder, this)
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        threadManager?.destroy()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!airObject!!.isFlying() && newGameCreated && reset) {
                     airObject?.setFlying(true)
                } else if (airObject!!.isFlying()) {
                    if (!started) started = true
                    reset = false
                    airObject?.setUp(true)
                }
                return true
            }

            MotionEvent.ACTION_UP -> {
                airObject?.setUp(false)
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    open fun update() {
        if (airObject!!.isFlying())
            gameUpdate()
        else
            resetUpdate()
    }

    private fun updateBorders() {
        if (airObject!!.getY() > HEIGHT - 50 || airObject!!.getY() < 0) {
            reset = false
            airObject!!.setFlying(false)
        }
    }

    private fun gameUpdate() {
        backgroundRelease?.update()
        airObject?.update()
        smokeRelease?.update()
        missileRelease?.update()
        fireMissileRelease?.update()
        bombRelease?.update()
        coinRelease?.update()
        coin = airObject?.getCoins()!!
        updateBorders()
        changeBackground()
    }

    private fun resetUpdate() {
        airObject?.resetDY()

        if (!reset) {
            reset = true
            disappear = true
            newGameCreated = false
            startReset = System.nanoTime()
            explosionRelease = ExplosionRelease(context, airObject!!)
        }

        explosionRelease?.update()
        val resetElapsed = (System.nanoTime() - startReset) / 1000000
        if (resetElapsed > 2500 && !newGameCreated)
            newGame()
    }

    private fun changeBackground() {
        if (airObject!!.getScore() * 3 > 250)
            backgroundRelease?.changeBackground()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        val scaleFactorX = width / (WIDTH * 1.0f)
        val scaleFactorY = height / (HEIGHT + 0.5f)
        val savedState = canvas.save()
        canvas.scale(scaleFactorX, scaleFactorY)

        drawViews(canvas)
        drawText(canvas)
        canvas.restoreToCount(savedState)
    }

    private fun drawViews(canvas: Canvas) {
        backgroundRelease?.draw(canvas)
        if (!disappear) airObject?.draw(canvas)
        smokeRelease?.draw(canvas)
        missileRelease?.draw(canvas)
        fireMissileRelease?.draw(canvas)
        bombRelease?.draw(canvas)
        coinRelease?.draw(canvas)
        if (started) explosionRelease?.draw(canvas)
    }

    private fun drawText(canvas: Canvas) {
        val paint = Paint()
        paint.textSize = 30f
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        canvas.drawText("DISTANCE: ${airObject!!.getScore() * 3}", 10f, (HEIGHT - 10).toFloat(), paint)
        canvas.drawText("BEST: $best", (WIDTH - 215).toFloat(), (HEIGHT - 10).toFloat(), paint)
        canvas.drawText("COIN: $coin", (WIDTH - 215).toFloat(), 40f, paint)

        if (!airObject!!.isFlying() && newGameCreated && reset) {
            paint.textSize = 50f
            canvas.drawText("PRESS TO START", (WIDTH / 2 - 50).toFloat(), (HEIGHT / 2).toFloat(), paint)
            paint.textSize = 20f
            canvas.drawText("PRESS AND HOLD TO GO UP", (WIDTH / 2 - 50).toFloat(), (HEIGHT / 2 + 20).toFloat(), paint)
            canvas.drawText("RELEASE TO GO DOWN", (WIDTH / 2 - 50).toFloat(), (HEIGHT / 2 + 40).toFloat(), paint)
        }
    }

    private fun newGame() {
        newGameCreated = true
        disappear = false

        airObject?.setY(HEIGHT / 2)
        airObject?.resetDY()
        if (best < airObject!!.getScore())
            best = airObject!!.getScore()
        airObject?.resetScore()

        smokeRelease?.clear()
        missileRelease?.clear()
        fireMissileRelease?.clear()
        airObject?.resetCoins()
        bombRelease?.clear()
    }

    companion object {
        const val WIDTH = 856
        const val HEIGHT = 480
        const val MOVE_SPEED = -5
    }
}