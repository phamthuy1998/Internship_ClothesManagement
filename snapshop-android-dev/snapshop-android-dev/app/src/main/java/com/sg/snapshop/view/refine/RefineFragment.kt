package com.sg.snapshop.view.refine

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.sg.core.param.CategoriesParam
import com.sg.core.param.CategoriesRefine
import com.sg.core.param.RefineParam
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.IS_PRODUCT
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.constant.KEY_EMPTY
import com.sg.snapshop.constant.KEY_MAIN_CATEGORIES
import com.sg.snapshop.databinding.FragmentRefineBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.viewmodel.RefineViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RefineFragment : BaseFragment<FragmentRefineBinding>(), View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_refine

    private val viewModel: RefineViewModel by sharedViewModel(from = { requireActivity() })
    private var refineParam: RefineParam? = null
    private var refineParamRequest: RefineParam? = null
    private var isProduct: Boolean = false
    private var isMainCategory: Boolean = false
    private var categoriesParam: CategoriesParam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refineLiveData.value = null
        refineParam = arguments?.getParcelable(KEY_ARGUMENT)
        refineParamRequest = refineParam?.copy()
        isProduct = arguments?.getBoolean(IS_PRODUCT) ?: false
        if (!isProduct) {
            isMainCategory = arguments?.getBoolean(KEY_MAIN_CATEGORIES) ?: false
        }
    }

    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
        initEvent()
        init(refineParam)
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.categoriesParamLiveData.observe(this, Observer {
            categoriesParam = it.first
        })
        viewModel.refineLiveData.observe(this, Observer {
            it?.let {
                if (!it.second) {
                    refineParamRequest = it.first
                    init(refineParamRequest)
                }
            }
        })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvClear -> {
                refineParamRequest?.clearData(
                    isProduct, categories = arrayListOf(
                        categoriesParam?.copyCategoryRefine() ?: CategoriesRefine()
                    )
                )
                clearView()
            }
            R.id.tvColours -> {
                navController.navigate(
                    R.id.action_fragment_colours,
                    bundleOf(KEY_ARGUMENT to refineParamRequest)
                )
            }
            R.id.tvSizes -> {
                navController.navigate(
                    R.id.action_fragment_sizes,
                    bundleOf(KEY_ARGUMENT to refineParamRequest)
                )
            }
            R.id.tvGender -> {
                navController.navigate(
                    R.id.action_fragment_gender,
                    bundleOf(KEY_ARGUMENT to refineParamRequest)
                )
            }
            R.id.tvCategories -> {
                if (isProduct) {
                    navController.navigate(
                        R.id.action_fragment_all_categories_refine,
                        bundleOf(KEY_ARGUMENT to refineParamRequest)
                    )
                } else {
                    if (isMainCategory) {
                        navController.navigate(
                            R.id.action_fragment_main_categories_refine,
                            bundleOf(KEY_ARGUMENT to refineParamRequest)
                        )
                    } else {
                        navController.navigate(
                            R.id.action_fragment_categories_refine,
                            bundleOf(KEY_ARGUMENT to refineParamRequest)
                        )
                    }
                }
            }
            R.id.tvBrands -> {
                if (refineParam?.storiesRefine?.isStories == true) {
                    navController.navigateAnimation(
                        R.id.fragment_brand_refine_stories,
                        bundle = bundleOf(KEY_ARGUMENT to refineParamRequest)
                    )
                } else {
                    navController.navigateAnimation(
                        R.id.fragment_brand_refine,
                        bundle = bundleOf(KEY_ARGUMENT to refineParamRequest)
                    )
                }
            }
            R.id.btnShowResult -> {
                refineParamRequest?.newItems = viewBinding.ivRightNewItems.isSelected
                refineParamRequest?.ourPicks = viewBinding.ivRightOurPicks.isSelected
                refineParamRequest?.priceHigh = viewBinding.ivRightPriceHigh.isSelected
                refineParamRequest?.priceLow = viewBinding.ivRightPriceLow.isSelected
                viewModel.refineLiveData.value = Pair(refineParamRequest, true)
                navController.popBackStack()
            }
            R.id.tvNewItems -> {
                viewBinding.ivRightNewItems.isSelected = !viewBinding.ivRightNewItems.isSelected
                viewBinding.chooseView(isNewItems = viewBinding.ivRightNewItems.isSelected)
            }
            R.id.tvOurPicks -> {
                viewBinding.ivRightOurPicks.isSelected = !viewBinding.ivRightOurPicks.isSelected
                viewBinding.chooseView(isOurPicks = viewBinding.ivRightOurPicks.isSelected)
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
            setupToolbar(getString(R.string.refine),
                isBackPress = false, messageQueue = this@RefineFragment::onClickToolbarEvent)
            when(this){
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
                viewModel.refineLiveData.value = Pair(refineParam, false)
                navController.popBackStack()
            }
        }
    }

    private fun init(refineParam: RefineParam?) {
        viewBinding.tvRefineGender.gendersRefine(refineParam?.gender)
        viewBinding.tvRefineColours.coloursRefine(refineParam?.colours)
        viewBinding.tvRefineSizes.sizesRefine(refineParam?.sizes)
        viewBinding.tvRefineCategories.sizeCategories(refineParam?.categories)
        viewBinding.tvRefineBrands.sizeBrands(refineParam?.brands)
        when {
            refineParam?.newItems == true -> viewBinding.ivRightNewItems.isSelected = true
            refineParam?.ourPicks == true -> viewBinding.ivRightOurPicks.isSelected = true
            refineParam?.priceHigh == true -> viewBinding.ivRightPriceHigh.isSelected = true
            refineParam?.priceLow == true -> viewBinding.ivRightPriceLow.isSelected = true
        }
    }

    private fun initEvent() {
        viewBinding.tvCategories.setOnClickListener(this)
        viewBinding.tvBrands.setOnClickListener(this)
        viewBinding.tvColours.setOnClickListener(this)
        viewBinding.tvSizes.setOnClickListener(this)
        viewBinding.tvGender.setOnClickListener(this)
        viewBinding.tvNewItems.setOnClickListener(this)
        viewBinding.tvOurPicks.setOnClickListener(this)
        viewBinding.tvPriceHigh.setOnClickListener(this)
        viewBinding.tvPriceLow.setOnClickListener(this)
        viewBinding.btnShowResult.setOnClickListener(this)
    }

    private fun clearView() {
        if (isProduct) {
            viewBinding.tvRefineCategories.text = KEY_EMPTY
        }
        viewBinding.tvRefineBrands.text = KEY_EMPTY
        viewBinding.tvRefineColours.text = KEY_EMPTY
        viewBinding.tvRefineSizes.text = KEY_EMPTY
        viewBinding.tvRefineGender.text = KEY_EMPTY
        viewBinding.ivRightNewItems.isSelected = false
        viewBinding.ivRightOurPicks.isSelected = false
        viewBinding.ivRightPriceLow.isSelected = false
        viewBinding.ivRightPriceHigh.isSelected = false
    }

}