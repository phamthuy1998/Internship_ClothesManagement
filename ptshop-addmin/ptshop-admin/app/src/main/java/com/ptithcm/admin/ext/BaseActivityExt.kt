package com.ptithcm.admin.ext

import android.app.Activity
import android.view.View
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseActivity
import com.ptithcm.admin.databinding.ActivityMainBinding
import com.ptithcm.admin.databinding.FragmentStoryDetailBinding
import com.ptithcm.admin.view.MainActivity
import com.ptithcm.admin.view.home.StoryDetailActivity
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
    (this as? MainActivity)?.viewBinding?.btnNav?.selectedItemId = R.id.nav_shop
}

fun MainActivity.goToShopFromWishList() {
    btnNav.selectedItemId = R.id.nav_shop
}