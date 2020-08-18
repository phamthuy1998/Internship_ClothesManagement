package com.ptithcm.ptshop.view.wishlist

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ptithcm.core.model.Color
import com.ptithcm.ptshop.R

class ColorSpinnerAdapter(
    context: Context,
    private val layoutId: Int = R.layout.item_color,
    private var listContent: List<Color>
) :
    ArrayAdapter<Color>(context, layoutId, listContent) {

    fun setList(content: ArrayList<Color>) {
        this.listContent = content
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View =
            convertView ?: LayoutInflater.from(context).inflate(layoutId, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(R.id.tvColorName).text = item?.colorName
        view.findViewById<View>(R.id.vColor).backgroundTintList =
            ColorStateList.valueOf(android.graphics.Color.parseColor(item?.colorHex))
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View =
            convertView ?: LayoutInflater.from(context).inflate(layoutId, parent, false)
        val item = getItem(position)
        view.findViewById<TextView>(R.id.tvColorName).text = item?.colorName
        view.findViewById<View>(R.id.vColor).backgroundTintList =
            ColorStateList.valueOf(android.graphics.Color.parseColor(item?.colorHex))
        return view
    }
}