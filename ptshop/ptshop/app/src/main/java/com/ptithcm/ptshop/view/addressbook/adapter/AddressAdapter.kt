package com.ptithcm.ptshop.view.addressbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemAddressBinding
import java.util.concurrent.Executors

class AddressAdapter(val listener: ((item: ShoppingAddress?, position: Int?) -> Unit)? = null) :
    ListAdapter<ShoppingAddress, RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder<ShoppingAddress>(callBack)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ) {

    var currentPosition = -1

    companion object {
        val callBack = object : DiffUtil.ItemCallback<ShoppingAddress>() {
            override fun areItemsTheSame(
                oldItem: ShoppingAddress,
                newItem: ShoppingAddress
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ShoppingAddress,
                newItem: ShoppingAddress
            ): Boolean = oldItem == newItem

            override fun getChangePayload(
                oldItem: ShoppingAddress,
                newItem: ShoppingAddress
            ): Any? =
                null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemAddressBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_address,
            parent,
            false
        )
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? AddressViewHolder)?.bind(getItem(position))
    }

    inner class AddressViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShoppingAddress) {
            binding.item = item

            binding.root.setOnClickListener {
                currentPosition = adapterPosition
                listener?.invoke(item, null)
            }

            binding.ivClose.setOnClickListener {
                currentPosition = adapterPosition
                listener?.invoke(item, adapterPosition)
            }

            binding.executePendingBindings()
        }
    }
}