package com.n16dccn159.admin.view.refine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.n16dccn159.core.model.Size
import com.n16dccn159.admin.R
import com.n16dccn159.admin.databinding.ItemSizeBinding

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