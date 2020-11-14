package com.ptithcm.ptshop.view.card

import androidx.lifecycle.Observer
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.databinding.FragmentBrandTermConditionBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.isShowErrorNetwork
import com.ptithcm.ptshop.ext.isShowLoading
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.viewmodel.ShoppingViewModel
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