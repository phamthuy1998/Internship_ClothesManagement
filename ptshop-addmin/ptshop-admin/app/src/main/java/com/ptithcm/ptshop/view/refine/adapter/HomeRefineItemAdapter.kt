package com.ptithcm.ptshop.view.refine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Tag
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.databinding.ItemHomeRefineDetailBinding

class HomeRefineItemAdapter(val listener: ((Tag?) -> Unit)? = null): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listTag = arrayListOf<Tag>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeRefineDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_refine_detail,
            parent,
            false
        )
        return ItemTagViewHolder(binding)
    }

    override fun getItemCount(): Int = listTag.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemTagViewHolder)?.bind(listTag[position])
    }

    fun addList(list: ArrayList<Tag>){
        this.listTag.apply {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }

    inner class ItemTagViewHolder(private val viewBinding: ItemHomeRefineDetailBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item: Tag){
            viewBinding.tag = item
            viewBinding.ivTag.isSelected = item.isCheck
            viewBinding.root.setOnClickListener{
                viewBinding.ivTag.isSelected = viewBinding.ivTag.isSelected.not()
                item.isCheck = item.isCheck.not()
                listener?.invoke(item)
            }
        }
    }
}