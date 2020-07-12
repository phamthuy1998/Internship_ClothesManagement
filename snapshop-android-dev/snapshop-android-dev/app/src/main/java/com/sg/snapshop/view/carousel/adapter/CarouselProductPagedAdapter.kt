package com.sg.snapshop.view.carousel.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.CoreApplication
import com.sg.core.model.CountViewModel
import com.sg.core.model.Product
import com.sg.core.util.ObjectHandler
import com.sg.core.vo.ItemViewModel
import com.sg.core.vo.Result
import com.sg.snapshop.R
import com.sg.snapshop.databinding.ItemProductCarouselBinding
import com.sg.snapshop.databinding.LayoutCountItemProductBinding
import com.sg.snapshop.base.NetworkStateItemViewHolder

class CarouselProductPagedAdapter(
    private val listener: (product: Product?, isRefine: Boolean) -> Unit,
    private val listenerAddProduct: ((product: Product?, position: Int) -> Unit)? = null
) :
    PagedListAdapter<ItemViewModel, RecyclerView.ViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ItemViewModel>() {
            override fun areItemsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ItemViewModel,
                newItem: ItemViewModel
            ): Boolean {
                return when (oldItem) {
                    is CountViewModel -> oldItem.count == (newItem as? CountViewModel)?.count
                    else -> oldItem.id == newItem.id
                }
            }
        }
    }

    private var networkState: Result<ItemViewModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.layout_count_item_product -> {
                val dataBinding = DataBindingUtil.inflate<LayoutCountItemProductBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_count_item_product,
                    parent,
                    false
                )
                CountViewHolder(dataBinding)
            }
            R.layout.item_product_carousel -> {
                val dataBinding = DataBindingUtil.inflate<ItemProductCarouselBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_product_carousel,
                    parent,
                    false
                )
                ProductsViewHolder(dataBinding)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_load_more, parent, false)
                NetworkStateItemViewHolder(view) {
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductsViewHolder -> {
                val item = getItem(position) as Product
                holder.bind(item)
                holder.itemView.setOnClickListener {
                    listener.invoke(item, false)
                }
                holder.viewBinding.ivStar.setOnClickListener {
                    if (CoreApplication.instance.profile != null) {
                        it.isSelected = !item.isAddProduct
                    }
                    listenerAddProduct?.invoke(item, position)
                }
            }
            is CountViewHolder -> {
                val item = getItem(position) as CountViewModel
                holder.bind(item)
                holder.viewBinding.layoutRefine.container.setOnClickListener {
                    listener.invoke(Product(), true)
                }
            }

            is NetworkStateItemViewHolder -> {
                holder.bindTo(networkState = networkState )
            }
        }
    }

    private fun hasExtraRow() =
        networkState != null && networkState != Result.Success<ItemViewModel>()

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.layout_load_more
        } else {
            when (getItem(position)) {
                is Product -> R.layout.item_product_carousel
                else -> R.layout.layout_count_item_product
            }
        }
    }

    fun setNetworkState(newNetworkState: Result<ItemViewModel>?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow()) 1 else 0

    inner class ProductsViewHolder(val viewBinding: ItemProductCarouselBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(product: Product?) {
            product?.variants?.let {
                it.sortBy { item -> item.position }
                if (it.size > 0 && !it[0].compare_at_price_after_tax.isNullOrEmpty()) {
                    viewBinding.isPriceCash =
                        it[0].compare_at_price_after_tax?.toDouble()!! > it[0].price_after_tax?.toDouble()!!
                } else {
                    viewBinding.isPriceCash = false
                }
            }
            viewBinding.tvPriceCash.apply {
                paintFlags = (Paint.STRIKE_THRU_TEXT_FLAG)
            }
            viewBinding.product = product
            viewBinding.locale = CoreApplication.instance.currency.name?.toLocale()
            product?.isAddProduct = ObjectHandler.isInWishList(product?.id)
            viewBinding.ivStar.isSelected = product?.isAddProduct ?: false
        }
    }

    inner class CountViewHolder(val viewBinding: LayoutCountItemProductBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: CountViewModel?) {
            viewBinding.count = data?.count
        }
    }

}