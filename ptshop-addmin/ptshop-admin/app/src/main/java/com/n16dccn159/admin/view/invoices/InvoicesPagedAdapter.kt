package com.n16dccn159.admin.view.invoices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.n16dccn159.core.model.CountViewModel
import com.n16dccn159.core.model.Invoice
import com.n16dccn159.core.vo.ItemViewModel
import com.n16dccn159.core.vo.Result
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.NetworkStateItemViewHolder
import com.n16dccn159.admin.databinding.ItemInvoiceBinding

class InvoicesPagedAdapter(
    private val listener: (invoice: Invoice?, Int) -> Unit
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
            R.layout.item_invoice -> {
                val dataBinding = DataBindingUtil.inflate<ItemInvoiceBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_invoice,
                    parent,
                    false
                )
                InvoiceViewHolder(dataBinding)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_load_more, parent, false)
                NetworkStateItemViewHolder(view) {}
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InvoiceViewHolder -> {
                val item = getItem(position) as Invoice
                holder.bind(item)
                holder.itemView.setOnClickListener {
                    listener.invoke(item, position)
                }
            }
            is NetworkStateItemViewHolder -> {
                holder.bindTo(networkState = networkState)
            }
        }
    }

    fun hasExtraRow() =
        networkState != null && networkState != Result.Success<ItemViewModel>()

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.layout_load_more
        } else {
            R.layout.item_invoice
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

    inner class InvoiceViewHolder(val viewBinding: ItemInvoiceBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(invoice: Invoice?) {
            viewBinding.item = invoice
            viewBinding.executePendingBindings()
        }
    }
}