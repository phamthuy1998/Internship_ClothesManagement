package com.ptithcm.admin.view.uploadproduct.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Tag
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemTagGroupBinding

class TagGroupAdapter(
    private var listItems: List<List<Tag>>?,
    private val listener: (tag: Tag?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setList(data: List<List<Tag>>?) {
        this.listItems = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = listItems?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTagGroupBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_tag_group, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ItemViewHolder
        viewHolder.bind(listItems?.get(position), position)
    }

    inner class ItemViewHolder(val binding: ItemTagGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(listTags: List<Tag>?, position: Int) {
            when (position) {
                0 -> {
                    binding.tvTitle.text = "Filter By"
                }
                1 -> {
                    binding.tvTitle.text = "Sort By"
                }
                2 -> {
                    binding.tvTitle.text = "Gender"
                }
            }
            binding.rcvTag.adapter = AddTagAdapter(listTags, listener)
        }
    }
}