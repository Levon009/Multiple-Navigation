package com.example.jetpack_multiplenavigation.gamePack.baseObject

import android.graphics.Rect

abstract class ObjectView {
    protected var mWidth: Int = 0
    protected var mHeight: Int = 0
    protected var mX: Int = 0
    protected var mY: Int = 0
    protected var dx: Int = 0
    protected var dy: Int = 0

    open fun setWidth(mWidth: Int) {
        this.mWidth = mWidth
    }

    open fun getWidth() : Int = this.mWidth

    open fun setHeight(mHeight: Int) {
        this.mHeight = mHeight
    }

    open fun getHeight() : Int = this.mHeight

    open fun setX(mX: Int) {
        this.mX = mX
    }

    open fun getX() : Int = this.mX

    open fun setY(mY: Int) {
        this.mY = mY
    }

    open fun getY() : Int = this.mY

    open fun setDX(dx: Int) {
        this.dx = dx
    }

    open fun getDX() : Int = this.dx

    open fun setDY(dy: Int) {
        this.dy = dy
    }

    open fun getDY() : Int = this.dy

    open fun getRectangle() : Rect = Rect(mX, mY, mX + mWidth, mY + mHeight)

    open fun getCenterX() : Int = getRectangle().centerX()

    open fun getCenterY() : Int = getRectangle().centerY()
}