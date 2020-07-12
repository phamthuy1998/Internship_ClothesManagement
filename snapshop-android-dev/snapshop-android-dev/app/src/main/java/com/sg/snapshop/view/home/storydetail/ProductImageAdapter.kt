package com.sg.snapshop.view.home.storydetail

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sg.core.model.Product
import com.sg.snapshop.R
import com.sg.snapshop.databinding.ItemProductBinding
import com.sg.snapshop.ext.roundPrice
import java.util.*
import kotlin.collections.ArrayList

class ProductImageAdapter(val locale: Locale = Locale.US, val listener:((item: Any?, position: Int?) -> Unit)? = null):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = ArrayList<Product>()

    private var isFullDetail = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductBinding>(LayoutInflater.from(parent.context),  R.layout.item_product, parent,false)
        return WishViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? WishViewHolder)?.bind(list[position])
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }
    override fun getItemId(position: Int): Long = list[position].id.toLong()

    fun addList(arrayList: ArrayList<Product>){
        this.list.clear()
        this.list.addAll(arrayList)
        notifyDataSetChanged()
    }

    fun setFullDetail(yes: Boolean){
        isFullDetail = yes
    }

    inner class WishViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Product?){
            binding.vendor = item?.vendor
            binding.title = item?.title

            if (item?.variants?.size ?: 0 > 0 && isFullDetail){
                binding.item = item
                item?.variants?.first()?.apply {
                    binding.tvPrice.text = price_after_tax?.roundPrice(locale)
                    binding.tvDiscountPrice.text = price_after_tax?.roundPrice(locale)
                    binding.tvOriginPrice.apply {
                        text = compare_at_price_after_tax?.roundPrice(locale)
                        paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    }
                }
            }

            if ( item?.image?.size ?: 0 > 0) {
                Glide.with(binding.root.context)
                    .load(item?.image?.get(0)?.src)
                    .into(binding.ivWishList)
            }

            binding.wishListContainer.setOnClickListener {
                listener?.invoke(item, null)
            }
        }

    }

}