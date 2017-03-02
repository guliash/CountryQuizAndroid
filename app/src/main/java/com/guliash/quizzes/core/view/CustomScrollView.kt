package com.guliash.quizzes.core.view

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet

class CustomScrollView : NestedScrollView {

    var maxHeight: Int = -1

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val newHeightMeasureSpec =
                if (maxHeight == -1) {
                    heightMeasureSpec
                } else {
                    MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)
                }
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }
}