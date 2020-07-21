package com.ptithcm.ptshop.view.addressbook.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.ptithcm.core.model.Location
import com.ptithcm.ptshop.R


class LocationAdapter(val context: Context) : BaseAdapter(), SpinnerAdapter {
    var data: ArrayList<Location> =
        arrayListOf(Location(id = 0, counties = null, name = "Country*"))

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = View.inflate(context, R.layout.item_location_spinner, null)
        val textView = view.findViewById<TextView>(R.id.item)
        if (position == 0) {
            textView.text = ""
            textView.hint = "Country*"
        } else textView.text = data[position].name
        return view
    }

    override fun getItem(position: Int): Location {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getDropDownView(position, convertView, parent)

        if (position == 0) {
            view.findViewById<TextView>(R.id.item).visibility=View.GONE
            view.visibility = View.GONE
        }
        return view
    }

    fun getPosition(location: String): Int {
        return data.indexOf(data.find { it.name == location })
    }

    fun setListLocation(_data: ArrayList<Location>) {
        data = arrayListOf(Location(id = 0, counties = null, name = "Country*"))
        data.addAll(_data)
        notifyDataSetChanged()
    }

}