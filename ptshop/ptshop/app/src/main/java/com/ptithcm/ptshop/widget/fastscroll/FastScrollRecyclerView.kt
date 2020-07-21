package com.ptithcm.ptshop.widget.fastscroll

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.ptshop.constant.KEY_EMPTY
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.floor

class FastScrollRecyclerView : RecyclerView {

    companion object {
        private var indWidth = 20
        private var indHeight = 16
        private const val indDefault = 6
    }

    private var ctx: Context? = null
    private var setupThings = false
    var scaledWidth: Float = 0.toFloat()
    var scaledHeight: Float = 0.toFloat()
     var sections: ArrayList<String?> = arrayListOf()
    var sx: Float = 0.toFloat()
    var sy: Float = 0.toFloat()
    var section: String
    var showLetter = false
    private var listHandler: Handler? = null

    constructor(context: Context) : super(context) {
        ctx = context
        section = KEY_EMPTY
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        ctx = context
        section = KEY_EMPTY
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        ctx = context
        section = KEY_EMPTY
    }

    override fun onDraw(c: Canvas?) {
        if (!setupThings)
            setupThings()
        super.onDraw(c)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x ?: 0f
        val y = event?.y ?: 0f

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size)
                    return super.onTouchEvent(event)
                else {
                    // We touched the index bar
                    val yy = y - this.paddingTop.toFloat() - paddingBottom.toFloat() - sy
                    var currentPosition = floor((yy / scaledHeight).toDouble()).toInt() + indDefault
                    if (currentPosition < 0) currentPosition = 0
                    if (currentPosition >= sections.size) currentPosition = sections.size - 1
                    section = sections[currentPosition] ?: KEY_EMPTY
                    showLetter = true
                    var positionInData = 0
                    if ((adapter as FastScrollRecyclerViewInterface).mapIndex.containsKey(section.toUpperCase(Locale.getDefault()))) {
                        positionInData = (adapter as FastScrollRecyclerViewInterface).mapIndex[section.toUpperCase(Locale.getDefault())] ?: 0
                    }
                    this.scrollToPosition(positionInData)
                    this.scrollTo(0, 0)
                    (this.layoutManager as? LinearLayoutManager)?.scrollToPosition(positionInData)
                    (this.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(positionInData, 0)
                    this@FastScrollRecyclerView.invalidate()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (!showLetter && (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size))
                    return super.onTouchEvent(event)
                else {
                    val yy = y - sy
                    var currentPosition = floor((yy / scaledHeight).toDouble()).toInt()
                    if (currentPosition < 0) currentPosition = 0
                    if (currentPosition >= sections.size) currentPosition = sections.size - 1
                    section = sections[currentPosition] ?: KEY_EMPTY
                    showLetter = true
                    var positionInData = 0
                    if ((adapter as FastScrollRecyclerViewInterface).mapIndex.containsKey(section.toUpperCase(Locale.getDefault()))) {
                        positionInData = (adapter as FastScrollRecyclerViewInterface).mapIndex[section.toUpperCase(Locale.getDefault())] ?: 0
                    }
                    this.scrollToPosition(positionInData)
                    this.scrollTo(0, 0)
                    (this.layoutManager as? LinearLayoutManager)?.scrollToPosition(positionInData)
                    (this.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(positionInData, 0)
                    this@FastScrollRecyclerView.invalidate()

                }

            }
            MotionEvent.ACTION_UP -> {
                listHandler = ListHandler()
                listHandler?.sendEmptyMessageDelayed(0, 100)
                return if (x < sx - scaledWidth || y < sy || y > sy + scaledHeight * sections.size)
                    super.onTouchEvent(event)
                else
                    true
            }
        }
        return true
    }

    private fun setupThings() {
        //create az text data
        val sectionSet = (adapter as FastScrollRecyclerViewInterface).mapIndex.keys
        val listSection = ArrayList(sectionSet)
        listSection.sort()
        listSection.forEach { sections.add(it) }

        scaledWidth = indWidth * (ctx?.resources?.displayMetrics?.density ?: 0f)
        scaledHeight = indHeight * (ctx?.resources?.displayMetrics?.density ?: 0f)
        sx = this.width.toFloat() - this.paddingRight.toFloat() - (1.2 * scaledWidth).toFloat()
        sy = ((this.height - scaledHeight * sections.size) / 2.0).toFloat()
        setupThings = true
    }

    inner class ListHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            showLetter = false
            this@FastScrollRecyclerView.invalidate()
        }
    }

}