package com.sg.snapshop.view.allcategories

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.model.MainCategories
import com.sg.core.model.TypeCategories
import com.sg.core.param.CategoriesRefine
import com.sg.core.param.RefineParam
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.constant.VIEW_ALL
import com.sg.snapshop.databinding.FragmentMainCategoriesRefineBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.initToolbar
import com.sg.snapshop.ext.refine.clearChooseCategories
import com.sg.snapshop.ext.refine.eventRefineParam
import com.sg.snapshop.ext.refine.getItemCategory
import com.sg.snapshop.ext.refine.removeItemMainCategory
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.ext.visible
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.allcategories.adapter.MainCategoriesRecyclerViewAdapter
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.viewmodel.ProductFilterViewModel
import com.sg.snapshop.viewmodel.RefineViewModel
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
        viewModelRefine.categoriesParamLiveData.observe(this, Observer {
            viewModel.requestMainCategories(it.first.copyCategoryRefine(), it.second)
        })
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
                viewModelRefine.refineLiveData.value = Pair(refineParam, false)
                navController.popBackStack()
            }
        }
    }

    private fun setupToolbar() {
        val title = viewModelRefine.categoriesParamLiveData.value?.first?.name
            ?: getString(R.string.all_categories)
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