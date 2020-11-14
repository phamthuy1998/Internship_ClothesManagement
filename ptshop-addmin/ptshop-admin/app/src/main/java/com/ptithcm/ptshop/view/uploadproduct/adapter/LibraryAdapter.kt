package com.ptithcm.ptshop.view.uploadproduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptithcm.core.model.UploadFile
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemLibraryBinding

class LibraryAdapter(
    private var listItem: List<UploadFile>?,
    val listener: (itemSelected: UploadFile?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setList(data: List<UploadFile>?) {
        data?.let {
            this.listItem = data
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = listItem?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as LibraryViewHolder
        viewHolder.bind(listItem?.get(position))

        viewHolder.binding.ctlContainerLibrary.setOnClickListener {
            listener(listItem?.get(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemLibraryBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_library, parent, false)
        return LibraryViewHolder(binding)
    }

    inner class LibraryViewHolder(val binding: ItemLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UploadFile?) {

            binding.item = item

            Glide.with(binding.root)
                .load(item?.file?.path)
                .placeholder(R.drawable.ic_place_holder)
                .into(binding.ivLibrary)
        }
    }
}