package com.ptithcm.admin.view.allcategories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemAllCategoriesBinding
import com.ptithcm.admin.ext.gone
import com.ptithcm.core.model.MainCategories

class CategoriesRecyclerViewAdapter(
    var mainCategories: ArrayList<MainCategories>?,
    private val listenerRefine: (position: Int) -> Unit
) :
    RecyclerView.Adapter<CategoriesRecyclerViewAdapter.MainCategoriesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoriesViewHolder {
        val dataBinding = DataBindingUtil.inflate<ItemAllCategoriesBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_all_categories, parent, false
        )
        return MainCategoriesViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = mainCategories?.size ?: 0

    override fun onBindViewHolder(holder: MainCategoriesViewHolder, position: Int) {
        val mainCategory = mainCategories?.get(position)
        holder.bind(mainCategory)
        holder.itemView.setOnClickListener {
            listenerRefine.invoke(position)
        }
    }

    inner class MainCategoriesViewHolder(private val viewBinding: ItemAllCategoriesBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(mainCategory: MainCategories?) {
            viewBinding.mainCategories = mainCategory
            viewBinding.ivRight.gone()
            viewBinding.isChoose = mainCategory?.isChoose
        }
    }
}