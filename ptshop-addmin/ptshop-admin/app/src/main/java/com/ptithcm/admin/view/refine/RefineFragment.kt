package com.ptithcm.admin.view.refine

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseActivity
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.*
import com.ptithcm.admin.databinding.FragmentRefineBinding
import com.ptithcm.admin.ext.*
import com.ptithcm.admin.view.MainActivity
import com.ptithcm.admin.view.home.StoryDetailActivity
import com.ptithcm.admin.viewmodel.RefineViewModel
import com.ptithcm.core.model.Filter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RefineFragment : BaseFragment<FragmentRefineBinding>(), View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_refine

    private val viewModel: RefineViewModel by sharedViewModel(from = { requireActivity() })

    private var filterParam: Filter? = null
    private var isShowFilterBy: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.filterLiveData.value = null

        arguments?.run {
            filterParam = clone(getParcelable(KEY_SEARCH))
            isShowFilterBy = getBoolean(KEY_IS_SHOW_FILTER_BY, false)
        }
    }

    override fun bindEvent() {
        setupToolbar()
        viewBinding.gFilter.isVisible = isShowFilterBy
        initEvent()
        init(filterParam)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvClear -> {
                filterParam?.clearData()
                clearView()
            }
            R.id.tvCategories -> {
//                if (isProduct) {
//                    navController.navigate(
//                        R.id.action_fragment_all_categories_refine,
//                        bundleOf(KEY_ARGUMENT to refineParamRequest)
//                    )
//                } else {
//                    if (isMainCategory) {
//                        navController.navigate(
//                            R.id.action_fragment_main_categories_refine,
//                            bundleOf(KEY_ARGUMENT to refineParamRequest)
//                        )
//                    } else {
//                        navController.navigate(
//                            R.id.action_fragment_categories_refine,
//                            bundleOf(KEY_ARGUMENT to refineParamRequest)
//                        )
//                    }
//                }
            }
            R.id.tvBrands -> {
//                if (refineParam?.storiesRefine?.isStories == true) {
//                    navController.navigateAnimation(
//                        R.id.fragment_brand_refine_stories,
//                        bundle = bundleOf(KEY_ARGUMENT to refineParamRequest)
//                    )
//                } else {
//                    navController.navigateAnimation(
//                        R.id.fragment_brand_refine,
//                        bundle = bundleOf(KEY_ARGUMENT to refineParamRequest)
//                    )
//                }
            }
            R.id.btnShowResult -> {
                val typeFilter: Int? = when {
                    viewBinding.ivRightPriceHigh.isSelected -> PRICE_DESC
                    viewBinding.ivRightPriceLow.isSelected -> PRICE_ASC
                    viewBinding.ivRightNewItems.isSelected -> NEWEST
                    viewBinding.ivRightOldItems.isSelected -> OLDEST
                    else -> null
                }
                filterParam?.run {
                    this.sortBy = typeFilter
                }
                viewModel.filterLiveData.value = Pair(filterParam, true)

                navController.popBackStack()
            }
            R.id.tvNewItems -> {
                viewBinding.ivRightNewItems.isSelected = !viewBinding.ivRightNewItems.isSelected
                viewBinding.chooseView(isNewItems = viewBinding.ivRightNewItems.isSelected)
            }
            R.id.tvOldItems -> {
                viewBinding.ivRightOldItems.isSelected = !viewBinding.ivRightOldItems.isSelected
                viewBinding.chooseView(isOurPicks = viewBinding.ivRightOldItems.isSelected)
            }
            R.id.tvPriceHigh -> {
                viewBinding.ivRightPriceHigh.isSelected = !viewBinding.ivRightPriceHigh.isSelected
                viewBinding.chooseView(isPriceHigh = viewBinding.ivRightPriceHigh.isSelected)
            }
            R.id.tvPriceLow -> {
                viewBinding.ivRightPriceLow.isSelected = !viewBinding.ivRightPriceLow.isSelected
                viewBinding.chooseView(isPriceLow = viewBinding.ivRightPriceLow.isSelected)
            }
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(hasBackRight = false, hasRight = false, hasLeft = false)
            setupToolbar(
                getString(R.string.refine),
                isBackPress = false, messageQueue = this@RefineFragment::onClickToolbarEvent
            )
            when (this) {
                is MainActivity -> {
                    viewBinding.layoutToolbar.ivBack.setImageResource(R.drawable.ic_black_close)
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@RefineFragment)
                }
                is StoryDetailActivity -> {
                    viewBinding.layoutToolbar.ivBack.setImageResource(R.drawable.ic_black_close)
                    viewBinding.layoutToolbar.tvClear.visible()
                    viewBinding.layoutToolbar.tvClear.setOnClickListener(this@RefineFragment)
                }
            }
        }
    }

    private fun onClickToolbarEvent(view: View) {
        when (view.id) {
            R.id.ivBack -> {
                viewModel.filterLiveData.value = Pair(filterParam, false)
                navController.popBackStack()
            }
        }
    }

    private fun init(filter: Filter?) {
        when (filter?.sortBy) {
            PRICE_ASC -> viewBinding.ivRightPriceLow.isSelected = true
            PRICE_DESC -> viewBinding.ivRightPriceHigh.isSelected = true
            NEWEST -> viewBinding.ivRightNewItems.isSelected = true
            OLDEST -> viewBinding.ivRightOldItems.isSelected = true
        }
    }

    private fun initEvent() {
        viewBinding.tvCategories.setOnClickListener(this)
        viewBinding.tvBrands.setOnClickListener(this)
        viewBinding.tvNewItems.setOnClickListener(this)
        viewBinding.tvOldItems.setOnClickListener(this)
        viewBinding.tvPriceHigh.setOnClickListener(this)
        viewBinding.tvPriceLow.setOnClickListener(this)
        viewBinding.btnShowResult.setOnClickListener(this)
    }

    private fun clearView() {
        viewBinding.ivRightNewItems.isSelected = false
        viewBinding.ivRightOldItems.isSelected = false
        viewBinding.ivRightPriceLow.isSelected = false
        viewBinding.ivRightPriceHigh.isSelected = false
    }
}