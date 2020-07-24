package com.ptithcm.ptshop.view.categories.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.CountViewModel
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.core.vo.ItemViewModel
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemProductCarouselBinding
import com.ptithcm.ptshop.databinding.LayoutCountItemCategoryBinding
import com.ptithcm.ptshop.base.NetworkStateItemViewHolder

class CategoriesPagedAdapter(
    private val banner: String? = null,
    private val listener: (product: ProductClothes?, isRefine: Boolean) -> Unit,
    private val listenerAddProduct: ((product: ProductClothes?) -> Unit)? = null
) : PagedListAdapter<ItemViewModel, RecyclerView.ViewHolder>(DIFF_UTIL) {

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
            R.layout.layout_count_item_category -> {
                val dataBinding = DataBindingUtil.inflate<LayoutCountItemCategoryBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_count_item_category,
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
                val item = getItem(position) as ProductClothes
                holder.bind(item)
                holder.itemView.setOnClickListener {
                    listener.invoke(item, false)
                }
                holder.viewBinding.ivStar.setOnClickListener {
                    if (CoreApplication.instance.profile != null) {
                        it.isSelected = !it.isSelected
                    }
                    listenerAddProduct?.invoke(item)
                }
            }
            is CountViewHolder -> {
                val item = getItem(position) as CountViewModel
                holder.bind(item)
//                holder.viewBinding.layoutRefine.container.setOnClickListener {
//                    listener.invoke(ProductClothes(), true)
//                }
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
                else -> R.layout.layout_count_item_category
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

        fun bind(product: ProductClothes?) {
            viewBinding.product = product
            viewBinding.tvPriceCash.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    inner class CountViewHolder(val viewBinding: LayoutCountItemCategoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: CountViewModel?) {
            viewBinding.count = data?.count
            viewBinding.banner = banner
        }
    }
}