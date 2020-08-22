package com.ptithcm.ptshop.view.card

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ptithcm.core.model.ProductClothes
import com.ptithcm.core.model.ProductClothesDetail
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.core.util.PriceFormat
import com.ptithcm.core.vo.Result
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.FROM_SHOPPING_BAG
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentShoppingBagBinding
import com.ptithcm.ptshop.databinding.LayoutPopUpDeleteItemBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.BindingAdapterText
import com.ptithcm.ptshop.util.PopUp
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.viewmodel.ShoppingViewModel
import com.ptithcm.ptshop.widget.RecyclerRefreshLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ShoppingBagFragment : BaseFragment<FragmentShoppingBagBinding>(), View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_shopping_bag

    private val basketViewModel: ShoppingViewModel by viewModel()

    private var isLogin = false
    private val adapter = ShoppingCardAdapter(this::adapterListener)

    override fun bindEvent() {
        viewBinding.fragment = this
        viewBinding.layoutEmpty.btnStartShopping.setOnClickListener(this)
        isLogin = ObjectHandler.isLogin()
        ObjectHandler.getCartFromPref()
        val countBags = ObjectHandler.getNumberItem()
        setUpToolBar(countBags)
        setUpRv()

        if (countBags > 0) {
            viewBinding.swipeRf.setRefreshing(true)
            basketViewModel.getAllProductsInCart(ObjectHandler.getAllIdProdsInCart())
        } else {
            viewBinding.swipeRf.isEnabled = false
            setupEmptyView()
        }
    }

    override fun bindViewModel() {
        basketViewModel.cartResult.observe(this, androidx.lifecycle.Observer {
            setUpResult(it)
            val indexOfItemChanged = it.indexOfFirst { it.hasChanged }
            if (indexOfItemChanged != -1) {
                messageHandler?.runMessageErrorHandler(getString(R.string.some_item_changed_in_cart))
                viewBinding.rvProducts.smoothScrollToPosition(indexOfItemChanged)
                viewBinding.btnCheckOut.isLoading = false
                return@Observer
            }
            if (viewBinding.btnCheckOut.isLoading) {
                viewBinding.btnCheckOut.isLoading = false
                navController.navigateAnimation(
                    R.id.fragment_checkout
                )
            }
        })

        basketViewModel.error.observe(this, androidx.lifecycle.Observer {
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
                basketViewModel.getAllProductsInCart(ObjectHandler.getAllIdProdsInCart())
            }
            R.id.btnCancel -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                adapter.removeItem(adapter.curProduct)
                ObjectHandler.saveCartToPref()
                setUpToolBar(ObjectHandler.cartSize())
            }
            R.id.btnOk -> {
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

        viewBinding.swipeRf.setOnRefreshListener {
            basketViewModel.getAllProductsInCart(ObjectHandler.getAllIdProdsInCart())
        }
    }

    private fun setUpToolBar(size: Int = 0) {
        viewBinding.size = size

        val totalPrice = ObjectHandler.cart?.products.calculateFinalPrice()
        val totalPriceStr = getString(
            R.string.basket_total,
            size,
            PriceFormat.currencyFormat(totalPrice)
        )

        BindingAdapterText.setTextHtml(viewBinding.tvDescShoppingBag, totalPriceStr)
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(getString(R.string.shopping_bag_tittle_s, size.toString()))
            when (this) {
                is MainActivity -> {
                    findViewById<BottomNavigationView>(R.id.btnNav).gone()
                    viewBinding.layoutToolbar.toolbar.apply {
                        isSelected = false
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

    private fun setUpResult(it: ArrayList<ProductClothesDetail>?) {
        it ?: return
        val size = ObjectHandler.getNumberItem()
        setUpToolBar(size)

        adapter.submitList(it)
        viewBinding.swipeRf.setRefreshing(false)
    }

    private fun adapterListener(eventType: Int, data: Any?) {
        when (eventType) {
            // click in item
            ShoppingCardAdapter.GO_TO_PRODUCT_DETAIL -> {
                (data as? ProductClothesDetail)?.let {
                    navController.navigateAnimation(
                        R.id.fragment_product_detail,
                        bundle = bundleOf(KEY_ARGUMENT to ProductClothes(id = it.id))
                    )
                }
            }

            //click in x button
            ShoppingCardAdapter.DELETE_PRODUCT -> {
                (data as? ProductClothesDetail)?.let {
                    (requireActivity() as? BaseActivity<*>)?.showPopup(
                        PopUp(
                            R.layout.layout_pop_up_delete_item,
                            messageQueue = this::popupDeleteIItem
                        )
                    )
                }
            }

            // update quantity of an item
            ShoppingCardAdapter.UPDATE_QUANTITY -> {
                (data as? ProductClothesDetail)?.let {
                    ObjectHandler.saveCartToPref()
                    setUpToolBar(ObjectHandler.getNumberItem())
                }
            }

            // out of stock
            ShoppingCardAdapter.ERROR -> {
                (data as? Result.Error)?.let {
                    viewBinding.root.showErrorSnackBar(it.message)
                }
            }

            //update size or color of am item
            ShoppingCardAdapter.UPDATE_PRODUCT -> {
                (data as? ProductClothesDetail)?.let {
                    ObjectHandler.adjustProductInCart(data)
                    setUpResult(ObjectHandler.cart?.products)
                }
            }
        }
    }

    private fun popupDeleteIItem(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutPopUpDeleteItemBinding)?.apply {
            title = getString(R.string.remove_from_basket_warning)
            left = getString(R.string.yes)
            right = getString(R.string.no)
            btnCancel.setOnClickListener(this@ShoppingBagFragment)
            btnOk.setOnClickListener(this@ShoppingBagFragment)
        }
    }

    private fun setupEmptyView() {
        setUpToolBar(0)
    }
}