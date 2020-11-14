package com.ptithcm.ptshop.view.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemProductCheckoutBinding
import java.util.*

class ProductCheckoutAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val products = arrayListOf<ProductClothesDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductCheckoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product_checkout,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(products[position])
    }

    fun addToList(arr: ArrayList<ProductClothesDetail>) {
        this.products.apply {
            clear()
            addAll(arr)
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(val binding: ItemProductCheckoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductClothesDetail) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}