package com.ptithcm.admin.view.card

import androidx.lifecycle.Observer
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.ERROR_CODE_404
import com.ptithcm.admin.databinding.FragmentBrandTermConditionBinding
import com.ptithcm.admin.ext.initToolBar
import com.ptithcm.admin.ext.isShowErrorNetwork
import com.ptithcm.admin.ext.isShowLoading
import com.ptithcm.admin.ext.setupToolbar
import com.ptithcm.admin.view.MainActivity
import com.ptithcm.admin.viewmodel.ShoppingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrandTermConditionFragment : BaseFragment<FragmentBrandTermConditionBinding>(){

    override val layoutId: Int
        get() = R.layout.fragment_brand_term_condition

    private val shoppingViewModel: ShoppingViewModel by viewModel()

    override fun bindEvent() {
        setUpToolBar()
    }

    override fun bindViewModel() {
        shoppingViewModel.isLoading.observe(this, Observer {
            (activity as? MainActivity)?.isShowLoading(it)
        })

        shoppingViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404){
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            }
        })
    }

    private fun setUpToolBar(){
        (activity as? MainActivity)?.apply {
            initToolBar(viewBinding.layoutToolbar.toolbar,
                hasBack = true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false)

            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.terms_and_conditions))
        }
    }
}