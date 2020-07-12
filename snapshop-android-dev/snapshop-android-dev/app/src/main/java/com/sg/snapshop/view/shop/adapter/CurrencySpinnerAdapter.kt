package com.sg.snapshop.view.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.sg.core.model.SupportedCurrency
import com.sg.snapshop.R

class CurrencySpinnerAdapter(context: Context, private val layoutId: Int = R.layout.item_currency, private var currencies : ArrayList<SupportedCurrency>) : ArrayAdapter<SupportedCurrency>(context, layoutId, currencies){

    fun setCurrencies(currencies: ArrayList<SupportedCurrency>){
        this.currencies.addAll(currencies)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return convertView ?: LayoutInflater.from(context).inflate(layoutId, parent, false)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(layoutId, parent, false)
        val currency = getItem(position)
        view.findViewById<AppCompatTextView>(R.id.tvCurrency).text = currency?.name?.toValue()
        return view
    }
}