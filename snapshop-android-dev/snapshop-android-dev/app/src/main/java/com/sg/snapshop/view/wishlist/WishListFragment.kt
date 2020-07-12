package com.sg.snapshop.view.wishlist

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sg.core.CoreApplication
import com.sg.core.model.Product
import com.sg.core.util.ObjectHandler
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.databinding.FragmentWishListBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.util.SpaceItemDecorator
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.viewmodel.WishListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class WishListFragment : BaseFragment<FragmentWishListBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_wish_list

    private val wishListViewModel: WishListViewModel by viewModel()

    private var position: Int? = null

    private val adapter = WishListAdapter(
        CoreApplication.instance.currency.getLocale() ?: Locale.UK
    ) { it: Any?, pos: Int? ->
        when (it) {
            // click to see detail
            is Product -> {
                navController.navigateAnimation(
                    R.id.nav_product,
                    bundle = bundleOf(KEY_ARGUMENT to it)
                )
            }
            // remove an item wish list
            is Int -> {
                wishListViewModel.removeFromWishList(it)
                this.position = pos
            }
        }
    }

    private val fabScroller = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as? GridLayoutManager
            if (layoutManager?.findFirstVisibleItemPosition() != 0) {
                viewBinding.btnFab.visible()
            } else {
                viewBinding.btnFab.gone()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewBinding.isLogin == true) {
            viewBinding.isNotEmpty = true
            wishListViewModel.getWishList()
            (activity as? MainActivity)?.isShowLoading(adapter.hasData().not())
        }
    }

    override fun bindEvent() {
        viewBinding.fragment = this
        viewBinding.isLogin = ObjectHandler.isLogin()
        if (viewBinding.isLogin == true) {
            setupRecyclerView()
        } else {
            (activity as? BaseActivity<*>)?.isShowLoading(false)
        }
        setUpToolBar()
    }

    override fun bindViewModelOnce() {
        wishListViewModel.wishListResult.observe(this, Observer {
            (activity as? MainActivity)?.isShowLoading(false)
            viewBinding.isNotEmpty = it.isNotEmpty()
            adapter.submitList(it)
        })

        wishListViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            }
        })

        wishListViewModel.removeResult.observe(this, Observer {})
    }

    private fun setUpToolBar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                hasBack = false,
                hasBackRight = false
            )
            viewBinding.layoutToolbar.toolbar.apply {
                isSelected = false
                ivRight?.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_shopping_bag
                    )
                )
            }
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.wishlist),
                messageQueue = {
                    when (it.id) {
                        R.id.ivRight,
                        R.id.tvCount -> {
                            navController.navigateAnimation(
                                R.id.nav_shopping_card, isBotToTop = true, bundle = bundleOf(
                                    KEY_ARGUMENT to true
                                )
                            )
                        }
                        R.id.ivLeft -> {
                            navController.navigate(R.id.nav_search)
                        }
                    }
                })
        }

        activity?.btnNav?.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        viewBinding.rvWishList.adapter = adapter
        viewBinding.rvWishList.addOnScrollListener(fabScroller)
        (viewBinding.rvWishList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        val separator = 3
        viewBinding.rvWishList.addItemDecoration(
            SpaceItemDecorator(
                separator,
                separator,
                separator,
                separator
            )
        )
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnStartShopping -> {
                if (viewBinding.isLogin == true) {
                    (activity as? MainActivity)?.goToShopFromWishList()
                } else {
                    navController.navigate(R.id.nav_login)
                }
            }
            R.id.btnFab -> {
                viewBinding.rvWishList.smoothScrollToPosition(0)
            }
        }
    }
}