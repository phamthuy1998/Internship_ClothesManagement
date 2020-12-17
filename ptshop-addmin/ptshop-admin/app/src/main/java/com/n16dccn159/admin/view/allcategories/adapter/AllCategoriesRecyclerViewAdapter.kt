package com.n16dccn159.admin.view.allcategories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.n16dccn159.admin.R
import com.n16dccn159.admin.databinding.ItemAllCategoriesBinding
import com.n16dccn159.admin.ext.addViewExt
import com.n16dccn159.admin.ext.expandOrCollapse
import com.n16dccn159.admin.ext.gone
import com.n16dccn159.admin.ext.visible
import com.n16dccn159.core.model.MainCategories
import com.n16dccn159.core.model.TypeCategories

class AllCategoriesRecyclerViewAdapter(
    var mainCategories: ArrayList<MainCategories>?,
    private val isRefine: Boolean = false,
    private val isExpandListener: (position: Pair<Int, Int>, isExpand: Boolean) -> Unit,
    private val listener: ((mainCategory: MainCategories?, type: TypeCategories, positionTriple: Triple<Int, Int, Int>) -> Unit)? = null,
    private val listenerRefine: ((positionTriple: Triple<Int, Int, Int>, type: TypeCategories) -> Unit)? = null
) :
    RecyclerView.Adapter<AllCategoriesRecyclerViewAdapter.MainCategoriesViewHolder>() {

    private var viewParentCategories: HashMap<Int, AppCompatImageView> = hashMapOf()
    private val viewCategories: HashMap<Pair<Int, Int>, AppCompatImageView> = hashMapOf()
    private val viewFilterCategories: HashMap<Triple<Int, Int, Int>, AppCompatImageView> =
        hashMapOf()

    fun clearView() {
        viewParentCategories.forEach {
            it.value.gone()
        }
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
            if (isRefine) {
                viewBinding.isChoose = mainCategory?.isChoose
                if (mainCategory?.isChoose == true) {
                    viewParentCategories[adapterPosition] = viewBinding.ivApply
                }
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
                    if (isRefine) {
                        viewBinding.isChoose = !mainCategory.isChoose
                        listenerRefine?.invoke(Triple(adapterPosition, -1, -1),
                            TypeCategories.MAIN_CATEGORIES
                        )
                        viewParentCategories[adapterPosition] = viewBinding.ivApply
                    } else {
                        listener?.invoke(mainCategory, TypeCategories.MAIN_CATEGORIES,
                            Triple(adapterPosition, -1, -1)
                        )
                    }
                } else {
                    isExpandListener.invoke(Pair(adapterPosition, -1),
                        viewBinding.subView.expandOrCollapse()
                    )
                }
            }
        }

        private fun bindCategory(
            viewBinding: ItemAllCategoriesBinding, mainCategory: MainCategories?,
            parentPosition: Int, position: Int
        ) {
            viewBinding.mainCategories = mainCategory
            if (isRefine) {
                viewBinding.isChoose = mainCategory?.isChoose
                if (mainCategory?.isChoose == true) {
                    viewCategories[Pair(parentPosition, position)] = viewBinding.ivApply
                }
            }
            if (mainCategory?.childCategories?.isEmpty() == true || mainCategory?.childCategories?.size == 1) {
                viewBinding.ivRight.gone()
            } else {
                viewBinding.ivRight.visible()
                if (mainCategory?.isExpand == true) {
                    expandViewCategories(viewBinding, mainCategory, parentPosition, position)
                } else {
                    viewBinding.subView.gone()
                    viewBinding.ivRight.setBackgroundResource(R.drawable.ic_right_black_24dp)
                }
            }
            viewBinding.tvName.setOnClickListener {
                if (mainCategory?.childCategories?.isEmpty() == true || mainCategory?.childCategories?.size == 1) {
                    if (isRefine) {
                        clearChooseCategories(position, parentPosition, viewBinding)
                        viewBinding.isChoose = !mainCategory.isChoose
                        if (position == 0) {
                            listenerRefine?.invoke(Triple(parentPosition, position, -1),
                                TypeCategories.MAIN_CATEGORIES)
                        } else {
                            listenerRefine?.invoke(Triple(parentPosition, position, -1),
                                TypeCategories.CATEGORIES)
                        }
                    } else {
                        if (position == 0) {
                            listener?.invoke(mainCategories?.get(parentPosition),
                                TypeCategories.MAIN_CATEGORIES, Triple(parentPosition, position, -1)
                            )
                        } else {
                            listener?.invoke(mainCategory, TypeCategories.CATEGORIES,
                                Triple(parentPosition, position, -1)
                            )
                        }
                    }
                } else {
                    expandViewFilter(viewBinding, mainCategory, parentPosition, position)
                    isExpandListener.invoke(
                        Pair(parentPosition, position),
                        viewBinding.subView.expandOrCollapse()
                    )
                }
            }
        }

        private fun bindFilter(
            viewBinding: ItemAllCategoriesBinding, mainCategory: MainCategories?,
            position: Int, index: Int, lastIndex: Int
        ) {
            viewBinding.mainCategories = mainCategory
            viewBinding.ivRight.gone()
            if (isRefine) {
                viewBinding.isChoose = mainCategory?.isChoose
                if (mainCategory?.isChoose == true) {
                    viewFilterCategories[Triple(position, index, lastIndex)] = viewBinding.ivApply
                }
            }
            viewBinding.tvName.setOnClickListener {
                if (isRefine) {
                    clearChooseFilter(lastIndex, position, index, viewBinding)
                    viewCategories[Pair(position, 0)]?.gone()
                    viewBinding.isChoose = !(mainCategory?.isChoose ?: false)
                    if (lastIndex == 0) {
                        listenerRefine?.invoke(Triple(position, index, lastIndex),
                            TypeCategories.CATEGORIES)
                    } else {
                        listenerRefine?.invoke(Triple(position, index, lastIndex),
                            TypeCategories.FILTER)
                    }
                } else {
                    if (lastIndex == 0) {
                        listener?.invoke(mainCategories?.get(position)?.childCategories?.get(index),
                            TypeCategories.CATEGORIES, Triple(position, index, lastIndex))
                    } else {
                        listener?.invoke(mainCategory, TypeCategories.FILTER,
                            Triple(position, index, lastIndex)
                        )
                    }
                }
            }
        }

        private fun clearChooseCategories(
            position: Int,
            parentPosition: Int,
            viewBinding: ItemAllCategoriesBinding
        ) {
            if (position == 0) {
                viewCategories[Pair(parentPosition, position)] = viewBinding.ivApply
                viewCategories.forEach {
                    if (it.key.first == parentPosition && it.key.second > 0) {
                        it.value.gone()
                    }
                }
                viewFilterCategories.forEach {
                    if (it.key.first == parentPosition) {
                        it.value.gone()
                    }
                }
            } else {
                viewCategories[Pair(parentPosition, position)] = viewBinding.ivApply
                viewCategories[Pair(parentPosition, 0)]?.gone()
            }
        }

        private fun clearChooseFilter(
            lastIndex: Int,
            position: Int,
            index: Int,
            viewBinding: ItemAllCategoriesBinding
        ) {
            if (lastIndex == 0) {
                viewFilterCategories[Triple(position, index, lastIndex)] =
                    viewBinding.ivApply
                viewFilterCategories.forEach {
                    if (it.key.first == position && it.key.second == index && it.key.third > 0) {
                        it.value.gone()
                    }
                }
            } else {
                viewFilterCategories[Triple(position, index, lastIndex)] =
                    viewBinding.ivApply
                viewFilterCategories[Triple(position, index, 0)]?.gone()
            }
        }

        private fun listenerCategory(
            viewBinding: ViewDataBinding?, item: MainCategories,
            parentIndex: Int, index: Int
        ) {
            (viewBinding as? ItemAllCategoriesBinding)?.apply {
                bindCategory(this, item, parentIndex, index)
            }
        }

        private fun listenerFilter(
            viewBinding: ViewDataBinding?, item: MainCategories,
            parentIndex: Int, index: Int, lastIndex: Int
        ) {
            (viewBinding as? ItemAllCategoriesBinding)?.apply {
                bindFilter(this, item, parentIndex, index, lastIndex)
            }
        }

        private fun expandView(
            viewBinding: ItemAllCategoriesBinding, mainCategory: MainCategories,
            parentPosition: Int
        ) {
            viewBinding.subView.addViewExt(
                R.layout.item_all_categories,
                mainCategory.childCategories ?: arrayListOf(),
                viewBinding.subView.marginStart, parentPosition, this::listenerCategory
            )
            toggleDrawable(
                viewBinding.ivRight,
                expandOrCollapse(viewBinding.subView, mainCategory.isExpand)
            )
        }

        private fun expandViewCategories(
            viewBinding: ItemAllCategoriesBinding, mainCategory: MainCategories?,
            parentPosition: Int, position: Int
        ) {
            viewBinding.subView.addViewExt(
                R.layout.item_all_categories,
                mainCategory?.childCategories ?: arrayListOf(),
                viewBinding.subView.marginStart, parentPosition, position, this::listenerFilter
            )
            toggleDrawable(
                viewBinding.ivRight,
                expandOrCollapse(
                    viewBinding.subView,
                    mainCategory?.isExpand ?: false
                )
            )
        }

        private fun expandViewFilter(
            viewBinding: ItemAllCategoriesBinding, mainCategory: MainCategories?,
            parentPosition: Int, position: Int
        ) {
            viewBinding.subView.addViewExt(
                R.layout.item_all_categories,
                mainCategory?.childCategories ?: arrayListOf(),
                viewBinding.subView.marginStart, parentPosition, position, this::listenerFilter
            )
            expandOrCollapse(
                viewBinding.subView,
                mainCategory?.isExpand ?: false
            )
            toggleDrawable(viewBinding.ivRight, !(mainCategory?.isExpand ?: false))
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