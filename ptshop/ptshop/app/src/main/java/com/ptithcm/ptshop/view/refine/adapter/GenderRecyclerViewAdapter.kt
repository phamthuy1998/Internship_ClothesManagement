package com.ptithcm.ptshop.view.refine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.GenderFilter
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemGenderBinding

class GenderRecyclerViewAdapter( var genders : ArrayList<GenderFilter>?, private val listener : (gender : GenderFilter?, position : Int) -> Unit) :
    RecyclerView.Adapter<GenderRecyclerViewAdapter.GenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderViewHolder {
        val dataBinding = DataBindingUtil.inflate<ItemGenderBinding>(LayoutInflater.from(parent.context), R.layout.item_gender, parent, false)
        return GenderViewHolder(dataBinding)
    }

    override fun getItemCount(): Int  = genders?.size ?: 0

    override fun onBindViewHolder(holder: GenderViewHolder, position: Int) {
        val gender = genders?.get(position)
        holder.bind(gender)
        holder.itemView.setOnClickListener{
            listener.invoke(gender, position)
        }
    }

    inner class GenderViewHolder(val viewBinding: ItemGenderBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(gender : GenderFilter?){
            viewBinding.gender = gender
            viewBinding.ivRight.isSelected = gender?.isChoose ?: false
        }
    }
}