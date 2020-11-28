package com.ptithcm.ptshop.view.message.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.ptshop.databinding.ViewHighlightMessageMeHolderBinding

class MessageMeHolder (val binding: ViewHighlightMessageMeHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val context: Context = binding.root.context

    fun bind(item: ShoppingAddress) {

        binding.executePendingBindings()
    }
}