package com.ptithcm.ptshop.view.invoices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.InvoiceProductDetail
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemProductInvoiceBinding

class ProductInvoiceAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val products = arrayListOf<InvoiceProductDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductInvoiceBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product_invoice,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(products[position])
    }

    fun addToList(arr: List<InvoiceProductDetail>?) {
        this.products.apply {
            clear()
            addAll(arr ?: arrayListOf())
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(val binding: ItemProductInvoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InvoiceProductDetail) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}