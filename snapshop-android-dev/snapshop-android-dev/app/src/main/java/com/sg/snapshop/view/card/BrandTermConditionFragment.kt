package com.sg.snapshop.view.card

import androidx.lifecycle.Observer
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.constant.KEY_ARGUMENT_INT
import com.sg.snapshop.databinding.FragmentBrandTermConditionBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.isShowErrorNetwork
import com.sg.snapshop.ext.isShowLoading
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.viewmodel.ShoppingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrandTermConditionFragment : BaseFragment<FragmentBrandTermConditionBinding>(){

    override val layoutId: Int
        get() = R.layout.fragment_brand_term_condition

    private val shoppingViewModel: ShoppingViewModel by viewModel()

    override fun bindEvent() {
        arguments?.getInt(KEY_ARGUMENT_INT)?.let {
            shoppingViewModel.getBrandDetail(it)
        }

        setUpToolBar()
    }

    override fun bindViewModel() {
        shoppingViewModel.brandDetailResult.observe(this, Observer {
            viewBinding.termCondition.text = if (it.terms.isNullOrEmpty()) getString(R.string.term_condition_empty) else it.terms
        })

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