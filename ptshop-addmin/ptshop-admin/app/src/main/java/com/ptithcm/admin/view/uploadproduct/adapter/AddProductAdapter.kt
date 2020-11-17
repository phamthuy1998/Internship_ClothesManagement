package com.ptithcm.admin.view.uploadproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ptithcm.core.model.Product
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemAddProductBinding

class AddProductAdapter(
    private var listItems: ArrayList<Product>? = arrayListOf(),
    private val listener: (product: Product?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isShowLoadMore = false

    fun setList(data: ArrayList<Product>?) {
        this.listItems = data
        notifyDataSetChanged()
    }

    fun addAll(data: ArrayList<Product>?) {
        data?.let {
            val pos = itemCount
            listItems?.addAll(pos, data)
            notifyItemRangeChanged(pos, listItems?.size ?: 0)
        }
    }

    fun getList(): ArrayList<Product>? {
        return listItems
    }

    fun isShowLoadMore(needLoadMore: Boolean = false) {
        this.isShowLoadMore = needLoadMore
        notifyItemChanged(itemCount - 1)
    }

    override fun getItemViewType(position: Int) =
        if (isShowLoadMore && position == itemCount -1)
            R.layout.layout_load_more
        else
            R.layout.item_add_product

    override fun getItemCount() = listItems?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_add_product -> {
                val binding: ItemAddProductBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.item_add_product, parent, false)
                ItemViewHolder(binding)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_load_more, parent, false)
                LoadingViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isShowLoadMore && position == itemCount -1)
            return
        val viewHolder = holder as ItemViewHolder
        viewHolder.bind(listItems?.get(position))
    }

    inner class ItemViewHolder(val binding: ItemAddProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product?) {
            binding.item = item
//            binding.locale = CoreApplication.instance.currency.name?.toLocale()
            Glide.with(binding.root)
                .load(item?.image?.get(0)?.src)
                .into(binding.ivProduct)
            binding.ctlAddProduct.setOnClickListener {
                listener(item)
            }
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)
}