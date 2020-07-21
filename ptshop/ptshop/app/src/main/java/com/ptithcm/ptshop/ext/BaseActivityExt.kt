package com.ptithcm.ptshop.ext

import android.app.Activity
import android.view.View
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.util.ObjectHandler
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.databinding.ActivityMainBinding
import com.ptithcm.ptshop.databinding.FragmentStoryDetailBinding
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

fun BaseActivity<*>.initToolbar(
    hasBack: Boolean = true, hasBackRight: Boolean = true,
    hasLeft: Boolean = true, hasRight: Boolean = true, hasTextLeft: Boolean = false,
    hasTextRight: Boolean = false, hasCloseButton: Boolean = false, hasCount: Boolean = true,
    isProductPage: Boolean = false
) {
    (viewBinding as? ActivityMainBinding)?.apply {
        initToolBar(
            this.layoutToolbar.toolbar, hasBack, hasBackRight, hasLeft, hasRight, hasTextLeft,
            hasTextRight, hasCloseButton, hasCount, isProductPage
        )
    }
    (viewBinding as? FragmentStoryDetailBinding)?.apply {
        initToolBar(
            this.layoutToolbar.toolbar, hasBack, hasBackRight, hasLeft, hasRight, hasTextLeft,
            hasTextRight, hasCloseButton, hasCount, isProductPage
        )
    }
}

fun BaseActivity<*>.setupToolbar(
    title: String = getString(R.string.app_name), isBackPress: Boolean = true,
    messageQueue: ((view: View) -> Unit)? = null
) {
    (viewBinding as? ActivityMainBinding)?.apply {
        setupToolbar(this.layoutToolbar.toolbar, title, isBackPress, messageQueue)
    }
    (viewBinding as? FragmentStoryDetailBinding)?.apply {
        setupToolbar(this.layoutToolbar.toolbar, title, isBackPress, messageQueue)
    }
}

fun BaseActivity<*>.isShowErrorNetwork(isShow: Boolean) {
    (viewBinding as? ActivityMainBinding)?.apply {
        if (isShow) {
            this.layoutError.visible()
        } else {
            this.layoutError.gone()
        }
    }
    (viewBinding as? FragmentStoryDetailBinding)?.apply {
        if (isShow) {
            this.layoutError.visible()
        } else {
            this.layoutError.gone()
        }
    }
}

fun BaseActivity<*>.isShowLoading(isShow: Boolean) {
    (viewBinding as? ActivityMainBinding)?.apply {
        if (isShow) {
            this.layoutLoading.visible()
        } else {
            this.layoutLoading.gone()
        }
    }
    (viewBinding as? FragmentStoryDetailBinding)?.apply {
        if (isShow) {
            this.layoutLoading.visible()
        } else {
            this.layoutLoading.gone()
        }
    }
}

fun BaseActivity<*>.isShowLoadingPayment(isShow: Boolean) {
    (viewBinding as? ActivityMainBinding)?.apply {
        if (isShow) {
            this.layoutLoadingPayment.visible()
        } else {
            this.layoutLoadingPayment.gone()
        }
    }
    (viewBinding as? FragmentStoryDetailBinding)?.apply {
        if (isShow) {
            this.layoutLoadingPayment.visible()
        } else {
            this.layoutLoadingPayment.gone()
        }
    }
}

fun BaseActivity<*>.goToShop() {
    (this as? StoryDetailActivity)?.apply {
        setResult(Activity.RESULT_OK)
        finish()
    }
    (this as? MainActivity)?.apply {
        val user = CoreApplication.instance.profile?.user
        if (user?.brand != null) {
            // for brand account
            this.viewBinding.btnNav.selectedItemId = R.id.nav_designer
        } else {
            // for normal user account
            this.viewBinding.btnNav.selectedItemId = R.id.nav_shop
        }
    }
}

fun MainActivity.goToShopFromWishList() {
    // temporary
    val user = CoreApplication.instance.profile?.user
    if (user?.brand != null) {
        // for brand account
        btnNav.selectedItemId = R.id.nav_designer
    } else {
        // for normal user account
        btnNav.selectedItemId = R.id.nav_shop
    }
}

fun MainActivity.removeFromWishListAfterCheckout(isGetWishList: Boolean) {
    if (isGetWishList.not()) {
        wishListViewModel.getWishList()
    } else {
        CoreApplication.instance.basket?.toProductId()?.forEach {
            if (ObjectHandler.isInWishList(it)) {
                wishListViewModel.removeFromWishList(it)
            }
        }
        refreshBashKet()
    }
}

private fun MainActivity.refreshBashKet() {
    shoppingViewModel.getBasket()
}