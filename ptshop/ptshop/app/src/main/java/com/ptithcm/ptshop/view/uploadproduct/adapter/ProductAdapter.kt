package com.ptithcm.ptshop.view.uploadproduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Product
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemPreviewProductBinding
import mobile.sarproj.com.layout.SwipeLayout

class ProductAdapter(
    private var listItems: ArrayList<Product>?,
    private val listenerRemove: (product: Product?) -> Unit
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setList(data: ArrayList<Product>?) {
        this.listItems = data
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        listItems?.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listItems?.size ?: 0)
    }

    override fun getItemCount() = listItems?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPreviewProductBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_preview_product, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ItemViewHolder
        viewHolder.bind(listItems?.get(position), position)
    }

    inner class ItemViewHolder(val binding: ItemPreviewProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product?, position: Int) {
            binding.item = item
            binding.locale = CoreApplication.instance.currency.name?.toLocale()
            binding.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener {
                override fun onOpen(direction: Int, isContinuous: Boolean) {
                    if (direction == SwipeLayout.LEFT && isContinuous) {
                        removeItem(position)
                        listenerRemove(item)
                    }
                }

                override fun onClose() {

                }
            })

            binding.tvRemove.setOnClickListener {
                removeItem(position)
                listenerRemove(item)
            }

            // DO NOT remove this line below
            binding.dragItem.setOnClickListener{}

            Glide.with(binding.root)
                .load(item?.image?.get(0)?.src)
                .into(binding.ivProduct)
        }
    }
}