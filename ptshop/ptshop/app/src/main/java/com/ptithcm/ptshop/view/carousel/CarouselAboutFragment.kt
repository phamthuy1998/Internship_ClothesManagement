package com.ptithcm.ptshop.view.carousel

import androidx.lifecycle.Observer
import com.ptithcm.core.model.Brand
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.databinding.FragmentCarouselAboutBinding
import com.ptithcm.ptshop.viewmodel.CarouselDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CarouselAboutFragment : BaseFragment<FragmentCarouselAboutBinding>() {

    override val layoutId: Int = R.layout.fragment_carousel_about

    private val viewModel: CarouselDetailViewModel by sharedViewModel(from = { requireParentFragment() })
    private var vendorBrand: Brand? = null


    override fun bindViewModel() {
        super.bindViewModel()

        viewModel.storiesLiveData.observe(this, Observer {
            vendorBrand = it.first
            viewBinding.bio = if (it.first.bio.isNullOrEmpty()) "Empty" else it.first.bio
            viewBinding.term = if (it.first.terms.isNullOrEmpty() && it.first.return_policy.isNullOrEmpty()) "Empty" else it.first.terms+"\n"+it.first.return_policy
        })
    }

}