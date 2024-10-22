package com.example.jetpack_multiplenavigation.circleTouchFlag

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CircleTouch : View {
    private val paintCircle = Paint()
    private val paintBG = Paint()
    private val circles = mutableSetOf<CircleArea>()
    private var flag = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private fun getTouchCircle(xTouch: Int, yTouch: Int) : CircleArea? {
        var touchCircle: CircleArea? = null

        circles.parallelStream().filter { c ->
            ((c.centerX - xTouch) * (c.centerX - xTouch)) + ((c.centerY - yTouch) * (c.centerY - yTouch))<= (c.radius * c.radius)
        }.parallel().forEach {
            touchCircle = it
        }

        return touchCircle
    }

    private fun obtainTouchCircle(xTouch: Int, yTouch: Int) : CircleArea {
        var touchCircleArea = getTouchCircle(xTouch, yTouch)

        if (touchCircleArea == null) touchCircleArea = CircleArea(xTouch, yTouch)
        if (circles.size < MAX_CIRCLES) circles.add(touchCircleArea)

        return touchCircleArea
    }

    @SuppressLint("CanvasSize")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = canvas.width
        val height = canvas.height

        paintBG.color = Color.WHITE
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBG)

        for (c in circles) {
            when {
                c.centerX < width / 3f -> {
                    paintBG.color = Color.RED
                    canvas.drawRect(0f, 0f, (width / 3f), height.toFloat(), paintBG)
                }
                c.centerX < (width * 2 / 3f) -> {
                    paintBG.color = Color.BLUE
                    canvas.drawRect((width / 3f), 0f, (width * 2 / 3f), height.toFloat(), paintBG)
                }
                else -> {
                    paintBG.color = Color.YELLOW
                    canvas.drawRect((width * 2 / 3f), 0f, width.toFloat(), height.toFloat(), paintBG)
                }
            }

            canvas.drawCircle(c.centerX.toFloat(), c.centerY.toFloat(), c.radius.toFloat(), paintCircle)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var circleArea: CircleArea
        var xTouch: Int
        var yTouch: Int
        var actionIndex = event.actionIndex

        when(event.action) {
            MotionEvent.ACTION_DOWN -> if (!flag) {
                xTouch = event.getX(0).toInt()
                yTouch = event.getY(0).toInt()
                circleArea = obtainTouchCircle(xTouch, yTouch)
                circleArea.centerX = xTouch
                circleArea.centerY = yTouch
                paintCircle.alpha = 255

                flag = true
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                val pointerCount = event.pointerCount
                actionIndex = 0
                while (actionIndex < pointerCount) {
                    xTouch = event.getX(actionIndex).toInt()
                    yTouch = event.getY(actionIndex).toInt()

                    circleArea = obtainTouchCircle(xTouch, yTouch)
                    circleArea.centerX = xTouch
                    circleArea.centerY = yTouch
                    actionIndex++
                }

                flag = true
                invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> if (flag) {
                paintCircle.alpha = 5
                flag = false
                invalidate()
            }
        }

        return true
    }

    companion object {
        const val MAX_CIRCLES = 1
    }

    class CircleArea(
        var centerX: Int,
        var centerY: Int,
        var radius: Int = 133
    )
}