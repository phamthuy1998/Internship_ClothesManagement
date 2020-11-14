package com.ptithcm.ptshop.view.home.storydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ptithcm.core.model.*
import com.ptithcm.core.util.capitalize
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.IS_FROM_BRAND_PROFILE
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_ID
import com.ptithcm.ptshop.constant.KEY_TYPE
import com.ptithcm.ptshop.databinding.ActivityStoryDetailBinding
import com.ptithcm.ptshop.databinding.ItemTagBinding
import com.ptithcm.ptshop.databinding.LayoutPopUpDeleteStoryBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.PopUp
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class StoryDetailFragment : BaseFragment<ActivityStoryDetailBinding>(), View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.activity_story_detail

    private lateinit var sheetBehavior: BottomSheetBehavior<LinearLayout>

    private val homeViewModel: HomeViewModel by viewModel()

    private val productAdapter = ProductImageAdapter(
        Locale.getDefault()
    ) { it: Any?, _: Int? ->
        when (it) {
            // click to see detail
            is Product -> {
                navController.navigateAnimation(
                    R.id.nav_product,
                    bundle = bundleOf(
                        KEY_ARGUMENT to it
                    )
                )
            }
        }
    }

//    private var size = 0
//
//    private val arr = arrayListOf<Product>()

    private val checkVidExtension = Regex("([^\\s]+(\\.(?i)(mov|mp4|flv|avi))$)")

    private var story: Stories? = null

    private var brandID: Int? = null

    private var typeCarousel: String? = null

    private var isFromBrandProfile = false

    private var currentState = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            arguments?.getParcelable<Stories>(KEY_ARGUMENT)?.apply {
                story = this
                val ids = arrayListOf<Int>()
                products?.forEach {
                    ids.add(it.id)
//                    homeViewModel.getProductDetail(id = it.id)
                }
                homeViewModel.getProductsDetail(ids)
            }
            brandID = arguments?.getInt(KEY_ID)
            typeCarousel = arguments?.getString(KEY_TYPE)
            isFromBrandProfile = arguments?.getBoolean(IS_FROM_BRAND_PROFILE) ?: false
        } else {
            (activity as? StoryDetailActivity)?.apply {
                story = storyObj
                brandID = brandId
                typeCarousel = brandType
                isFromBrandProfile = intent.getBooleanExtra(IS_FROM_BRAND_PROFILE, false)
            }
            val ids = arrayListOf<Int>()
            story?.products?.forEach {
                ids.add(it.id)
//                homeViewModel.getProductDetail(id = it.id)
            }
            homeViewModel.getProductsDetail(ids)
        }
        arguments?.clear()
    }

    override fun bindEvent() {
        viewBinding.activity = this

        viewBinding.isFromBrandProfile = isFromBrandProfile

        story?.apply {
            viewBinding.story = this
            bindingTags(tags)
//            size = products?.size ?: 0
//            if (arr.size > 0) {
//                productAdapter.addList(arr)
//            }
//            else {
//                arr.addAll(products ?: arrayListOf())
//                productAdapter.addList(products ?: arrayListOf())
//            }
            productAdapter.addList(products ?: arrayListOf())
        }

        setUpVP()

        setUpToolBar()

        showBottomSheet()

        (activity as? StoryDetailActivity)?.onBackPressedDispatcher?.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (sheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    } else {
                        activity?.finish()
                    }
                }
            })
    }

    override fun bindViewModel() {
//        homeViewModel.productLiveData.observe(this, Observer {
//            if (size > 0){
//                val position = checkPosition(it)
//                if (position != -1){
//                    arr.removeAt(position)
//                    arr.add(position, it)
//                }
//            }
//            size--
//            if (size == 0){
//                productAdapter.apply {
//                    setFullDetail(true)
//                    addList(arr)
//                }
//            }
//        })

        homeViewModel.productsLiveData.observe(this, Observer {
            productAdapter.apply {
                setFullDetail(true)
                addList(it)
            }
        })

        homeViewModel.deleteResult.observe(this, Observer {
            navController.popBackStack()
        })
    }

    private fun setUpVP() {
        val upload = viewBinding.story?.uploads?.find {
            it.url?.contains(checkVidExtension) == true
        }
        val uploads = if (upload != null)
            arrayListOf(upload)
        else
            viewBinding.story?.uploads
        viewBinding.uploadSize = uploads?.size
        viewBinding.vpImage.adapter = StoryImgPageAdapter(childFragmentManager, uploads ?: return)
        viewBinding.indicator.setupWithViewPager(viewBinding.vpImage)
    }

    private fun setUpToolBar() {
        (activity as? StoryDetailActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.gone()
            transparentStatusBar(isFull = false, isBlack = true)
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivClose -> {
                if (!navController.popBackStack())
                    activity?.finish()
            }
            R.id.backGround -> {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            R.id.tvBrandName -> {
                if (typeCarousel == TypeCarousel.STORE.value) {
                    navController.navigateAnimation(
                        R.id.nav_carousel_detail,
                        bundle = bundleOf(
                            KEY_ARGUMENT to Carousel(
                                storeId = brandID,
                                type = TypeCarousel.STORE.value,
                                gender = Gender.NONE.value
                            )
                        )
                    )
                } else {
                    navController.navigateAnimation(
                        R.id.nav_carousel_detail,
                        bundle = bundleOf(
                            KEY_ARGUMENT to Carousel(
                                brand_id = brandID,
                                type = TypeCarousel.BRAND.value,
                                gender = Gender.NONE.value
                            )
                        )
                    )
                }
            }
            R.id.ivDelete -> {
                showDeletePopUp()
            }
            R.id.btnCancel -> {
                // todo delete not working at the moment
                (activity as? BaseActivity<*>)?.closePopup()
                homeViewModel.deleteStory(story?.id)
            }
            R.id.btnOk -> {
                (activity as? BaseActivity<*>)?.closePopup()
            }
        }
    }

    private fun showDeletePopUp() {
        (requireActivity() as? BaseActivity<*>)?.showPopup(
            PopUp(
                R.layout.layout_pop_up_delete_story,
                messageQueue = this::popEvent
            )
        )
    }

    private fun popEvent(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutPopUpDeleteStoryBinding)?.apply {
            left = getString(R.string.cancel)
            right = getString(R.string.delete)
            title = getString(R.string.title_delete_story)
            btnCancel.setOnClickListener(this@StoryDetailFragment)
            btnOk.setOnClickListener(this@StoryDetailFragment)
        }

    }

    private fun bindingTags(tags: ArrayList<Tag>?) {
        tags?.forEachIndexed { index, it ->
            val binding = DataBindingUtil.inflate<ItemTagBinding>(
                LayoutInflater.from(context),
                R.layout.item_tag,
                null,
                false
            )
            binding.tvTag.text = it.name?.toLowerCase(Locale.getDefault())?.capitalize()
            if (index > 0) {
                binding.root.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(10, 0, 0, 10)
                }
            }
            viewBinding.groupTags.addView(binding.root)
        }
    }

    private fun showBottomSheet() {
        sheetBehavior =
            BottomSheetBehavior.from(viewBinding.includeShopTheLook.root as LinearLayout)

        sheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                viewBinding.backGround.gone()
                currentState = -1
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        currentState = newState
                        showBackGround()
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        showBackGround()
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        viewBinding.includeShopTheLook.clShopTheLook.visible()
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }
        }
        )

        viewBinding.includeShopTheLook.root.findViewById<ConstraintLayout>(R.id.clShopTheLook)
            ?.apply {
                setOnClickListener {
                    showBackGround()
                    sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }

        sheetBehavior.apply {
            isFitToContents = true
            isHideable = false
            halfExpandedRatio = 0.5f
            if (currentState != -1) {
                showBackGround()
                state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        setRvBottomSheet()
    }

    private fun showBackGround() {
        viewBinding.backGround.visible()
        viewBinding.includeShopTheLook.clShopTheLook.inVisible()
    }

    private fun setRvBottomSheet() {
        viewBinding.includeShopTheLook.rvStoryProducts.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator?.changeDuration = 0
        }
    }

//    private fun checkPosition(item: Product): Int{
//        return arr.indexOfFirst {
//            it.id == item.id
//        }
//    }
}