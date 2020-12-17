package com.n16dccn159.admin.view.allcategories

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.KEY_ARGUMENT
import com.n16dccn159.admin.constant.VIEW_ALL
import com.n16dccn159.admin.databinding.FragmentCategoriesRefineBinding
import com.n16dccn159.admin.ext.gone
import com.n16dccn159.admin.ext.initToolbar
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.ext.visible
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.allcategories.adapter.CategoriesRecyclerViewAdapter
import com.n16dccn159.admin.view.home.StoryDetailActivity
import com.n16dccn159.admin.viewmodel.ProductFilterViewModel
import com.n16dccn159.admin.viewmodel.RefineViewModel
import com.n16dccn159.core.model.MainCategories
import com.n16dccn159.core.model.TypeCategories
import com.n16dccn159.core.param.CategoriesRefine
import com.n16dccn159.core.param.RefineParam
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoriesRefineFragment : BaseFragment<FragmentCategoriesRefineBinding>(),
    View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_categories_refine

    private val viewModel: ProductFilterViewModel by sharedViewModel(from = { requireActivity() })
    private val viewModelRefine: RefineViewModel by sharedViewModel(from = { requireActivity() })

    private lateinit var adapter: CategoriesRecyclerViewAdapter
    private var refineParam : RefineParam? = null
    private var categories: ArrayList<CategoriesRefine>? = null
    val numberSelected = ObservableField(0)

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.data = this
        setupToolbar()
        initAdapter()
        initEvent()
        refineParam = arguments?.getParcelable(KEY_ARGUMENT)
        categories?.addAll(refineParam?.categories ?: arrayListOf())
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.categoriesRefineLiveData.observe(this, Observer {
            initChoose(it)
            if (it?.isNotEmpty() == true && it.size > 1){
                adapter.mainCategories = it
                adapter.notifyDataSetChanged()
            }
            funcView(!(it?.isNotEmpty() == true && it.size > 1))
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvClear -> {
                adapter.mainCategories?.mapIndexed { index, item ->
                    if (index > 0 && item.isChoose) {
                        item.isChoose = false
                        adapter.notifyItemChanged(index)
                    }
                }
                adapter.mainCategories?.first()?.isChoose = true
                adapter.notifyItemChanged(0)
                numberSelected.set(1)
            }

            R.id.btnApply -> {
                categoriesSelected()
                refineParam?.categories = categories
                navController.popBackStack()
            }
        }
    }

    private fun setupToolbar() {
        val title = getString(R.string.all_categories)
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar( hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(if (title.contains(VIEW_ALL)) title.substringAfterLast(VIEW_ALL) else title)
            when(this){
                is MainActivity -> {
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@CategoriesRefineFragment)
                }
                is StoryDetailActivity -> {
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@CategoriesRefineFragment)
                }
            }
        }
    }

    private fun initEvent() {
        viewBinding.btnApply.setOnClickListener(this)
    }

    private fun initAdapter() {
        adapter = CategoriesRecyclerViewAdapter(
            arrayListOf(),
            listenerRefine = this::listenerRefine
        )
        viewBinding.rvCategories.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.rvCategories.adapter = adapter
    }

    private fun listenerRefine(position: Int) {
        adapter.mainCategories?.get(position)?.isChoose =
            !(adapter.mainCategories?.get(position)?.isChoose ?: false)
        adapter.notifyItemChanged(position)
        if (position == 0) {
            adapter.mainCategories?.mapIndexed { index, item ->
                if (index > 0) {
                    item.isChoose = false
                    adapter.notifyItemChanged(index)
                }
            }
        } else {
            adapter.mainCategories?.get(0)?.isChoose = false
            adapter.notifyItemChanged(0)
        }
        numberSelected.set(adapter.mainCategories?.filter { it.isChoose }?.size)
    }

    private fun initChoose(arrayList: ArrayList<MainCategories>) {
        if (arrayList.isNotEmpty()) {
            arrayList.map { mainCategory ->
                categories?.forEach {
                    mainCategory.isChoose = it.id == mainCategory.value
                }
            }
            numberSelected.set(categories?.size)
        }
    }

    private fun categoriesSelected() {
        if (viewBinding.rvCategories.isVisible) {
            categories = arrayListOf()
            adapter.mainCategories?.forEachIndexed { index, mainCategories ->
                if (mainCategories.isChoose) {
                    if (index == 0) {
                        categories?.add(
                            mainCategories.copyCategoryRefine(
                                index,
                                TypeCategories.CATEGORIES
                            )
                        )
                    } else {
                        categories?.add(
                            mainCategories.copyCategoryRefine(
                                index,
                                TypeCategories.FILTER
                            )
                        )
                    }
                }
            }
        }
    }

    private fun funcView(isEmpty : Boolean){
        when(isEmpty){
            true -> {
                viewBinding.tvEmpty.visible()
                viewBinding.rvCategories.gone()
            }
            else -> {
                viewBinding.tvEmpty.gone()
                viewBinding.rvCategories.visible()
            }
        }
    }
}