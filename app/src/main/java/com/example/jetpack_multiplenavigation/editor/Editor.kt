package com.example.jetpack_multiplenavigation.editor

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Matrix
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.jetpack_multiplenavigation.R
import kotlin.math.sqrt

class Editor : View {
    private val paint = Paint()
    private val mMatrix: Matrix = Matrix()
    private var bitmap: Bitmap? = null
    private var bitmapF: Bitmap? = null
    private var objectView: ObjectView? = null
    private var pointersCount: Int = 0
    private var clicked: Boolean = false
    private var MODE: Int = 0

    // MOVE
    private val MOVE: Int = 1
    private var dx: Float = 0f
    private var dy: Float = 0f

    // ZOOM
    private val ZOOM: Int = 2
    private var oldScaledValue: Double = 0.0
    private var newScaledValue: Double = 0.0

    // ROTATE
    private val ROTATE: Int = 3
    private var rotationAngle: Double = 0.0
    private var xOldRotation: Float = 0f
    private var xNewRotation: Float = 0f
    private var yOldRotation: Float = 0f
    private var yNewRotation: Float = 0f

    init {
        paint.apply {
            isAntiAlias = true
            color = Color.RED
            strokeWidth = 1.toFloat()
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            pathEffect = DashPathEffect(floatArrayOf(25f, 0f), 0f)
        }

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.distance)
    }

    constructor(context: Context) : super(context) {
        this.background = ContextCompat.getDrawable(context, R.drawable.sl_editor_room)
    }

    private fun space(event: MotionEvent) : Double {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt((x * x) + (y * y)).toDouble()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (!clicked && objectView == null) {
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.distance)
            bitmapF = Bitmap.createBitmap(bitmap!!, 0, 0, bitmap!!.width, bitmap!!.height, mMatrix, true)
            objectView = ObjectView(bitmapF!!, 50, 100, (width / 2 - bitmapF!!.width / 2), bitmapF!!.height)
            objectView?.draw(canvas)
        } else {
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.distance)
            bitmapF = Bitmap.createBitmap(bitmap!!, 0, 0, bitmap!!.width, bitmap!!.height, mMatrix, true)
            objectView = ObjectView(bitmapF!!, objectView!!.getX(), objectView!!.getY(), objectView!!.getWidth(), objectView!!.getHeight())
            objectView?.draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                if (!clicked) {
                    pointersCount = event.pointerCount
                    val startX: Int = event.x.toInt()
                    val startY: Int = event.y.toInt()
                    MODE = when (pointersCount) {
                        1 -> MOVE
                        2 -> ZOOM
                        3, 4 -> ROTATE
                        else -> 0
                    }
                    if (startX >= objectView!!.getX() && startX < (objectView!!.getX() + objectView!!.getWidth())
                        && startY >= objectView!!.getY() && startY < (objectView!!.getY() + objectView!!.getHeight())) {
                        dx = objectView!!.getX() - event.rawX
                        dy = objectView!!.getY() - event.rawY
                        clicked = true
                        invalidate()
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (clicked) {
                    pointersCount = event.pointerCount
                    MODE = when (pointersCount) {
                        1 -> MOVE
                        2 -> ZOOM
                        3, 4 -> ROTATE
                        else -> 0
                    }
                    when (MODE) {
                        MOVE -> {
                            val newX: Float = dx + event.rawX
                            val newY: Float = dy + event.rawY
                            objectView!!.setX(newX.toInt())
                            objectView!!.setY(newY.toInt())
                        }
                        ZOOM -> {
                            if (oldScaledValue == 0.0) {
                                oldScaledValue = space(event)
                            } else {
                                newScaledValue = space(event)

                                if (oldScaledValue > newScaledValue) {
                                    objectView!!.setWidth(objectView!!.getWidth() - 14)
                                    objectView!!.setHeight(objectView!!.getHeight() - 14)
                                } else {
                                    objectView!!.setWidth(objectView!!.getWidth() + 14)
                                    objectView!!.setHeight(objectView!!.getHeight() + 14)
                                }

                                oldScaledValue = 0.0
                                newScaledValue = 0.0
                            }
                        }
                        ROTATE -> {
                            if (xOldRotation == 0f && yOldRotation == 0f) {
                                xOldRotation = event.x
                                yOldRotation = event.y
                            } else {
                                xNewRotation = event.x
                                yNewRotation = event.y

                                if (xOldRotation < xNewRotation && yOldRotation > yNewRotation) {
                                    rotationAngle -= 0.05
                                } else {
                                    rotationAngle += 0.05
                                }

                                mMatrix.postRotate(rotationAngle.toFloat())

                                if (rotationAngle >= 3 || rotationAngle <= -3) {
                                    rotationAngle = 0.0
                                    xOldRotation = 0f
                                    xNewRotation = 0f
                                    yOldRotation = 0f
                                    yNewRotation = 0f
                                }
                            }
                        }
                    }

                    invalidate()
                }
            }
            MotionEvent.ACTION_CANCEL -> {
                if (clicked) {
                    pointersCount = 0
                    MODE = 0
                    rotationAngle = 0.0
                    clicked = false
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                if (clicked) {
                    pointersCount = 0
                    MODE = 0
                    rotationAngle = 0.0
                    clicked = false
                    invalidate()
                }
            }
        }
        return true
    }
}