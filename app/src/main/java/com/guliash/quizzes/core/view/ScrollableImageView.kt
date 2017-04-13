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
    private val transformArray = FloatArray(9)

    private val tempMatrix = Matrix()

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
            transformMatrix.getValues(transformArray)
            val scale = transformArray[Matrix.MSCALE_X]
            val translateX = transformArray[Matrix.MTRANS_X]
            val translateY = transformArray[Matrix.MTRANS_Y]
            val diffX = event.x - prevMoveX
            val diffY = event.y - prevMoveY
            var newTranslateX = translateX + diffX
            var newTranslateY = translateY + diffY

            if (diffX >= 0) {
                if (newTranslateX >= 0) {
                    newTranslateX = 0f
                }
            } else if (newTranslateX + scale * drawable.intrinsicWidth <= width) {
                newTranslateX = translateX
            }

            if (diffY >= 0) {
                if (newTranslateY >= 0f) {
                    newTranslateY = 0f
                }
            } else if (newTranslateY + scale * drawable.intrinsicHeight <= height) {
                newTranslateY = translateY
            }

            transformArray[Matrix.MTRANS_X] = newTranslateX
            transformArray[Matrix.MTRANS_Y] = newTranslateY
            transformMatrix.setValues(transformArray)
            transformMatrix.getValues(transformArray)
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
                Math.max(scaleFactorX, scaleFactorY),
                Math.max(scaleFactorX, scaleFactorY),
                width / 2f,
                height / 2f
        )

        transformMatrix.getValues(transformArray)

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
        if (drawable != null) {
            tempMatrix.set(transformMatrix)
            tempMatrix.postScale(detector.scaleFactor, detector.scaleFactor, detector.focusX, detector.focusY)

            tempMatrix.getValues(transformArray)
            val drawableWidth = drawable.intrinsicWidth
            val drawableHeight = drawable.intrinsicHeight

            val sourceScale = Math.max(width.toFloat() / drawableWidth, height.toFloat() / drawableHeight)
            val scale = transformArray[Matrix.MSCALE_X]

            val canScale = scale * drawableWidth > sourceScale * drawableWidth ||
                    scale * drawableHeight > sourceScale * drawableHeight

            if (canScale) {
                transformMatrix.set(tempMatrix)
            }

            invalidate()
        }
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
