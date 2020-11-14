package com.ptithcm.ptshop.view.addressbook.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.ptithcm.ptshop.R


class CountyAdapter(val context: Context) : BaseAdapter(), SpinnerAdapter {
    var data:ArrayList<String> = arrayListOf("County/State*")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(context, R.layout.item_location_spinner, null)
        val textView = view.findViewById<TextView>(R.id.item)
        if (position == 0) {
            textView.text = ""
            textView.hint = "County/State*"
        } else textView.text = data[position]
        return view
    }

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }

    override fun getItem(position: Int): String {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getDropDownView(position, convertView, parent)
        if (position == 0) {
            view.findViewById<TextView>(R.id.item).visibility=View.GONE
            view.visibility = View.GONE
        }
        return view
    }

    override fun getCount(): Int {
        return data.size
    }

    fun getPosition(location:String):Int{
        return data.indexOf(data.find { it == location })
    }

    fun setListLocation(_data:ArrayList<String>){
        data = arrayListOf("County/State*")
        data.addAll(_data)
        notifyDataSetChanged()
    }

}