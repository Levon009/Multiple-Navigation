package com.example.jetpack_multiplenavigation.editor

import android.graphics.Rect

abstract class BaseObject {
    protected var mWidth: Int = 0
    protected var mHeight: Int = 0
    protected var mX: Int = 0
    protected var mY: Int = 0
    protected var dx: Int = 0
    protected var dy: Int = 0

    fun setX(mX: Int) {
        this.mX = mX
    }

    fun getX() : Int = this.mX

    fun setY(mY: Int) {
        this.mY = mY
    }

    fun getY() : Int = this.mY

    fun setWidth(mWidth: Int) {
        this.mWidth = mWidth
    }

    fun getWidth() : Int = this.mWidth

    fun setHeight(mHeight: Int) {
        this.mHeight = mHeight
    }

    fun getHeight() : Int = this.mHeight

    fun setDX(dx: Int) {
        this.dx = dx
    }

    fun getDX() : Int = this.dx

    fun setDY(dy: Int) {
        this.dy = dy
    }

    fun getDY() : Int = this.dy

    fun getRectangle() : Rect {
        return Rect(mX, mY, mX + mWidth, mY + mHeight)
    }

    fun getCenterX() : Int {
        return getRectangle().centerY()
    }

    fun getCenterY() : Int {
        return getRectangle().centerY()
    }
}