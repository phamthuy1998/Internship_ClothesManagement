package com.ptithcm.admin.view.shop

import android.os.Bundle
import android.os.Handler
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ptithcm.core.model.Category
import com.ptithcm.core.model.Gender
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.KEY_ARGUMENT
import com.ptithcm.admin.databinding.FragmentBaseShopGenderBinding
import com.ptithcm.admin.ext.isShowErrorNetwork
import com.ptithcm.admin.ext.isShowLoading
import com.ptithcm.admin.ext.navigateAnimation
import com.ptithcm.admin.view.MainActivity
import com.ptithcm.admin.view.shop.adapter.CategoriesRecyclerViewAdapter
import com.ptithcm.admin.viewmodel.ShopViewModel
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

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        initCategoriesAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.networkState.observe(this, Observer {
            when (it) {
                true -> (requireActivity() as? MainActivity)?.isShowLoading(it)
                false -> {
                    Handler().postDelayed({
                        (requireActivity() as? MainActivity)?.isShowLoading(it)
                    }, 3000)
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