package com.ptithcm.ptshop.view.refine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Size
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemSizeBinding

class SizeRecyclerViewAdapter(
    var sizes: ArrayList<Size>?,
    private val listener: (size: Size?, position: Int) -> Unit
) : RecyclerView.Adapter<SizeRecyclerViewAdapter.SizeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val dataBinding = DataBindingUtil.inflate<ItemSizeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_size,
            parent,
            false
        )
        return SizeViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = sizes?.size ?: 0

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val size = sizes?.get(position)
        holder.bind(size)
        holder.itemView.setOnClickListener {
            listener.invoke(size, position)
        }
    }

    inner class SizeViewHolder(val viewBinding: ItemSizeBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(size: Size?) {
            viewBinding.size = size
            viewBinding.ivRight.isSelected = size?.isChoose ?: false
        }
    }
}