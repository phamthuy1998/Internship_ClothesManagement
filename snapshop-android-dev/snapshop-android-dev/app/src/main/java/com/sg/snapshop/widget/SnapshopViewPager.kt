package com.sg.snapshop.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class SnapshopViewPager : ViewPager {

    var isDisable = false

    constructor(context: Context): super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        try {
            return !isDisable && super.onInterceptTouchEvent(ev)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }

        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        try {
            return !isDisable && super.onTouchEvent(ev)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }

        return false
    }

    fun isDisable(isDisable : Boolean){
        this.isDisable = isDisable
    }
}