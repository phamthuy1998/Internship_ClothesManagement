package com.ptithcm.ptshop.view.allcategories

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.MainCategories
import com.ptithcm.core.model.TypeCategories
import com.ptithcm.core.param.CategoriesRefine
import com.ptithcm.core.param.RefineParam
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.VIEW_ALL
import com.ptithcm.ptshop.databinding.FragmentMainCategoriesRefineBinding
import com.ptithcm.ptshop.ext.initToolbar
import com.ptithcm.ptshop.ext.refine.clearChooseCategories
import com.ptithcm.ptshop.ext.refine.eventRefineParam
import com.ptithcm.ptshop.ext.refine.getItemCategory
import com.ptithcm.ptshop.ext.refine.removeItemMainCategory
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.ext.visible
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.allcategories.adapter.MainCategoriesRecyclerViewAdapter
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.viewmodel.ProductFilterViewModel
import com.ptithcm.ptshop.viewmodel.RefineViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainCategoriesRefineFragment : BaseFragment<FragmentMainCategoriesRefineBinding>(),
    View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_main_categories_refine

    private val viewModel: ProductFilterViewModel by sharedViewModel(from = { requireActivity() })
    private val viewModelRefine: RefineViewModel by sharedViewModel(from = { requireActivity() })

    private lateinit var adapter: MainCategoriesRecyclerViewAdapter
    private var refineParam: RefineParam? = null
    private var categoriesSelected: ArrayList<CategoriesRefine>? = null
    val numberSelected = ObservableField(0)

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.data = this
        categoriesSelected = arrayListOf()
        setupToolbar()
        initAdapter()
        initEvent()
        refineParam = arguments?.getParcelable(KEY_ARGUMENT)
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.categoriesMainRefineLiveData.observe(this, Observer {
            initChoose(it)
            categoriesSelected?.addAll(refineParam?.categories ?: arrayListOf())
            adapter.mainCategories = it
            adapter.notifyDataSetChanged()
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvClear -> {
                clearChooseCategories(adapter.mainCategories, categoriesSelected)
                categoriesSelected = arrayListOf()
                eventRefineParam(
                    adapter.mainCategories, Triple(0, -1, -1),
                    categoriesSelected, TypeCategories.MAIN_CATEGORIES
                )
                adapter.clearView()
                adapter.notifyItemChanged(0)
                numberSelected.set(categoriesSelected?.size)
            }

            R.id.btnApply -> {
                refineParam?.categories = categoriesSelected
                navController.popBackStack()
            }
        }
    }

    private fun setupToolbar() {
        val title = getString(R.string.all_categories)
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(if (title.contains(VIEW_ALL)) title.substringAfterLast(VIEW_ALL) else title)
            when(this){
                is MainActivity -> {
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@MainCategoriesRefineFragment)
                }
                is StoryDetailActivity -> {
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@MainCategoriesRefineFragment)
                }
            }
        }
    }

    private fun initEvent() {
        viewBinding.btnApply.setOnClickListener(this)
    }

    private fun initAdapter() {
        adapter = MainCategoriesRecyclerViewAdapter(
            arrayListOf(),
            this::isExpandListener,
            listenerRefine = this::listenerRefine
        )
        viewBinding.rvCategories.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.rvCategories.adapter = adapter
    }

    private fun isExpandListener(position: Pair<Int, Int>, isExpand: Boolean) {
        if (position.second > -1) {
            adapter.mainCategories?.get(position.first)?.childCategories?.get(position.second)
                ?.isExpand = isExpand
        } else {
            adapter.mainCategories?.get(position.first)?.isExpand = isExpand
            adapter.notifyItemChanged(position.first)
        }
    }

    private fun listenerRefine(
        positionPair: Triple<Int, Int, Int>, type: TypeCategories
    ) {
        removeItemMainCategory(adapter.mainCategories, positionPair, categoriesSelected)
        eventCategories(adapter.mainCategories, positionPair)
        eventRefineParam(adapter.mainCategories, positionPair, categoriesSelected, type)
        numberSelected.set(categoriesSelected?.size)
    }

    private fun eventCategories(
        mainCategories: ArrayList<MainCategories>?, positionTriple: Triple<Int, Int, Int>
    ) {
        getItemCategory(mainCategories, positionTriple)?.isChoose =
            !(getItemCategory(mainCategories, positionTriple)?.isChoose ?: false)
    }

    private fun initChoose(arrayList: ArrayList<MainCategories>) {
        refineParam?.categories?.forEach {
            getItemCategory(arrayList, it.position)?.isChoose = true
        }
        numberSelected.set(refineParam?.categories?.size)
    }

}