package com.ptithcm.ptshop.view.addressbook.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.ptithcm.ptshop.R


class ExpiryDateAdapter(val context: Context) : BaseAdapter(), SpinnerAdapter {
    var data:ArrayList<String> = arrayListOf("Expiry Date*")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(context, R.layout.item_date_spinner, null)
        val textView = view.findViewById<TextView>(R.id.item)
        if (position == 0) {
            textView.text = ""
            textView.hint = "Expiry Date*"
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

    fun getPosition(date:String):Int{
        return data.indexOf(data.find { it == date })
    }

    fun setListData(_data:ArrayList<String>){
        data = arrayListOf("Expiry Date*")
        data.addAll(_data)
        notifyDataSetChanged()
    }

}