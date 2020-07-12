package com.sg.snapshop.view.refine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.model.Colour
import com.sg.snapshop.R
import com.sg.snapshop.databinding.ItemColoursBinding

class ColourRecyclerViewAdapter(var colours : ArrayList<Colour>?, private val listener : (colour : Colour?, position : Int) -> Unit) :
    RecyclerView.Adapter<ColourRecyclerViewAdapter.ColourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColourViewHolder {
        val dataBinding = DataBindingUtil.inflate<ItemColoursBinding>(LayoutInflater.from(parent.context), R.layout.item_colours, parent, false)
        return ColourViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = colours?.size ?: 0

    override fun onBindViewHolder(holder: ColourViewHolder, position: Int) {
        val colour = colours?.get(position)
        holder.bind(colour)
        holder.itemView.setOnClickListener {
            listener.invoke(colour, position)
        }
    }

    inner class ColourViewHolder(private val viewBinding: ItemColoursBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(colour: Colour?){
            viewBinding.colour = colour
            viewBinding.ivRight.isSelected = colour?.isChoose ?: false
        }
    }
}