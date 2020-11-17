package com.ptithcm.admin.view.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemWishListBinding
import java.util.concurrent.Executors

class WishListAdapter(val listener: ((item: Any?, position: Int?) -> Unit)? = null) :
    ListAdapter<ProductClothes, RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder<ProductClothes>(callBack)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ) {

    companion object {
        val callBack = object : DiffUtil.ItemCallback<ProductClothes>() {
            override fun areItemsTheSame(
                oldItem: ProductClothes,
                newItem: ProductClothes
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ProductClothes,
                newItem: ProductClothes
            ): Boolean = oldItem == newItem

            override fun getChangePayload(oldItem: ProductClothes, newItem: ProductClothes): Any? =
                null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemWishListBinding>(LayoutInflater.from(parent.context),  R.layout.item_wish_list, parent,false)
        return WishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? WishViewHolder)?.bind(getItem(position), position)
    }

    fun alertRemove(position: Int){
        val newList = ArrayList<ProductClothes>(currentList)
        val size = currentList.size
        newList.removeAt(position)
        submitList(newList)
        notifyItemRangeChanged(position, size)
    }

    fun hasData() = currentList.size > 0

    inner class WishViewHolder(val binding: ItemWishListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductClothes?, position: Int) {
            binding.product = item
            binding.ivClose.setOnClickListener {
                listener?.invoke(item?.id, position)
            }
            binding.wishListContainer.setOnClickListener {
                listener?.invoke(item, null)
            }
        }

    }

}