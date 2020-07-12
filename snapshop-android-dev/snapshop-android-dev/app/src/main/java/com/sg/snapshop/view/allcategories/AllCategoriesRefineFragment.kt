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
import com.sg.snapshop.databinding.FragmentAllCategoriesRefineBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.initToolbar
import com.sg.snapshop.ext.refine.clearAllChooseCategories
import com.sg.snapshop.ext.refine.eventRefineParam
import com.sg.snapshop.ext.refine.getItemCategory
import com.sg.snapshop.ext.refine.removeItemDefaultCategory
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.ext.visible
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.allcategories.adapter.AllCategoriesRecyclerViewAdapter
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.viewmodel.ProductFilterViewModel
import com.sg.snapshop.viewmodel.RefineViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AllCategoriesRefineFragment : BaseFragment<FragmentAllCategoriesRefineBinding>(),
    View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_all_categories_refine

    private val viewModel: ProductFilterViewModel by sharedViewModel(from = { requireActivity() })
    private val viewModelRefine: RefineViewModel by sharedViewModel(from = { requireActivity() })

    private lateinit var adapter: AllCategoriesRecyclerViewAdapter

    private var refineParam : RefineParam? = null
    private var categoriesSelected : ArrayList<CategoriesRefine>? = null
    val numberSelected = ObservableField(0)

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.data = this
        categoriesSelected = arrayListOf()
        setupToolbar()
        initAdapter()
        initEvent()
        viewModel.requestAllCategories(null)
        refineParam = arguments?.getParcelable(KEY_ARGUMENT)
    }

    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.allCategoriesRefineLiveData.observe(this, Observer {
            initChoose(it)
            categoriesSelected?.addAll(refineParam?.categories ?: arrayListOf())
            adapter.mainCategories = it
            adapter.notifyDataSetChanged()
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvClear -> {
                clearAllChooseCategories(adapter.mainCategories, categoriesSelected)
                categoriesSelected = arrayListOf()
                adapter.clearView()
                numberSelected.set(0)
            }

            R.id.btnApply -> {
                refineParam?.categories = categoriesSelected
                viewModelRefine.refineLiveData.value = Pair(refineParam, false)
                navController.popBackStack()
            }
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(getString(R.string.all_categories))
            when(this){
                is MainActivity -> {
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@AllCategoriesRefineFragment)
                }
                is StoryDetailActivity -> {
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@AllCategoriesRefineFragment)
                }
            }
        }
    }

    private fun initEvent() {
        viewBinding.btnApply.setOnClickListener(this)
    }

    private fun initChoose(mainCategories: ArrayList<MainCategories>?) {
        refineParam?.categories?.forEach { item ->
            eventCategories(mainCategories, item.position)
            item.name = getItemCategory(mainCategories, item.position)?.text
        }
        numberSelected.set(refineParam?.categories?.size)
    }

    private fun initAdapter() {
        adapter = AllCategoriesRecyclerViewAdapter(
            arrayListOf(), true,
            this::isExpandListener, listener = null, listenerRefine = this::listenerRefine
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
        positionTriple: Triple<Int, Int, Int>, type: TypeCategories
    ) {
        removeItemDefaultCategory(adapter.mainCategories, positionTriple, categoriesSelected)
        eventCategories(adapter.mainCategories, positionTriple)
        eventRefineParam(adapter.mainCategories, positionTriple, categoriesSelected, type)
        numberSelected.set(categoriesSelected?.size)
    }

    private fun eventCategories(
        mainCategories: ArrayList<MainCategories>?, positionTriple: Triple<Int, Int, Int>
    ) {
        getItemCategory(mainCategories, positionTriple)?.isChoose =
            !(getItemCategory(mainCategories, positionTriple)?.isChoose ?: false)
    }


}