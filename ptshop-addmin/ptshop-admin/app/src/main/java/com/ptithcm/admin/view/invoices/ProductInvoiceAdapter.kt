package com.ptithcm.admin.view.invoices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.InvoiceProductDetail
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemProductInvoiceBinding

class ProductInvoiceAdapter(
    var listener: ((item: InvoiceProductDetail, type: Int) -> Unit?)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_CLICK = 1
        const val ITEM_WRITE_REVIEW = 2
    }

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
            binding.apply {
                this.item = item
                binding.root.setOnClickListener { listener?.invoke(item, ITEM_CLICK) }
                tvWriteReview.setOnClickListener { listener?.invoke(item, ITEM_WRITE_REVIEW) }
                executePendingBindings()
            }
        }
    }
}