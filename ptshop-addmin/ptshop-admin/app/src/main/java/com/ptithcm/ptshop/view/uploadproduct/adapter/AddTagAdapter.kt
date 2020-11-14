package com.ptithcm.ptshop.view.uploadproduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Tag
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemAddTagBinding

class AddTagAdapter(
    private var listItems: List<Tag>?,
    private val listener: (tag: Tag?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setList(data: List<Tag>?) {
        this.listItems = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = listItems?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemAddTagBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_add_tag, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ItemViewHolder
        viewHolder.bind(listItems?.get(position))
    }

    inner class ItemViewHolder(val binding: ItemAddTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tag?) {
            binding.item = item
            binding.rlAddTag.setOnClickListener {
                listener(item)
            }
        }
    }
}