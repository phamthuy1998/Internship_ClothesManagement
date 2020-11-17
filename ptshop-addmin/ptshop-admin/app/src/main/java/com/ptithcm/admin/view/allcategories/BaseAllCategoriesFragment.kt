package com.ptithcm.admin.view.allcategories

import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Categories
import com.ptithcm.core.model.Gender
import com.ptithcm.core.model.MainCategories
import com.ptithcm.core.model.TypeCategories
import com.ptithcm.core.param.CategoriesParam
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.IS_PRODUCT
import com.ptithcm.admin.constant.KEY_ARGUMENT
import com.ptithcm.admin.constant.KEY_BANNER_CATEGORY
import com.ptithcm.admin.databinding.FragmentBaseAllCategoriesGenderBinding
import com.ptithcm.admin.ext.getBannerCategory
import com.ptithcm.admin.view.allcategories.adapter.AllCategoriesRecyclerViewAdapter
import com.ptithcm.admin.viewmodel.ProductFilterViewModel
import com.ptithcm.admin.viewmodel.ShopViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseAllCategoriesFragment : BaseFragment<FragmentBaseAllCategoriesGenderBinding>() {

    override val layoutId: Int = R.layout.fragment_base_all_categories_gender

    abstract fun getAllCategoriesType(): Gender

    private val viewModel: ProductFilterViewModel by sharedViewModel(from = { requireActivity() })
    private val viewModelCategories: ShopViewModel by viewModel()

    private lateinit var adapter: AllCategoriesRecyclerViewAdapter
    private var categories: ArrayList<Categories>? = null
    private var banner: String? = null

    fun reloadPage() {
        viewModel.requestAllCategories(getAllCategoriesType())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.requestAllCategories(getTypeGender())
        viewModelCategories.getMainCategories(getTypeGender().value)
    }

    override fun bindEvent() {
        super.bindEvent()
        initAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.allCategoriesLiveData.observe(this, Observer {
            adapter.mainCategories = it
            adapter.notifyDataSetChanged()
        })

        viewModelCategories.categoriesLiveData.observe(this, Observer {
            categories = arrayListOf()
//            categories?.addAll(it)
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initAdapter() {
        adapter = AllCategoriesRecyclerViewAdapter(arrayListOf(), false,
            this::isExpandListener,
            this::eventListener
        )
        viewBinding.rvAllCategories.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.rvAllCategories.adapter = adapter
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

    private fun eventListener(
        mainCategory: MainCategories?,
        type: TypeCategories,
        position: Triple<Int, Int, Int>
    ) {
        val categoriesParam = CategoriesParam(
            gender = getAllCategoriesType().value,
            name = mainCategory?.text, typeCategories = type, position = position
        )
        when (type) {
            TypeCategories.MAIN_CATEGORIES -> {
                categoriesParam.position = Triple(0, -1, -1)
                categoriesParam.main_category_id = mainCategory?.value ?: 0
                banner = categories?.getBannerCategory(mainCategory?.value, getTypeGender())
            }
            TypeCategories.CATEGORIES -> {
                categoriesParam.category_id = mainCategory?.value ?: 0
            }
            else -> {
                categoriesParam.filter_id = mainCategory?.value ?: 0
            }
        }
        navController.navigate(
            R.id.action_categories_detail, bundleOf(
                KEY_ARGUMENT to categoriesParam, IS_PRODUCT to false,
                KEY_BANNER_CATEGORY to banner
            )
        )
    }

    private fun getTypeGender(): Gender {
        return when (parentFragment?.arguments?.getInt(KEY_ARGUMENT)) {
            0 -> Gender.WOMEN
            1 -> Gender.MEN
            2 -> Gender.KIDS
            else -> Gender.UNISEX
        }
    }
}