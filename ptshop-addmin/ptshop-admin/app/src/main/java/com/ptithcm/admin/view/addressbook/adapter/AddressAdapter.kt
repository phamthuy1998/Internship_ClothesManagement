package com.ptithcm.admin.view.addressbook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.ShoppingAddress
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemAddressBinding

class AddressAdapter(val listener: ((item: ShoppingAddress?, position: Int?) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var addressList = arrayListOf<ShoppingAddress>()

    var currentPosition: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemAddressBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_address,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = addressList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(addressList[position])
    }

    fun submitList(list: ArrayList<ShoppingAddress>) {
        addressList.clear()
        addressList.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int?) {
        position ?: return
        addressList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ItemViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val context: Context = binding.root.context

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