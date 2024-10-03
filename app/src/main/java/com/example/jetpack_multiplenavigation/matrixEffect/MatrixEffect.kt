package com.example.jetpack_multiplenavigation.matrixEffect

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.Random

class MatrixEffect : View {
    private val random = Random()
    private val paintInitBg = Paint()
    private val paintBackground = Paint()
    private val paintBitmap = Paint()
    private val paintText = Paint()
    private var mCanvas: Canvas? = null
    private var bitmap: Bitmap? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var columnSize: Int = 0
    private val fontSize: Int = 15
    private var chars = "+_))(*&^%#@!!@#$".toCharArray()
    private lateinit var txtPositionByColumn: IntArray

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        paintText.apply {
            isAntiAlias = true
            color = Color.argb(255, 0, 255, 0)
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
        }

        paintBitmap.apply {
            isAntiAlias = true
            color = Color.BLACK
        }

        paintInitBg.apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            alpha = 255
        }

        paintBackground.apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            alpha = 5
        }
    }

    private fun drawText() {
        for (i in txtPositionByColumn.indices) {
            mCanvas?.drawText(
                "${chars[random.nextInt(chars.size)]}",
                (i * fontSize).toFloat(),
                (txtPositionByColumn[i] * fontSize).toFloat(),
                paintText
            )
            if (txtPositionByColumn[i] * fontSize > mHeight && Math.random() > 0.95 && random.nextInt(10) > 0.777) {
                    txtPositionByColumn[i] = 0
            }
            txtPositionByColumn[i]++
        }
    }

    private fun drawCanvas() {
        mCanvas?.drawRect(0f, 0f, mWidth.toFloat(), mHeight.toFloat(), paintBackground)
        drawText()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(bitmap!!)
        mCanvas?.drawRect(0f, 0f, mWidth.toFloat(), mHeight.toFloat(), paintInitBg)

        columnSize = mWidth / fontSize
        txtPositionByColumn = IntArray(columnSize + 1)
        txtPositionByColumn.forEachIndexed { index, i ->
            txtPositionByColumn[index] = random.nextInt(mHeight / 2)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap!!, 0f, 0f, paintBitmap)
        drawCanvas()
        invalidate()
    }
}