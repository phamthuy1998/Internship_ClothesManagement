package com.ptithcm.ptshop.view.wishlist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptithcm.core.model.Product
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemWishListBinding
import com.ptithcm.ptshop.ext.roundPrice
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

class WishListAdapter(val locale: Locale = Locale.UK, val listener:((item: Any?, position: Int?) -> Unit)? = null):
    ListAdapter<Product, RecyclerView.ViewHolder>(
        AsyncDifferConfig.Builder<Product>(callBack)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ) {

    companion object{
        val callBack = object : DiffUtil.ItemCallback<Product>(){
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem

            override fun getChangePayload(oldItem: Product, newItem: Product): Any? = null
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
        val newList = ArrayList<Product>(currentList)
        val size = currentList.size
        newList.removeAt(position)
        submitList(newList)
        notifyItemRangeChanged(position, size)
    }

    fun hasData() = currentList.size > 0

    inner class WishViewHolder(val binding: ItemWishListBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Product?, position: Int){
            binding.item = item
            if (item?.variants?.size ?: 0 > 0){
                item?.variants?.first()?.apply {
                    binding.tvPrice.text = price_after_tax?.roundPrice(locale)
                    binding.tvDiscountPrice.text = price_after_tax?.roundPrice(locale)
                    binding.tvOriginPrice.apply {
                        text = compare_at_price_after_tax?.roundPrice(locale)
                        paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG)
                    }
                }
            }else{
                binding.tvPrice.text = binding.root.context.getString(R.string.no_price)
            }
            Glide.with(binding.root.context)
                .load(item?.image?.get(0)?.src)
                .into(binding.ivWishList)
            binding.ivClose.setOnClickListener {
                listener?.invoke(item?.id, position)
            }
            binding.wishListContainer.setOnClickListener {
                listener?.invoke(item, null)
            }
        }

    }

}