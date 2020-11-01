package com.ptithcm.ptshop.view.wishlist

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_DESIGNER
import com.ptithcm.ptshop.databinding.FragmentProductDetailBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.view.wishlist.overview.ProductionClothesBannersPagerAdapter
import com.ptithcm.ptshop.viewmodel.ShoppingViewModel
import com.ptithcm.ptshop.viewmodel.WishListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_product_detail

    private val wishListViewModel: WishListViewModel by viewModel()
    private val shoppingViewModel: ShoppingViewModel by viewModel()

    private var product: ProductClothes? = null
    private var productDetail: ProductClothesDetail? = null
    private var quality = 0
    private val isLogin = ObjectHandler.isLogin()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (arguments?.getParcelable(KEY_ARGUMENT) as? ProductClothes)?.let {
            product = it
            shoppingViewModel.getProdDetail(it.id)
        }
        activity?.btnNav?.visibility = View.GONE
        (activity as? BaseActivity<*>)?.isShowLoading(false)
    }

    override fun bindEvent() {
        viewBinding.fragment = this
        setUpViewPager()
        bindProduct()
        setUpToolBar()
        viewBinding.tvOriginPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        if (product == null) viewBinding.btnAddToCard.disable()
    }

    override fun bindViewModelOnce() {
        shoppingViewModel.detailResult.observe(this, Observer {
            productDetail = it
//            viewBinding.item = productDetail
            setUpToolBar()
            bindProduct()
            setUpViewPager()
        })
    }

    override fun bindViewModel() {
        wishListViewModel.addAndRemoveResult.observe(this, Observer {})

        shoppingViewModel.isLoading.observe(this, Observer {
            viewBinding.btnAddToCard.isLoading = it
        })

        shoppingViewModel.error.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
        })
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> {
                navController.popBackStack()
            }
            R.id.btnDesc, R.id.tvDescription -> {
                toggleDrawable(viewBinding.btnDesc, viewBinding.tvDescription.expandOrCollapse())
            }
            R.id.btnAboutBrand, R.id.tvAboutBrand -> {
                toggleDrawable(
                    viewBinding.btnAboutBrand,
                    viewBinding.tvAboutBrand.expandOrCollapse()
                )
            }
            R.id.tvSizeGuide -> {
                navController.navigateAnimation(R.id.sizeGuideFragment)
            }
            R.id.btnAddToCard -> {
                val numQuality = viewBinding.btnQuantity.selectedItem?.toString()?.toInt() ?: 0

                if (numQuality == 0 || quality == 0) {
                    return
                }
                quality -= numQuality

                handleAddToCart(
                    selectedQuantity = numQuality
                )

            }
            R.id.ivRight -> {
                v.apply {
                    if (isLogin) {
                        isSelected = isSelected.not()
                        wishListViewModel.addAndRemoveToWishList(productDetail?.id)
                        wishListViewModel.productWishListChange.value = Pair(
                            wishListViewModel.productWishListChange.value?.first ?: 0,
                            isSelected
                        )
                    } else {
                        messageHandler?.runMessageErrorHandler(getString(R.string.login_to_add_wish_list))
                    }
                }
            }
            R.id.tvTitleToolbar -> {
                navController.navigateAnimation(
                    R.id.nav_carousel_detail,
                    bundle = bundleOf(KEY_ARGUMENT to productDetail?.provider, KEY_DESIGNER to true)
                )
            }
        }
    }

    private fun bindProduct() {
        productDetail?.let {
            viewBinding.item = it
            setUpSpinner()
        }
    }

    private fun setUpViewPager() {
        productDetail?.let {
            viewBinding.vpImage.adapter = ProductionClothesBannersPagerAdapter(
                childFragmentManager,
                it.images ?: arrayListOf()
            ) { list, pos ->
                navController.navigate(
                    R.id.fragment_over_view,
                    bundleOf(
                        "list" to list,
                        "pos" to pos
                    )
                )
            }

            viewBinding.vpImage.offscreenPageLimit = it.images?.size ?: 0

            if (it.images?.size ?: 0 < 2) {
                viewBinding.indicator.inVisible()
                return
            }

            viewBinding.indicator.setupWithViewPager(viewBinding.vpImage)
        }
    }

    private fun setUpToolBar() {
        (requireActivity() as? BaseActivity<*>)?.apply {
            val toolbar = when (this) {
                is MainActivity -> viewBinding.layoutToolbar.toolbar
                is StoryDetailActivity -> viewBinding.layoutToolbar.toolbar
                else -> null
            }
            initToolbar(
                hasBackRight = false,
                hasLeft = false,
                hasCount = false,
                isProductPage = true
            )
            toolbar?.isSelected = true
            setupToolbar(
                productDetail?.provider?.brandName ?: "",
                isBackPress = false,
                messageQueue = {
                    onClick(it)
                })
            toolbar?.findViewById<AppCompatImageButton>(R.id.ivRight)?.apply {
                isSelected = product?.getIsFavorite() ?: false
                setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.wished_selector)
                )
            }
        }
    }

    private fun setUpSpinner() {
        val colorOptions = productDetail?.colors ?: arrayListOf()
        val sizeOptions = productDetail?.sizes ?: arrayListOf()

        viewBinding.btnColor.apply {
            viewBinding.btnColorVisible = !colorOptions.isNullOrEmpty()

            adapter = ColorSpinnerAdapter(
                context,
                R.layout.item_color,
                colorOptions
            )

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                var iCurrentSelection = viewBinding.btnColor.selectedItemPosition

                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 != iCurrentSelection) {
                        iCurrentSelection = p2
                        viewBinding.btnQuantity.setSelection(0)
                    }
                    handleSelectOption()
                }
            }
        }

        viewBinding.btnSize.apply {
            viewBinding.btnSizeVisible = !sizeOptions.isNullOrEmpty()

            adapter = ArrayAdapter(
                context,
                R.layout.item_spinner,
                sizeOptions.map { it.sizeName }
            )

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                var iCurrentSelection = viewBinding.btnSize.selectedItemPosition

                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 != iCurrentSelection) {
                        iCurrentSelection = p2
                        viewBinding.btnQuantity.setSelection(0)
                    }
                    handleSelectOption()
                }
            }
        }

        viewBinding.btnColor.setSelection(0, true)
        viewBinding.btnSize.setSelection(0, true)
        handleSelectOption()
    }

    private fun handleSelectOption() {
        if (viewBinding.btnSizeVisible == false || viewBinding.btnSizeVisible == false)
            return

        val colorOption =
            productDetail?.colors?.getOrNull(viewBinding.btnColor.selectedItemPosition)
        val sizeOption = productDetail?.sizes?.getOrNull(viewBinding.btnSize.selectedItemPosition)
        viewBinding.isAvailable = colorOption == null && sizeOption == null

        val quantityFromBE = productDetail?.sizesColors?.firstOrNull {
            it.colorID == colorOption?.id && it.sizeId == sizeOption?.id
        }?.quantity ?: 0
        val quantityFromLocal = ObjectHandler.getQuantityProductClothesFromLocal(
            product?.id,
            sizeOption?.id,
            colorOption?.id
        )

        val realQuantity = (quantityFromBE - quantityFromLocal).coerceIn(0..Int.MAX_VALUE)
        viewBinding.hasQuantity = realQuantity != 0
        setQuantity(realQuantity)
    }

    private fun setQuantity(inventory_quantity: Int?) {
        viewBinding.btnQuantity.apply {
            val size = inventory_quantity?.coerceAtMost(50)

            quality = inventory_quantity ?: 0

            val option = if (size ?: 0 < 1) {
                isEnabled = false
                viewBinding.hasQuantity = false
                viewBinding.btnAddToCard.btnOutOfStock = true
                arrayListOf("0")
            } else {
                isEnabled = true
                viewBinding.hasQuantity = true
                viewBinding.btnAddToCard.btnOutOfStock = false
                (1..size!!).toMutableList().map { it.toString() }
            }

            adapter = ArrayAdapter(context, R.layout.item_spinner, option)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    id: Long
                ) {
                    viewBinding.tvQuantity.text = getString(
                        R.string.quantity_x,
                        viewBinding.btnQuantity.getItemAtPosition(position)
                    )
                }
            }
        }
    }

    private fun handleAddToCart(selectedQuantity: Int) {
        viewBinding.hasQuantity = quality == 0
        setQuantity(quality)

        val colorOption =
            productDetail?.colors?.getOrNull(viewBinding.btnColor.selectedItemPosition)
        val sizeOption = productDetail?.sizes?.getOrNull(viewBinding.btnSize.selectedItemPosition)
        viewBinding.isAvailable = colorOption == null && sizeOption == null
        val sizesColor =
            productDetail?.sizesColors?.firstOrNull { it.colorID == colorOption?.id && it.sizeId == sizeOption?.id }

        productDetail?.selectedColor = colorOption
        productDetail?.selectedSize = sizeOption

        val product = clone(productDetail)?.apply {
            quantityInCart = clone(sizesColor).apply { this?.quantity = selectedQuantity }
        }
        ObjectHandler.addToCart(product)
        messageHandler?.runMessageHandler(getString(R.string.add_to_basket_success))
    }
}