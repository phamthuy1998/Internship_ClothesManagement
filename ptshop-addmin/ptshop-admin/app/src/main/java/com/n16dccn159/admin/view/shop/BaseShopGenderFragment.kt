package com.n16dccn159.admin.view.shop

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.KEY_ARGUMENT
import com.n16dccn159.admin.databinding.FragmentBaseShopGenderBinding
import com.n16dccn159.admin.ext.isShowErrorNetwork
import com.n16dccn159.admin.ext.isShowLoading
import com.n16dccn159.admin.ext.navigateAnimation
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.shop.adapter.CategoriesRecyclerViewAdapter
import com.n16dccn159.admin.viewmodel.ShopViewModel
import com.n16dccn159.core.model.Category
import com.n16dccn159.core.model.Gender
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseShopGenderFragment : BaseFragment<FragmentBaseShopGenderBinding>() {

    override val layoutId: Int = R.layout.fragment_base_shop_gender

    abstract fun getShopType(): Gender

    private val viewModel: ShopViewModel by viewModel()
    private lateinit var categoriesAdapter: CategoriesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMainCategories(getShopType().value)
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
        viewModel.networkState.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowLoading(it)
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