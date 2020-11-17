package com.ptithcm.admin.view.shop.adapter

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginRight
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemMainCategoryBinding
import com.ptithcm.admin.databinding.ItemSectionBinding
import com.ptithcm.core.model.Category
import com.ptithcm.core.model.Gender
import com.ptithcm.core.model.ImageCategories
import org.jetbrains.anko.windowManager

class CategoriesRecyclerViewAdapter(
    private var categories: ArrayList<Category>?,
    private var type: Gender,
    private val listener : (category: Category?) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_VIEW = 0
        private const val ITEM_SECTION = 1
    }

    fun setCategories(categories: ArrayList<Category>?, type: Gender) {
        this.categories = categories
        this.type = type
        notifyDataSetChanged()
    }

    fun getCategories() : ArrayList<Category>? = this.categories


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW -> {
                val dataBinding = DataBindingUtil.inflate<ItemMainCategoryBinding>(LayoutInflater.from(parent.context), R.layout.item_main_category, parent,false)
                CategoriesViewHolder(dataBinding)
            }
            else -> {
                val dataBinding = DataBindingUtil.inflate<ItemSectionBinding>(LayoutInflater.from(parent.context), R.layout.item_section, parent,false)
                SectionViewHolder(dataBinding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            ITEM_VIEW -> {
                val category = categories?.get(position)
                (holder as? CategoriesViewHolder)?.bind(category)
                holder.itemView.setOnClickListener {
                    listener.invoke(category)
                }
            }

          else -> {
              (holder as? SectionViewHolder)?.bind()
          }
        }
    }

    override fun getItemCount(): Int = categories?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        return if (categories?.get(position)?.isSection == true) {
            ITEM_SECTION
        } else {
            ITEM_VIEW
        }
    }

    inner class CategoriesViewHolder(private val viewBinding: ItemMainCategoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(category: Category?) {
            val displayMetrics = DisplayMetrics()
            viewBinding.root.context.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels
            viewBinding.ivImage.layoutParams.width = (width - viewBinding.ivImage.marginStart - viewBinding.ivImage.marginRight) / 3
            viewBinding.ivImage.layoutParams.height = (viewBinding.ivImage.layoutParams.width * 0.5).toInt()
            viewBinding.category = category
        }

        private fun filter(images: ArrayList<ImageCategories>, type: Gender): ImageCategories {
            var imageCategories : ImageCategories? = null
            images.forEach {
                if (it.gender == type.value){
                    imageCategories = it.copy()
                }
            }
            return imageCategories ?: ImageCategories()
        }
    }

    inner class SectionViewHolder(private val viewBinding: ItemSectionBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind() {
        }

    }
}