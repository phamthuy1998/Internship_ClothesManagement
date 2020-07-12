package com.sg.snapshop.view.sizeguide

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.sg.snapshop.R

class SizeGuideFunc(
    private val context: Context
) {

    private var recyclableTextView: TextView? = null

    private fun makeTableRowWithText(
        text: String, widthInPercentOfScreenWidth: Int,
        fixedHeightInPixels: Int, isFirstColumn: Boolean = false
    ): TextView? {
        recyclableTextView = TextView(context)
        val screenWidth = context.resources.displayMetrics.widthPixels
        val screenHeight = context.resources.displayMetrics.heightPixels
        recyclableTextView?.text = text
        val typeface = ResourcesCompat.getFont(context, R.font.montserrat_regular)
        recyclableTextView?.setTextColor(Color.BLACK)
        recyclableTextView?.typeface = typeface
        recyclableTextView?.textSize = 13f
        if (!isFirstColumn) {
            recyclableTextView?.gravity = Gravity.CENTER
        } else {
            recyclableTextView?.gravity = Gravity.CENTER_VERTICAL
            recyclableTextView?.setPadding(20, 0, 0, 0)
        }
        recyclableTextView?.width = widthInPercentOfScreenWidth * screenWidth / 100
        recyclableTextView?.height = fixedHeightInPixels * screenHeight / 100
        return recyclableTextView
    }

    fun initRow(row: TableRow = TableRow(context), layoutParam: TableRow.LayoutParams) {
        val mRow = row
        mRow.layoutParams = layoutParam
        mRow.gravity = Gravity.CENTER
        mRow.setBackgroundColor(Color.WHITE)
    }

    fun addRow(
        fixedColumn: TableLayout, scrollablePart: TableLayout, row: TableRow, sizes : ArrayList<String>,
        text: String, widthInPercentOfScreenWidthFirst: Int, widthInPercentOfScreenWidth : Int, fixedHeightInPixels: Int
    ) {
        val fixedView =
            makeTableRowWithText(text, widthInPercentOfScreenWidthFirst, fixedHeightInPixels, true)
        fixedColumn.addView(fixedView)
        for (size in sizes) {
            row.addView(
                makeTableRowWithText(size, widthInPercentOfScreenWidth, fixedHeightInPixels)
            )
        }
        scrollablePart.addView(row)
    }
}