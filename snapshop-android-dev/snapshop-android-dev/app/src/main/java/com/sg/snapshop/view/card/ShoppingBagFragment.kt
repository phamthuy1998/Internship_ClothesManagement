package com.sg.snapshop.view.card

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.text.HtmlCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sg.core.CoreApplication
import com.sg.core.model.ProductVariant
import com.sg.core.model.Variant
import com.sg.core.param.AddProductParam
import com.sg.core.util.ObjectHandler
import com.sg.core.vo.Result
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.constant.FROM_SHOPPING_BAG
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.databinding.FragmentShoppingBagBinding
import com.sg.snapshop.databinding.LayoutPopUpDeleteItemBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.util.PopUp
import com.sg.snapshop.widget.RecyclerRefreshLayout
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.viewmodel.ShoppingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingBagFragment : BaseFragment<FragmentShoppingBagBinding>(), View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_shopping_bag

    private val basketViewModel: ShoppingViewModel by viewModel()
    private var prodId: Long = 0
    private var isUpdate = false
    private var addProductParam: AddProductParam? = null
    private var isLogin = false
    private var isFirstTimeCall = false
    private val adapter = ShoppingCardAdapter(this::adapterListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFirstTimeCall = isLogin
    }

    override fun bindEvent() {
        viewBinding.fragment = this
        viewBinding.layoutEmpty.btnStartShopping.setOnClickListener(this)
        isLogin = ObjectHandler.isLogin()
        val countBags = ObjectHandler.getNumberItem()
        setUpToolBar(countBags)
        setUpRv()
        if (!isLogin) {
            viewBinding.swipeRf.isEnabled = false
            if (countBags > 0) {
                setUpResult(ObjectHandler.notLoginBasket)
            } else {
                setupEmptyView()
            }
        } else {
            if (ObjectHandler.notLoginBasketSize() > 0) {
                basketViewModel.updateBasketFromLocal()
            } else {
                basketViewModel.getBasket()
            }
            if (isFirstTimeCall) {
                viewBinding.swipeRf.visible()
                viewBinding.layoutEmpty.root.gone()
                viewBinding.swipeRf.setRefreshing(true)
                isFirstTimeCall = false
            }
            viewBinding.swipeRf.setOnRefreshListener {
                basketViewModel.getBasket()
            }
        }
    }

    override fun bindViewModelOnce() {
        basketViewModel.updateResult.observe(this, Observer {
            setUpResult(it.product_variants as ArrayList<ProductVariant>)
        })

        basketViewModel.removeResult.observe(this, Observer {
            if (isUpdate.not()) {
                basketViewModel.getBasket()
            } else {
                isUpdate = false
                basketViewModel.updateBasket(addProductParam ?: return@Observer)
                addProductParam = null
            }
        })
    }

    override fun bindViewModel() {
        basketViewModel.cardResult.observe(this, Observer {
            setUpResult(it.product_variants as ArrayList<ProductVariant>)
            if (viewBinding.btnCheckOut.isLoading) {
                viewBinding.btnCheckOut.isLoading = false
                navController.navigateAnimation(
                    R.id.fragment_checkout
                )
            }
        })

        basketViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageErrorHandler(it.first)
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCheckOut -> {
                if (isLogin.not()) {
                    navController.navigateAnimation(
                        R.id.nav_login,
                        isBotToTop = true,
                        bundle = bundleOf(
                            FROM_SHOPPING_BAG to true
                        )
                    )
                    return
                }
                viewBinding.btnCheckOut.isLoading = true
                basketViewModel.getBasket()
            }
            R.id.btnCancel -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                if (isLogin.not()) {
                    adapter.removeItem(prodId)
                    setUpResult(ObjectHandler.removeFromNotLoginBasket(prodId))
                    return
                }
                if (prodId != 0.toLong())
                    basketViewModel.removeFromBasket(prodId)
            }
            R.id.btnOk -> {
                prodId = 0
                (requireActivity() as? BaseActivity<*>)?.closePopup()
            }
            R.id.btnStartShopping -> {
                val isShopSelected =
                    (requireActivity() as? MainActivity)?.viewBinding?.btnNav?.selectedItemId == R.id.nav_shop
                if (activity is StoryDetailActivity) {
                    (activity as? BaseActivity<*>)?.goToShop()
                    activity?.finish()
                } else {
                    if (isShopSelected) {
                        navController.navigate(R.id.action_pop_nav_shop)
                    } else {
                        navController.navigate(R.id.action_pop_nav_designer)
                        (requireActivity() as? BaseActivity<*>)?.goToShop()
                    }
                }
            }
        }
    }

    private fun setUpRv() {
        viewBinding.rvProducts.adapter = adapter
        viewBinding.swipeRf.setRefreshView(
            RecyclerRefreshLayout(
                requireContext(),
                text = getString(R.string.sync_basket)
            ),
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
    }

    private fun setUpToolBar(size: Int = 0) {
        if (isFirstTimeCall.not()) {
            viewBinding.size = size
        }
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(getString(R.string.shopping_bag_tittle_s, size.toString()))
            when(this){
                is MainActivity -> {
                    findViewById<BottomNavigationView>(R.id.btnNav).gone()
                    viewBinding.layoutToolbar.toolbar.apply { isSelected = false
                        findViewById<AppCompatImageButton>(R.id.ivBack)?.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_black_close
                            )
                        )
                    }
                }
                is StoryDetailActivity -> {
                    viewBinding.layoutToolbar.toolbar.apply { isSelected = false
                        findViewById<AppCompatImageButton>(R.id.ivBack)?.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_black_close
                            )
                        )
                    }
                }
            }
        }
    }

    private fun setUpResult(it: ArrayList<ProductVariant>) {
        val size = ObjectHandler.getNumberItem()
        setUpToolBar(size)
        val sum = it.calculatePriceAfterTax()
        val string = getString(
            R.string.basket_total,
            size,
            sum.toString().roundPrice(CoreApplication.instance.currency.getLocale())
        )
        viewBinding.tvDescShoppingBag.text =
            HtmlCompat.fromHtml(string, HtmlCompat.FROM_HTML_MODE_COMPACT)
        adapter.addProduct(it)
        viewBinding.swipeRf.setRefreshing(false)
    }

    private fun adapterListener(it: Any?) {
        when (it) {
            // click in item
            is Variant -> {
                navController.navigateAnimation(
                    R.id.fragment_product_detail,
                    bundle = bundleOf(KEY_ARGUMENT to it.product)
                )
            }

            //click in x button
            is Long -> {
                if (prodId != it) {
                    prodId = it
                    (requireActivity() as? BaseActivity<*>)?.showPopup(
                        PopUp(
                            R.layout.layout_pop_up_delete_item,
                            messageQueue = this::popEvent
                        )
                    )
                }
            }

            // update quantity of an item
            is AddProductParam -> {
                basketViewModel.updateBasket(it)
            }

            // out of stock
            is Result.Error -> {
                messageHandler?.runMessageErrorHandler(it.message)
            }

            //update size or color of am item
            is Pair<*, *> -> {
                when {
                    // for already login
                    it.first is AddProductParam && it.second is Long -> {
                        isUpdate = true
                        addProductParam = it.first as AddProductParam
                        basketViewModel.removeFromBasket(it.second as Long)
                    }
                    //for not login yet
                    it.first is ProductVariant && it.second is Long -> {
                        setUpResult(
                            ObjectHandler.adjustProdInNotLoginBasket(
                                it.first as ProductVariant,
                                it.second as Long
                            )
                        )
                    }
                }
            }
        }
    }

    private fun popEvent(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutPopUpDeleteItemBinding)?.apply {
            title = getString(R.string.remove_from_basket_warning)
            left = getString(R.string.yes)
            right = getString(R.string.no)
            btnCancel.setOnClickListener(this@ShoppingBagFragment)
            btnOk.setOnClickListener(this@ShoppingBagFragment)
        }
    }

    private fun setupEmptyView() {
        setUpToolBar()
        viewBinding.tvDescShoppingBag.text = HtmlCompat.fromHtml(
            getString(
                R.string.basket_total,
                0,
                "0".roundPrice(CoreApplication.instance.currency.getLocale())
            ), HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }
}