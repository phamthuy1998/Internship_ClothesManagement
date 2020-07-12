package com.sg.snapshop.view.designer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sg.core.model.Brand
import com.sg.snapshop.R
import com.sg.snapshop.databinding.ItemDesignerBinding
import com.sg.snapshop.databinding.LayoutDesignerHeaderBinding

class DesignerAdapter(
    val listener: ((item: Brand) -> Unit),
    val listenerViewAll: (() -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = arrayListOf(Brand())
    private var needLoadMore = true

    fun setList(list: ArrayList<Brand>) {
        this.data.apply {
            clear()
            addAll(size, list)
            notifyDataSetChanged()
        }
    }

    fun addAll(list: ArrayList<Brand>) {
        this.data.addAll(itemCount - 1,list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    fun hideLoadMore() {
        needLoadMore = false
        this.data.removeAt(itemCount - 1)
        notifyItemChanged(itemCount - 1)
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> R.layout.layout_designer_header
            needLoadMore && position == itemCount - 1 -> R.layout.layout_load_more
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
        if (needLoadMore && position == itemCount -1)
            return
        val item = data[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(item)
            }
            is HeaderViewHolder -> {
                holder.bind()
            }

        }
    }

    inner class ItemViewHolder(val binding: ItemDesignerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Brand) {
            Glide.with(binding.root)
                .load(if (item.avatar_image.isNullOrEmpty()) item.image_url else item.avatar_image)
                .placeholder(R.drawable.ic_place_holder)
                .into(binding.ivImage)

            binding.ctlItemBrandContainer.setOnClickListener {
                listener(item)
            }
        }
    }

    inner class HeaderViewHolder(val binding: LayoutDesignerHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.tvViewAllDesigners.setOnClickListener {
                listenerViewAll()
            }
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)
}