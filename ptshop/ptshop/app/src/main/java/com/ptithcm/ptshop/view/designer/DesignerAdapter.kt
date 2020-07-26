package com.ptithcm.ptshop.view.designer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptithcm.core.model.Provider
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemDesignerBinding
import com.ptithcm.ptshop.databinding.LayoutDesignerHeaderBinding

class DesignerAdapter(
    val listener: ((item: Provider) -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = arrayListOf<Provider>()

    fun setList(list: ArrayList<Provider>) {
        this.data.apply {
            clear()
            addAll(size, list)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = data.size + 1

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.layout_designer_header
            else -> R.layout.item_designer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.layout_designer_header -> {
                val binding =
                    DataBindingUtil.inflate<LayoutDesignerHeaderBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.layout_designer_header,
                        parent,
                        false
                    )
                HeaderViewHolder(binding)
            }

            R.layout.item_designer -> {
                val binding =
                    DataBindingUtil.inflate<ItemDesignerBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.item_designer,
                        parent,
                        false
                    )
                ItemViewHolder(binding)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_load_more, parent, false)
                LoadingViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val item = data[position - 1]
                holder.bind(item)
            }
            is HeaderViewHolder -> {
                holder.bind()
            }

        }
    }

    inner class ItemViewHolder(val binding: ItemDesignerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Provider) {
            Glide.with(binding.root)
                .load(item.imageUrl)
                .placeholder(R.drawable.ic_place_holder)
                .into(binding.ivImage)

            binding.tvName.text = item.brandName

            binding.ctlItemBrandContainer.setOnClickListener {
                listener(item)
            }
        }
    }

    inner class HeaderViewHolder(val binding: LayoutDesignerHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)
}