package com.ptithcm.admin.view.wishlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.ptithcm.admin.R

class SpinnerAdapter(context: Context,private val layoutId: Int = R.layout.item_currency, private val listContent: ArrayList<String>):
    ArrayAdapter<String>(context, layoutId, listContent){

    fun setList(content: ArrayList<String>){
        this.listContent.addAll(content)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return convertView ?: LayoutInflater.from(context).inflate(layoutId, parent, false)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(layoutId, parent, false)
        view.findViewById<AppCompatTextView>(R.id.tvCurrency).text = getItem(position)
        return view
    }
}