package com.sg.snapshop.view.wishlist

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sg.core.CoreApplication
import com.sg.core.model.*
import com.sg.core.param.AddProductParam
import com.sg.core.param.ProductVariantParam
import com.sg.core.param.RefineParam
import com.sg.core.util.ObjectHandler
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.constant.KEY_ARGUMENT_REFINE
import com.sg.snapshop.databinding.FragmentProductDetailBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.view.wishlist.overview.ProductionBannersPagerAdapter
import com.sg.snapshop.viewmodel.RefineViewModel
import com.sg.snapshop.viewmodel.ShoppingViewModel
import com.sg.snapshop.viewmodel.WishListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_product_detail

    private val wishListViewModel: WishListViewModel by viewModel()
    private val shoppingViewModel: ShoppingViewModel by viewModel()
    private val refineViewModel: RefineViewModel by sharedViewModel(from = { requireActivity() })

    private val locale by lazy {
        CoreApplication.instance.currency.name?.toLocale() ?: Locale.UK
    }
    private var productVariant: Variant? = null
    private var product: Product? = null
    private var quality = 0
    private val isLogin = ObjectHandler.isLogin()
    private var firstSizeSelection = -1
    private var firstColorSelection = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (arguments?.getParcelable(KEY_ARGUMENT) as? Product)?.let {
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
        //disable add to cart when login as brand
        viewBinding.btnAddToCard.visibility =
            if (CoreApplication.instance.profile?.user?.brand != null) View.GONE else View.VISIBLE
        viewBinding.tvOriginPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        if(product==null) viewBinding.btnAddToCard.disable()
    }

    override fun bindViewModelOnce() {
        shoppingViewModel.updateResult.observe(this, Observer {
            viewBinding.hasQuantity = quality > 0
            setQuantity(quality)
            messageHandler?.runMessageHandler(getString(R.string.add_to_basket_success))
        })

        shoppingViewModel.detailResult.observe(this, Observer {
            product = it
            setUpToolBar()
            bindProduct()
        })
    }

    override fun bindViewModel() {
        wishListViewModel.addResult.observe(this, Observer {})
        wishListViewModel.removeResult.observe(this, Observer {})

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
                refineViewModel.refineLiveData.value = Pair(arguments?.getParcelable(KEY_ARGUMENT_REFINE) as? RefineParam, false)
                navController.popBackStack()
            }
            R.id.btnDesc, R.id.tvDescription -> {
                toggleDrawable(viewBinding.btnDesc, viewBinding.tvDescription.expandOrCollapse())
            }
            R.id.btnDeliveryReturn, R.id.tvDeliveryReturn -> {
                toggleDrawable(
                    viewBinding.btnDeliveryReturn,
                    viewBinding.tvDeliveryReturn.expandOrCollapse()
                )
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
            R.id.tvMoreFromBoutique, R.id.tvTitleToolbar -> {
                navController.navigateAnimation(
                    R.id.nav_carousel_detail,
                    bundle = bundleOf(
                        KEY_ARGUMENT to Carousel(
                            storeId = product?.brand?.id,
                            type = TypeCarousel.STORE.value,
                            gender = Gender.NONE.value
                        )
                    )
                )
            }
            R.id.tvMoreFromVendor, R.id.tvVendor -> {
                val typeCarousel =
                    if (product?.vendor_brand?.stores?.size ?: 0 > 1) TypeCarousel.BRAND else TypeCarousel.STORE
                navController.navigateAnimation(
                    R.id.nav_carousel_detail,
                    bundle = bundleOf(
                        KEY_ARGUMENT to Carousel(
                            brand_id = product?.vendor_brand?.id,
                            storeId = product?.brand?.id,
                            type = typeCarousel.value,
                            name = product?.vendor_brand?.name,
                            gender = Gender.NONE.value
                        )
                    )
                )
            }
            R.id.btnAddToCard -> {
                val numQuality = viewBinding.btnQuantity.selectedItem?.toString()?.toInt()?:0

                if (numQuality == 0 || quality == 0) {
                    return
                }
                quality -= numQuality
                if (isLogin.not()) {
                    handleAddToCardWithoutLogin(
                        selectedQuantity = numQuality
                    )
                    return
                }

                shoppingViewModel.updateBasket(
                    AddProductParam(
                        arrayListOf(
                            ProductVariantParam(
                                quantity = productVariant?.inventory_quantity!! - quality,
                                product_variant = productVariant?.id ?: return
                            )
                        )
                    )
                )
            }
            R.id.ivRight -> {
                v.apply {
                    if (isLogin) {
                        isSelected = isSelected.not()
                        if (isSelected) {
                            wishListViewModel.addToWishList(this@ProductDetailFragment.viewBinding.item?.id)
                        } else {
                            wishListViewModel.removeFromWishList(this@ProductDetailFragment.viewBinding.item?.id)
                        }
                    } else {
                        messageHandler?.runMessageErrorHandler(getString(R.string.login_to_add_wish_list))
                    }
                }
            }
        }
    }

    private fun bindProduct() {
        product?.let {
            viewBinding.item = it

            viewBinding.isAvailable = checkQuantity()

            setUpSpinner()

            setSelection()
        }
    }

    private fun setUpViewPager(){
        product?.let {
            viewBinding.vpImage.adapter = ProductionBannersPagerAdapter(
                childFragmentManager,
                it.image ?: arrayListOf()
            ) { list, pos ->
                navController.navigate(
                    R.id.fragment_over_view,
                    bundleOf(
                        "list" to list,
                        "pos" to pos
                    )
                )
            }

            viewBinding.vpImage.offscreenPageLimit = it.image?.size ?: 0

            if (it.image?.size ?: 0 < 2) {
                viewBinding.indicator.inVisible()
                return
            }

            viewBinding.indicator.setupWithViewPager(viewBinding.vpImage)
        }
    }

    private fun setUpToolBar() {
        (requireActivity() as? BaseActivity<*>)?.apply {
            val toolbar = when(this){
                is MainActivity -> viewBinding.layoutToolbar.toolbar
                is StoryDetailActivity -> viewBinding.layoutToolbar.toolbar
                else -> null
            }
            initToolbar(hasBackRight = false, hasLeft = false, hasCount = false, isProductPage = true)
            toolbar?.isSelected = true
            setupToolbar(product?.brand?.brand_name ?: "", isBackPress = false, messageQueue = {
                    onClick(it)
                })
            toolbar?.findViewById<AppCompatImageButton>(R.id.ivRight)?.apply {
                isSelected = product?.isAddProduct ?: false
                setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.wished_selector)
                )
            }
        }
    }

    private fun setUpSpinner() {
        val options = viewBinding.item?.options
        val hasQuantity = viewBinding.hasQuantity ?: false
        val isAvailable = viewBinding.isAvailable ?: false
        viewBinding.btnColor.apply {
            if (viewBinding.btnColorVisible == false){
                return@apply
            }
            val colorOption = options?.colorOption()?.values

            adapter = ArrayAdapter(
                context,
                R.layout.item_spinner,
                colorOption?.checkProdAvailable(hasQuantity, isAvailable) ?: listOf()
            )
            var iCurrentSelection = this.selectedItemPosition

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 != iCurrentSelection) {
                        iCurrentSelection = p2
                        viewBinding.btnQuantity.setSelection(0)
                    }
                    val value = getItemAtPosition(p2) as String
                    productVariant = if (viewBinding.btnSize.visibility != View.GONE) {
                        findVariant(
                            Pair(viewBinding.btnSize.selectedItem.toString(), value),
                            viewBinding.item?.variants
                        )?.checkIfWrongPrice()
                    } else {
                        findVariant(Pair(null, value), viewBinding.item?.variants)?.checkIfWrongPrice()
                    }
                    handleSelectOption()
                }
            }
        }

        viewBinding.btnSize.apply {
            if (viewBinding.btnSizeVisible == false){
                return@apply
            }

            val sizeOption = options?.sizeOption()?.values

            adapter = ArrayAdapter(
                context,
                R.layout.item_spinner,
                sizeOption?.checkProdAvailable(hasQuantity, isAvailable) ?: listOf()
            )

            var iCurrentSelection = this.selectedItemPosition

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 != iCurrentSelection) {
                        iCurrentSelection = p2
                        viewBinding.btnQuantity.setSelection(0)
                    }

                    val value = getItemAtPosition(p2) as String

                    productVariant = if (viewBinding.btnColor.visibility != View.GONE) {
                        findVariant(
                            Pair(value, viewBinding.btnColor.selectedItem.toString()),
                            viewBinding.item?.variants
                        )?.checkIfWrongPrice()
                    } else {
                        findVariant(Pair(value, null), viewBinding.item?.variants)?.checkIfWrongPrice()
                    }

                    handleSelectOption()
                }
            }
        }
    }

    private fun handleSelectOption(){
        if (productVariant == null){
            viewBinding.tvPriceOrigin.text = getString(R.string.no_price)
            viewBinding.isAvailable = false
            viewBinding.hasQuantity = false
            setQuantity(0)
            return
        }

        viewBinding.tvPriceOrigin.text =
            productVariant?.price_after_tax?.roundPrice(locale)
        viewBinding.tvAfterTaxPrice.text =
            productVariant?.price_after_tax?.roundPrice(locale)
        viewBinding.tvOriginPrice.text =
            productVariant?.compare_at_price_after_tax?.roundPrice(locale)
        val quantityFromBE = productVariant?.inventory_quantity ?: 0
        val quantityFromLocal =
            ObjectHandler.getQuantityFromLocal(productVariant?.id)
        val realQuantity = if (quantityFromBE - quantityFromLocal < 0)
            0
        else quantityFromBE - quantityFromLocal
        setQuantity(realQuantity)
    }

    private fun setQuantity(inventory_quantity: Int?) {
        viewBinding.btnQuantity.apply {
            val size = if (inventory_quantity ?: 0 > 50)
                50
            else inventory_quantity

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

    /* - Check field option-A in product and option-B in variants of product
        if non of  B in A then set un available
        else if B in A then get the 1st valid variant
       - The first valid variant is the first on has field inventory_quantity > 0 (usually variant with field position = 1)
       - Field variant already been sort by position */
    private fun checkQuantity(): Boolean {
        val item = viewBinding.item?.variants
        val sizeOption = product?.options?.sizeOption()
        val colorOptions = product?.options?.colorOption()
        val arrBoolean = arrayListOf<Boolean>()
        viewBinding.btnSizeVisible = sizeOption != null
        viewBinding.btnColorVisible = colorOptions != null

        item?.forEach {
            val variantSizeOption = it.options?.sizeOption()?.value
            val variantColorOption = it.options?.colorOption()?.value
            val hasOptionColor = colorOptions.containValue(variantColorOption ?: "")
            val hasOptionSize = sizeOption.containValue(variantSizeOption ?: "")
            arrBoolean.add(hasOptionColor&&hasOptionSize)
            if (hasOptionSize
                && hasOptionColor){
                if (it.inventory_quantity ?: 0 > 0){
                    firstSizeSelection = sizeOption?.values?.indexOf(variantSizeOption) ?: -1
                    firstColorSelection = colorOptions?.values?.indexOf(variantColorOption) ?: -1
                    productVariant = it.checkIfWrongPrice()
                    viewBinding.hasQuantity = true
                    viewBinding.isAvailable = true
                    return true
                }
            }
        }

        if (viewBinding.hasQuantity == null) {
            viewBinding.hasQuantity = sizeOption == null && colorOptions == null
        }
        if (item?.size ?: 0 > 0) {
            productVariant = item?.first()?.checkIfWrongPrice()
        }
        if (sizeOption == null && colorOptions == null){
            return true
        }
        return arrBoolean.finalBoolean()
    }

    private fun setSelection(){
        productVariant?.let {
            viewBinding.tvPriceOrigin.text =
                productVariant?.price_after_tax?.roundPrice(locale)
            viewBinding.tvAfterTaxPrice.text =
                productVariant?.price_after_tax?.roundPrice(locale)
            viewBinding.tvOriginPrice.text =
                productVariant?.compare_at_price_after_tax?.roundPrice(locale)

            if (viewBinding.hasQuantity == false
                && viewBinding.isAvailable == false){
                viewBinding.btnSize.setSelection(0)
                viewBinding.btnColor.setSelection(0)
                return
            }

            if (firstSizeSelection != -1) {
                viewBinding.btnSize.setSelection(firstSizeSelection)
            }

            if (firstColorSelection != -1){
                viewBinding.btnColor.setSelection(firstColorSelection)
            }

            if (it.options?.sizeOption() == null && it.options?.colorOption() == null){
                setQuantity(it.inventory_quantity)
            }
        }
    }

    private fun handleAddToCardWithoutLogin(selectedQuantity: Int) {
        viewBinding.hasQuantity = quality == 0
        setQuantity(quality)
        ObjectHandler.addToNotLoginBasket(
            ProductVariant(
                productVariant?.copy(
                    product = product?.copyToNew()
                ) ?: return,
                selectedQuantity,
                null
            )
        )
        messageHandler?.runMessageHandler(getString(R.string.add_to_basket_success))
    }

}