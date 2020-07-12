package com.sg.snapshop.view.refine

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.jay.widget.StickyHeadersLinearLayoutManager
import com.sg.core.model.Designer
import com.sg.core.param.BrandsRefine
import com.sg.core.param.RefineParam
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.databinding.FragmentRefineBrandsBinding
import com.sg.snapshop.widget.fastscroll.FastScrollRecyclerViewItemDecoration
import com.sg.snapshop.ext.initToolbar
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.ext.visible
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.view.refine.adapter.BrandRecyclerViewAdapter
import com.sg.snapshop.viewmodel.BrandsViewModel
import com.sg.snapshop.viewmodel.RefineViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RefineBrandsFragment : BaseFragment<FragmentRefineBrandsBinding>(), View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_refine_brands

    private val viewModel: BrandsViewModel by sharedViewModel(from = { requireActivity() })
    private val refineViewModel: RefineViewModel by sharedViewModel(from = { requireActivity() })

    private lateinit var adapter: BrandRecyclerViewAdapter
    val numberSelected = ObservableField(0)
    private var refineParam: RefineParam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refineParam = arguments?.getParcelable(KEY_ARGUMENT)
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.data = this
        setupToolbar()
        initAdapter()
        initEvent()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.brandsRefineLiveData.observe(this, Observer {
            initChoose(it, refineParam)
            adapter.brands = it
            adapter.mapIndex = viewModel.calculateIndexesForName(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvClear -> {
                clearBrandSelected()
            }
            R.id.btnApply -> {
                brandsSelected()
                refineViewModel.refineLiveData.value = Pair(refineParam ?: RefineParam(), false)
                navController.popBackStack()
            }
        }
    }

    private fun initEvent() {
        viewBinding.btnApply.setOnClickListener(this)
    }

    private fun setupToolbar() {
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(getString(R.string.brands))
            when (this) {
                is MainActivity -> viewBinding.layoutToolbar.tvClear.apply {
                    visible()
                    setOnClickListener(this@RefineBrandsFragment)
                }
                is StoryDetailActivity -> viewBinding.layoutToolbar.tvClear.apply {
                    visible()
                    setOnClickListener(this@RefineBrandsFragment)
                }
            }
        }
    }

    private fun initAdapter() {
        adapter = BrandRecyclerViewAdapter(arrayListOf(), hashMapOf(), this::listener)
        val decoration = FastScrollRecyclerViewItemDecoration()
        viewBinding.rvBrands.layoutManager =
            StickyHeadersLinearLayoutManager<BrandRecyclerViewAdapter>(requireContext())
        viewBinding.rvBrands.addItemDecoration(decoration)
        viewBinding.rvBrands.adapter = adapter
    }


    private fun listener(designer: Designer?, position: Int) {
        adapter.brands?.get(position)?.isChoose = !(designer?.isChoose ?: false)
        adapter.notifyItemChanged(position)
        numberSelected.set(adapter.brands?.filter { it.isChoose }?.size)
    }

    private fun initChoose(designers: ArrayList<Designer>, refineParam: RefineParam?) {
        designers.map {
            if (!it.isSection) {
                it.isChoose = refineParam?.brands?.contains(
                    BrandsRefine(
                        brandId = it.brandId,
                        storeId = it.storeId,
                        name = it.name
                    )
                ) == true
            }
        }
        numberSelected.set(refineParam?.brands?.size)
    }

    private fun brandsSelected() {
        refineParam?.brands = arrayListOf()
        adapter.brands?.forEach {
            if (it.isChoose) {
                refineParam?.brands?.add(
                    BrandsRefine(
                        brandId = it.brandId,
                        storeId = it.storeId,
                        name = it.name
                    )
                )
            }
        }
    }

    private fun clearBrandSelected() {
        adapter.brands?.mapIndexed { index, designer ->
            if (designer.isChoose) {
                designer.isChoose = false
                adapter.notifyItemChanged(index)
            }
        }
        numberSelected.set(0)
    }
}