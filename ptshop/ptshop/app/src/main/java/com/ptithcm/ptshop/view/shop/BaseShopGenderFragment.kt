package com.ptithcm.ptshop.view.shop

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Category
import com.ptithcm.core.model.Gender
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentBaseShopGenderBinding
import com.ptithcm.ptshop.ext.isShowErrorNetwork
import com.ptithcm.ptshop.ext.isShowLoading
import com.ptithcm.ptshop.ext.navigateAnimation
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.shop.adapter.CategoriesRecyclerViewAdapter
import com.ptithcm.ptshop.viewmodel.ShopViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseShopGenderFragment : BaseFragment<FragmentBaseShopGenderBinding>() {

    override val layoutId: Int = R.layout.fragment_base_shop_gender

    abstract fun getShopType(): Gender

    private val viewModel: ShopViewModel by viewModel()
    private lateinit var categoriesAdapter: CategoriesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMainCategories(getShopType().value)
        viewModel.getShopInfo()
    }


    open fun onRefreshData() {
        viewModel.getMainCategories(getShopType().value)
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        initCategoriesAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.shopInfo.observe(this, Observer {
            if (it != null) CoreApplication.instance.shopInfo = it
        })

        viewModel.networkState.observe(this, Observer {
            when (it) {
                true -> (requireActivity() as? MainActivity)?.isShowLoading(it)
                false -> {
                    (requireActivity() as? MainActivity)?.isShowLoading(it)
                }
            }
        })

        viewModel.categoriesLiveData.observe(this, Observer {
            if (it != null) {
                categoriesAdapter.setCategories(it, getShopType())
            }
        })

        viewModel.error.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
        })
    }

    private fun initCategoriesAdapter() {
        categoriesAdapter =
            CategoriesRecyclerViewAdapter(arrayListOf(), getShopType(), this::listenerCategories)
        val layoutManager = GridLayoutManager(requireContext(), 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val category = categoriesAdapter.getCategories()?.get(position)
                if (category?.isSection == false) {
                    return 1
                }
                return category?.countSection ?: 3
            }
        }
        viewBinding.rvPopularCategories.layoutManager = layoutManager
        viewBinding.rvPopularCategories.adapter = categoriesAdapter
    }

    // On click item category
    private fun listenerCategories(category: Category?) {
        navController.navigateAnimation(
            R.id.nav_categories_detail, bundle =
            bundleOf(KEY_ARGUMENT to category)
        )
    }
}