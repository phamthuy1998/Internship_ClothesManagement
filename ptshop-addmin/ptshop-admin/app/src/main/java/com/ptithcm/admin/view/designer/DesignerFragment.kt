package com.ptithcm.admin.view.designer

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.Provider
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.KEY_ARGUMENT
import com.ptithcm.admin.constant.KEY_DESIGNER
import com.ptithcm.admin.databinding.FragmentDesignerBinding
import com.ptithcm.admin.ext.*
import com.ptithcm.admin.view.MainActivity
import com.ptithcm.admin.viewmodel.ProvidersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DesignerFragment : BaseFragment<FragmentDesignerBinding>() {

    override val layoutId: Int = R.layout.fragment_designer

    private val providersViewModel: ProvidersViewModel by viewModel()

    private val designerAdapter = DesignerAdapter(this::eventListener)
    private var isError = false

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
            initToolBar(
                viewBinding.layoutToolbar.toolbar, false, hasBackRight = false
            )
            setupToolbar(
                viewBinding.layoutToolbar.toolbar, getString(R.string.designer),
                messageQueue = {
                    when (it.id) {
                        R.id.ivRight, R.id.tvCount -> {
                            navController.navigateAnimation(
                                R.id.nav_shopping_card,
                                isBotToTop = true, bundle = bundleOf(KEY_ARGUMENT to true)
                            )
                        }
                        R.id.ivLeft -> {
                            navController.navigate(R.id.nav_search)
                        }
                    }
                }
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getProviders()
        response()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setUpAdapter()
    }

    private fun response() {
        providersViewModel.providersResult.observe(this, Observer {
            designerAdapter.setList(it)
        })

        providersViewModel.isLoading.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowLoading(it)
        })

        providersViewModel.error.observe(this, Observer {
            isError =  true
            (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
        })
    }

    override fun bindEvent() {
        (requireActivity() as MainActivity).btnNav.visible()
        viewBinding.btnFab.setOnClickListener {
            viewBinding.rcvBrand.smoothScrollToPosition(0)
        }
        viewBinding.swDesigner.setOnRefreshListener {
            isError = false
            getProviders()
            viewBinding.swDesigner.isRefreshing = false
        }
    }

    private fun setUpAdapter() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        viewBinding.rcvBrand.apply {
            layoutManager = gridLayoutManager
            adapter = designerAdapter
            val listenerScrollFab = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() != 0) {
                        viewBinding.btnFab.visible()
                    } else {
                        viewBinding.btnFab.gone()
                    }
                }
            }
            addOnScrollListener(listenerScrollFab)
        }
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0 -> 3
                    else -> {
                        if (isError) 3
                        else 1
                    }
                }
            }
        }
    }

    private fun eventListener(provider: Provider?) {
        navController.navigate(
            R.id.action_carousel_detail,
            bundleOf(KEY_ARGUMENT to provider, KEY_DESIGNER to true)
        )
    }

    private fun getProviders() {
        providersViewModel.getProviders()
    }
}