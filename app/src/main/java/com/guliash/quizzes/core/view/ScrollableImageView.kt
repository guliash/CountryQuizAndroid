package com.guliash.quizzes.core.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView

class ScrollableImageView : ImageView, ScaleGestureDetector.OnScaleGestureListener {

    private var prevMoveX = 0f
    private var prevMoveY = 0f

    private var prevPointerCount = 1

    private val transformMatrix = Matrix()

    private lateinit var scaleDetector: ScaleGestureDetector

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleArr: Int) : super(context, attrs, defStyleArr) {
        init()
    }

    fun init() {
        scaleDetector = ScaleGestureDetector(context, this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        val action = event.action

        if (action == MotionEvent.ACTION_DOWN) {
            prevPointerCount = event.pointerCount
            prevMoveX = event.x
            prevMoveY = event.y
        }

        if (action == MotionEvent.ACTION_UP) {
            prevPointerCount = 1
            prevMoveX = 0f
            prevMoveY = 0f
        }

        if (action == MotionEvent.ACTION_MOVE && prevPointerCount != event.pointerCount) {
            prevMoveX = event.x
            prevMoveY = event.y
        }

        if (action == MotionEvent.ACTION_MOVE && drawable != null && event.pointerCount == 1) {
            transformMatrix.postTranslate(event.x - prevMoveX, event.y - prevMoveY)
            invalidate()
        }

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            prevMoveX = event.x
            prevMoveY = event.y
            prevPointerCount = event.pointerCount
            return true
        }

        return super.onTouchEvent(event)
    }

    override fun setImageDrawable(drawable: Drawable?) {
        if (drawable == null) {
            super.setImageDrawable(null)
            return
        }
        val drawableWidth = drawable.intrinsicWidth
        val drawableHeight = drawable.intrinsicHeight

        val scaleFactorX = width.toFloat() / drawableWidth
        val scaleFactorY = height.toFloat() / drawableHeight

        transformMatrix.setTranslate((width - drawableWidth) / 2f, (height - drawableHeight) / 2f)

        transformMatrix.postScale(
                Math.min(scaleFactorX, scaleFactorY),
                Math.min(scaleFactorX, scaleFactorY),
                width / 2f,
                height / 2f
        )

        super.setImageDrawable(drawable)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        if (drawable != null) {
            canvas.matrix = transformMatrix
            drawable.draw(canvas)
        }
        canvas.restore()
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean = true

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        transformMatrix.postScale(detector.scaleFactor, detector.scaleFactor, detector.focusX, detector.focusY)
        invalidate()
        return true

//        just for me (learned how to apply scale relative to point manually:)
//        val transX = matrixValues[Matrix.MTRANS_X]
//        val transY = matrixValues[Matrix.MTRANS_Y]
//        transformMatrix.postTranslate(scaleDetector.focusX - transX, scaleDetector.focusY - transY)
//        transformMatrix.preScale(detector.scaleFactor, detector.scaleFactor)
//        transformMatrix.postTranslate(-detector.scaleFactor * (scaleDetector.focusX - transX), -detector.scaleFactor * (scaleDetector.focusY - transY))
    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {
    }
}
