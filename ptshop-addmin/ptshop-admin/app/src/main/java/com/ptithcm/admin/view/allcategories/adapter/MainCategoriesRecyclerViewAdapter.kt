package com.ptithcm.admin.view.allcategories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.admin.R
import com.ptithcm.admin.databinding.ItemAllCategoriesBinding
import com.ptithcm.admin.ext.addViewExt
import com.ptithcm.admin.ext.expandOrCollapse
import com.ptithcm.admin.ext.gone
import com.ptithcm.admin.ext.visible
import com.ptithcm.core.model.MainCategories
import com.ptithcm.core.model.TypeCategories

class MainCategoriesRecyclerViewAdapter(
    var mainCategories: ArrayList<MainCategories>?,
    private val isExpandListener: (position: Pair<Int, Int>, isExpand: Boolean) -> Unit,
    private val listenerRefine: ((positionTriple: Triple<Int, Int, Int>, type: TypeCategories) -> Unit)? = null
) : RecyclerView.Adapter<MainCategoriesRecyclerViewAdapter.MainCategoriesViewHolder>() {

    private var viewCategories: HashMap<Int, AppCompatImageView> = hashMapOf()
    private val viewFilterCategories: HashMap<Pair<Int, Int>, AppCompatImageView> =
        hashMapOf()

    fun clearView() {
        viewCategories.forEach {
            it.value.gone()
        }
        viewFilterCategories.forEach {
            it.value.gone()
        }
    }

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
    }


    inner class MainCategoriesViewHolder(val viewBinding: ItemAllCategoriesBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(mainCategory: MainCategories?) {
            viewBinding.mainCategories = mainCategory
            viewBinding.isChoose = mainCategory?.isChoose
            if (mainCategory?.isChoose == true) {
                viewCategories[adapterPosition] = viewBinding.ivApply
            }
            if (mainCategory?.childCategories?.isEmpty() == true || mainCategory?.childCategories?.size == 1) {
                viewBinding.ivRight.gone()
            } else {
                viewBinding.ivRight.visible()
                if (mainCategory?.isExpand == true) {
                    expandView(viewBinding, mainCategory, adapterPosition)
                } else {
                    viewBinding.subView.gone()
                    viewBinding.ivRight.setBackgroundResource(R.drawable.ic_right_black_24dp)
                }
            }
            viewBinding.tvName.setOnClickListener {
                if (mainCategory?.childCategories?.isEmpty() == true || mainCategory?.childCategories?.size == 1) {
                    clearChooseCategories(adapterPosition, viewBinding)
                    viewBinding.isChoose = !mainCategory.isChoose
                    if (adapterPosition == 0) {
                        listenerRefine?.invoke(Triple(adapterPosition, -1, -1), TypeCategories.MAIN_CATEGORIES)
                    } else {
                        listenerRefine?.invoke(Triple(adapterPosition, -1, -1), TypeCategories.CATEGORIES)
                    }
                } else {
                    isExpandListener.invoke(
                        Pair(adapterPosition, -1),
                        viewBinding.subView.expandOrCollapse()
                    )
                }
            }
        }

        private fun bindFilter(
            viewBinding: ItemAllCategoriesBinding, mainCategory: MainCategories?,
            parentPosition: Int, position: Int
        ) {
            viewBinding.mainCategories = mainCategory
            viewBinding.ivRight.gone()
            viewBinding.isChoose = mainCategory?.isChoose
            if (mainCategory?.isChoose == true) {
                viewFilterCategories[Pair(parentPosition, position)] = viewBinding.ivApply
            }
            viewBinding.tvName.setOnClickListener {
                clearChooseFilter(parentPosition, position, viewBinding)
                viewCategories[0]?.gone()
                viewBinding.isChoose = !(mainCategory?.isChoose ?: false)
                if (position == 0) {
                    listenerRefine?.invoke(
                        Triple(parentPosition, position, -1),
                        TypeCategories.CATEGORIES
                    )
                } else {
                    listenerRefine?.invoke(
                        Triple(parentPosition, position, -1),
                        TypeCategories.FILTER
                    )
                }
            }
        }


        private fun expandView(
            viewBinding: ItemAllCategoriesBinding, mainCategory: MainCategories,
            parentPosition: Int
        ) {
            viewBinding.subView.addViewExt(
                R.layout.item_all_categories,
                mainCategory.childCategories ?: arrayListOf(),
                viewBinding.subView.marginStart, parentPosition, this::listenerFilter
            )
            toggleDrawable(
                viewBinding.ivRight,
                expandOrCollapse(viewBinding.subView, mainCategory.isExpand)
            )
        }

        private fun listenerFilter(
            viewBinding: ViewDataBinding?, item: MainCategories,
            parentIndex: Int, index: Int
        ) {
            (viewBinding as? ItemAllCategoriesBinding)?.apply {
                bindFilter(this, item, parentIndex, index)
            }
        }

        private fun clearChooseCategories(
            position: Int,
            viewBinding: ItemAllCategoriesBinding
        ) {
            if (position == 0) {
                viewCategories[position] = viewBinding.ivApply
                viewCategories.forEach {
                    if (it.key > 0) {
                        it.value.gone()
                    }
                }
                viewFilterCategories.forEach {
                    it.value.gone()
                }
            } else {
                viewCategories[position] = viewBinding.ivApply
                viewCategories[0]?.gone()
            }
        }

        private fun clearChooseFilter(
            parentPosition: Int,
            position: Int,
            viewBinding: ItemAllCategoriesBinding
        ) {
            if (position == 0) {
                viewFilterCategories[Pair(parentPosition, position)] =
                    viewBinding.ivApply
                viewFilterCategories.forEach {
                    if (it.key.first == parentPosition && it.key.second > 0) {
                        it.value.gone()
                    }
                }
            } else {
                viewFilterCategories[Pair(parentPosition, position)] = viewBinding.ivApply
                viewFilterCategories[Pair(parentPosition, 0)]?.gone()
            }
        }

    }

    private fun toggleDrawable(rootView: AppCompatImageView, isViewVisible: Boolean) {
        rootView.setBackgroundResource(
            if (isViewVisible)
                R.drawable.ic_down_black_24dp
            else
                R.drawable.ic_right_black_24dp
        )
    }
}