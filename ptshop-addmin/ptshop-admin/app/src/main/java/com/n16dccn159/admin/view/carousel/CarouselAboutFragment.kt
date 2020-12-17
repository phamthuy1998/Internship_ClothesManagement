package com.n16dccn159.admin.view.carousel

import androidx.lifecycle.Observer
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.databinding.FragmentCarouselAboutBinding
import com.n16dccn159.admin.viewmodel.ProvidersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CarouselAboutFragment : BaseFragment<FragmentCarouselAboutBinding>() {

    override val layoutId: Int = R.layout.fragment_carousel_about

    private val providersViewModel: ProvidersViewModel by sharedViewModel(from = { requireParentFragment() })

    override fun bindViewModel() {
        super.bindViewModel()

        providersViewModel.providerDetailResult.observe(this, Observer {
            viewBinding.information = it.infomation
        })
    }
}