package com.n16dccn159.admin.view.refine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.n16dccn159.core.model.Tag
import com.n16dccn159.admin.R
import com.n16dccn159.admin.databinding.ItemHomeRefineBinding

class HomeRefineAdapter(val listener: ((Tag?) -> Unit)? = null):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = arrayListOf<ArrayList<Tag>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeRefineBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_refine,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.binding(listTag = list[position])
    }

    fun addList(list: ArrayList<ArrayList<Tag>>){
        this.list.apply {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(private val viewBinding: ItemHomeRefineBinding): RecyclerView.ViewHolder(viewBinding.root){

        fun binding(listTag: ArrayList<Tag>){

            // currently there are only 3 kind of tag_type
            val str = when(listTag.first().tag_type){
                1 -> R.string.filter_by
                2 -> R.string.sort_by
                else -> R.string.gender
            }
            viewBinding.title = viewBinding.root.context.getString(str)

            val adapter = HomeRefineItemAdapter{
                listener?.invoke(it)
            }
            viewBinding.rvItemRefine.adapter = adapter
            adapter.addList(listTag)
        }
    }
}