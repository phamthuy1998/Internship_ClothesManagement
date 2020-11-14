package com.ptithcm.ptshop.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.ptithcm.ptshop.R

class RoundImageView : AppCompatImageView {

    companion object {
        const val CORNER_NONE = 0
        const val CORNER_TOP_LEFT = 1
        const val CORNER_TOP_RIGHT = 2
        const val CORNER_BOTTOM_RIGHT = 4
        const val CORNER_BOTTOM_LEFT = 8
        const val CORNER_ALL = 15
    }

    private val cornerRect = RectF()
    private val path = Path()
    private var cornerRadius: Int = 0
    private var roundedCorners: Int = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView)
        cornerRadius = a.getDimensionPixelSize(R.styleable.RoundImageView_cornerRadius, 0)
        roundedCorners = a.getInt(R.styleable.RoundImageView_roundedCorners,
            CORNER_NONE
        )
        a.recycle()
    }

    fun setCornerRadius(radius: Int) {
        cornerRadius = radius
        setPath()
        invalidate()
    }

    fun getRadius(): Int {
        return cornerRadius
    }

    fun setRoundedCorners(corners: Int) {
        roundedCorners = corners
        setPath()
        invalidate()
    }

    fun getRoundedCorners(): Int {
        return roundedCorners
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setPath()
    }

    override fun onDraw(canvas: Canvas) {
        if (!path.isEmpty) {
            canvas.clipPath(path)
        }
        super.onDraw(canvas)
    }

    private fun setPath() {
        path.rewind()

        if (cornerRadius >= 1f && roundedCorners != CORNER_NONE) {
            val width = width
            val height = height
            val twoRadius = (cornerRadius * 2).toFloat()
            cornerRect.set(
                (-cornerRadius).toFloat(),
                (-cornerRadius).toFloat(),
                cornerRadius.toFloat(),
                cornerRadius.toFloat()
            )

            if (isRounded(CORNER_TOP_LEFT)) {
                cornerRect.offsetTo(0f, 0f)
                path.arcTo(cornerRect, 180f, 90f)
            } else {
                path.moveTo(0f, 0f)
            }

            if (isRounded(CORNER_TOP_RIGHT)) {
                cornerRect.offsetTo(width - twoRadius, 0f)
                path.arcTo(cornerRect, 270f, 90f)
            } else {
                path.lineTo(width.toFloat(), 0f)
            }

            if (isRounded(CORNER_BOTTOM_RIGHT)) {
                cornerRect.offsetTo(width - twoRadius, height - twoRadius)
                path.arcTo(cornerRect, 0f, 90f)
            } else {
                path.lineTo(width.toFloat(), height.toFloat())
            }

            if (isRounded(CORNER_BOTTOM_LEFT)) {
                cornerRect.offsetTo(0f, height - twoRadius)
                path.arcTo(cornerRect, 90f, 90f)
            } else {
                path.lineTo(0f, height.toFloat())
            }

            path.close()
        }
    }

    private fun isRounded(corner: Int): Boolean {
        return roundedCorners and corner == corner
    }
}