package com.ptithcm.admin.widget.fastscroll

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.admin.constant.KEY_EMPTY
import java.util.*

class FastScrollRecyclerViewItemDecoration : RecyclerView.ItemDecoration() {

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val scaledWidth = (parent as? FastScrollRecyclerView)?.scaledWidth ?: 0f
        val sx = (parent as? FastScrollRecyclerView)?.sx ?: 0f
        val scaledHeight = (parent as? FastScrollRecyclerView)?.scaledHeight ?: 0f
        val sy = (parent as? FastScrollRecyclerView)?.sy ?: 0f
        val sections = (parent as? FastScrollRecyclerView)?.sections ?: arrayListOf()
        val section = (parent as? FastScrollRecyclerView)?.section ?: KEY_EMPTY
        val showLetter = (parent as? FastScrollRecyclerView)?.showLetter ?: false

        val textPaint = Paint()
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL

        for (i in sections.indices) {
            textPaint.color = Color.BLUE

            textPaint.isFakeBoldText = showLetter && section != KEY_EMPTY &&
                    sections[i].toString().toUpperCase(Locale.getDefault()) == section.toUpperCase(
                Locale.getDefault()
            )

            textPaint.textSize = (scaledWidth / 2) + 5

            canvas.drawText(sections[i]?.toUpperCase(Locale.getDefault()).toString(),
                sx + textPaint.textSize / 2, sy + parent.paddingTop
                        + scaledHeight * (i + 1), textPaint)
        }
    }
}