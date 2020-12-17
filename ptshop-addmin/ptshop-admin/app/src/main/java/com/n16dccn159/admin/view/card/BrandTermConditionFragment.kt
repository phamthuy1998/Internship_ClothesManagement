package com.n16dccn159.admin.view.card

import androidx.lifecycle.Observer
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.ERROR_CODE_404
import com.n16dccn159.admin.databinding.FragmentBrandTermConditionBinding
import com.n16dccn159.admin.ext.initToolBar
import com.n16dccn159.admin.ext.isShowErrorNetwork
import com.n16dccn159.admin.ext.isShowLoading
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.viewmodel.ShoppingViewModel
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