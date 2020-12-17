package com.n16dccn159.admin.view.designer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.n16dccn159.core.model.Brand
import com.n16dccn159.admin.R
import com.n16dccn159.admin.databinding.ItemDesignerBinding

class DesignerPagingAdapter(private val listener: (brand: Brand?) -> Unit) :
    PagedListAdapter<Brand, RecyclerView.ViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Brand>() {
            override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Brand,
                newItem: Brand
            ): Boolean = oldItem.id == newItem.id
        }
    }

    private var isShowLoadMore: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_designer -> {
                val binding =
                    DataBindingUtil.inflate<ItemDesignerBinding>(
                        LayoutInflater.from(parent.context),
                        R.layout.item_designer,
                        parent,
                        false
                    )
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
        if (!isShowLoadMore) {
            val item = getItem(position)
            (holder as? ItemViewHolder)?.bind(item)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isShowLoadMore && position == itemCount - 1) {
            R.layout.layout_load_more
        } else {
            R.layout.item_designer
        }

    }

    fun showLoadingMore(isShowLoadMore: Boolean) {
        val hadShowMore = this.isShowLoadMore
        this.isShowLoadMore = isShowLoadMore
        val hasExtraRow = isShowLoadMore
        if (hadShowMore != hasExtraRow) {
            if (hadShowMore) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && isShowLoadMore) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if (isShowLoadMore) 1 else 0


    inner class ItemViewHolder(private val binding: ItemDesignerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Brand?) {
            Glide.with(binding.root)
                .load(item?.image_url)
                .placeholder(R.drawable.ic_place_holder)
                .into(binding.ivImage)
            binding.ctlItemBrandContainer.setOnClickListener {
                listener.invoke(item)
            }
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

}