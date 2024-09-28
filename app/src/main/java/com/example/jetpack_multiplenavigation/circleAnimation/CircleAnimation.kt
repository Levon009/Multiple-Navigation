package com.example.jetpack_multiplenavigation.circleAnimation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.compose.runtime.mutableIntStateOf
import com.example.jetpack_multiplenavigation.R
import kotlin.math.cos
import kotlin.math.sin

class CircleAnimation : View {
    private val paint = Paint()
    private var bitmap: Bitmap? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var centerX: Int = 0
    private var centerY: Int = 0
    private var bcx: Int = 0
    private var bcy: Int = 0
    private var newBallX: Float = 0f
    private var newBallY: Float = 0f
    private var radius: Int = 0
    private val angle = mutableIntStateOf(0)

    constructor(context: Context) : super(context) {
        this.setBackgroundColor(Color.TRANSPARENT)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.setBackgroundColor(Color.TRANSPARENT)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        this.mWidth = MeasureSpec.getSize(widthMeasureSpec)
        this.mHeight = MeasureSpec.getSize(heightMeasureSpec)
        super.onMeasure(mWidth, mHeight)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        centerX = mWidth / 2
        centerY = mHeight / 2
        radius = mWidth / 6

        applyPaint()

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.fire_ic)
        bcx = (mWidth - bitmap!!.width) shr 1
        bcy = (mHeight - bitmap!!.height) shr 1
        canvas.drawBitmap(bitmap!!, bcx.toFloat(), bcy.toFloat(), paint)
        //canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat(), paint)

        newBallX = ((centerX + radius * sin(Math.toRadians(angle.intValue.toDouble()))).toFloat())
        newBallY = ((centerY + radius * cos(Math.toRadians(angle.intValue.toDouble()))).toFloat())
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        canvas.drawCircle(newBallX, newBallY, 15.toFloat(), paint)

        angle.value -= 7
        if (angle.value == 0) angle.value = 360
        invalidate()
    }

    private fun applyPaint() {
        paint.apply {
            isAntiAlias = true
            strokeWidth = 5.toFloat()
            color = Color.LTGRAY
            style = Paint.Style.STROKE
        }
    }
}