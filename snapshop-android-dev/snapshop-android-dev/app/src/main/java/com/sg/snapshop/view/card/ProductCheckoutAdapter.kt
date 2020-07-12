package com.sg.snapshop.view.card

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sg.core.CoreApplication
import com.sg.core.model.ProductVariant
import com.sg.snapshop.R
import com.sg.snapshop.databinding.ItemProductCheckoutBinding
import com.sg.snapshop.ext.roundPrice
import com.sg.snapshop.ext.visible

class ProductCheckoutAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val productVariants = arrayListOf<ProductVariant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductCheckoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product_checkout,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = productVariants.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(productVariants[position])
    }

    fun addToList(arr: ArrayList<ProductVariant>){
        this.productVariants.apply {
            clear()
            addAll(arr)
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(val binding: ItemProductCheckoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: ProductVariant){
            val locale = CoreApplication.instance.currency.getLocale()!!
            item.product_variant.checkIfWrongPrice()
            binding.data = item
            item.product_variant.apply {
                binding.tvPrice.text = price_after_tax?.roundPrice(locale)
                binding.tvDiscountPrice.text = price_after_tax?.roundPrice(locale)
                binding.tvOriginPrice.apply {
                    text = compare_at_price_after_tax?.roundPrice(locale)
                    paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG)
                }
            }
            item.applied_discount?.apply {
                binding.discountInfo.root.visible()
                binding.discountInfo.title = title
            }
            Glide.with(binding.root.context)
                .load(item.product_variant.product?.image?.get(0)?.src_original)
                .into(binding.ivProduct)
        }
    }
}