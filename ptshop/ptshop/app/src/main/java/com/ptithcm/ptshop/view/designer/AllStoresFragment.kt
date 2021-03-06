package com.ptithcm.ptshop.view.designer

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.jay.widget.StickyHeadersLinearLayoutManager
import com.ptithcm.core.model.Designer
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentAllDesignersBinding
import com.ptithcm.ptshop.ext.gone
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.navigateAnimation
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.designer.adapter.AllStoresRecyclerViewAdapter
import com.ptithcm.ptshop.viewmodel.BrandsViewModel
import com.ptithcm.ptshop.widget.fastscroll.FastScrollRecyclerViewItemDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AllStoresFragment : BaseFragment<FragmentAllDesignersBinding>() {

    override val layoutId = R.layout.fragment_all_designers

    private val viewModel: BrandsViewModel by sharedViewModel(from = { requireActivity() })

    private lateinit var adapter: AllStoresRecyclerViewAdapter

    override fun bindEvent() {
        setupToolbar()
        initAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.pagedList.observe(this, Observer {
            adapter.mapIndex = viewModel.indexHeaderMap
            adapter.brands = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.gone()
            initToolBar(
                viewBinding.layoutToolbar.toolbar, true, hasBackRight = false
            )
            setupToolbar(
                viewBinding.layoutToolbar.toolbar, getString(R.string.all_stores),
                messageQueue = this@AllStoresFragment::onClickToolbar
            )
        }
    }

    private fun onClickToolbar(view: View) {
        when (view.id) {
            R.id.ivRight -> {
                navController.navigateAnimation(
                    R.id.nav_shopping_card,
                    bundle = bundleOf(KEY_ARGUMENT to true)
                )
            }
            R.id.ivLeft -> {
                navController.navigateAnimation(R.id.nav_search)
            }
        }
    }

    private fun initAdapter() {
        adapter = AllStoresRecyclerViewAdapter(arrayListOf(), hashMapOf(), this::listener)
        val decoration = FastScrollRecyclerViewItemDecoration()
        viewBinding.rvDesigners.layoutManager =
            StickyHeadersLinearLayoutManager<AllStoresRecyclerViewAdapter>(requireContext())
//        viewBinding.rvDesigners.layoutManager= LinearLayoutManager(context)
        viewBinding.rvDesigners.addItemDecoration(decoration)
        viewBinding.rvDesigners.adapter = adapter
    }

    private fun listener(designer: Designer?) {
        navController.navigate(
            R.id.action_carousel_detail,
            bundleOf(KEY_ARGUMENT to designer?.copyCarousel())
        )
    }

}
