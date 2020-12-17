package com.n16dccn159.admin.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewAdapter<T, K>(
    private val onCreateViewHolderFunc: (viewGroup: ViewGroup, viewType: Int) -> ViewDataBinding,
    private var data: K?,
    private val bindFunc: ((binding: T, item: K?, position: Int) -> Unit)? = null,
    private val getItemIdFunc: ((position: Int) -> Long)? = null,
    private val getItemCountFunc: (() -> Int)? = null
) : RecyclerView.Adapter<BaseViewAdapter<T, K>.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(onCreateViewHolderFunc.invoke(viewGroup, viewType))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data, position)
    }

    override fun getItemCount(): Int {
        return getItemCountFunc?.invoke()
            ?: when (data) {
                is Collection<*>? -> (data as? Collection<*>)?.size ?: 0
                else -> 0
            }
    }

    override fun getItemId(position: Int): Long {
        return getItemIdFunc?.invoke(position) ?: position.toLong()
    }

    fun updateData(newData: K?) {
        data = newData
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(private val mBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(item: K?, position: Int) {
            bindFunc?.invoke(mBinding as T, item, position)
        }
    }
}