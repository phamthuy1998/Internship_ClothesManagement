package com.ptithcm.ptshop.view.rating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.ptithcm.core.CoreApplication
import com.ptithcm.core.model.Question
import com.ptithcm.core.model.Rating
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.constant.IS_PRODUCT
import com.ptithcm.ptshop.constant.KEY_EMPTY
import com.ptithcm.ptshop.databinding.FragmentQuestionBinding
import com.ptithcm.ptshop.databinding.FragmentRatingBinding
import com.ptithcm.ptshop.databinding.LayoutPopUpBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.util.PopUp
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.question.adapter.ITEM_DEL
import com.ptithcm.ptshop.view.question.adapter.ITEM_EDIT
import com.ptithcm.ptshop.view.question.adapter.ITEM_REPLY
import com.ptithcm.ptshop.view.question.adapter.QuestionAdapter
import com.ptithcm.ptshop.view.rating.adapter.RatingAdapter
import com.ptithcm.ptshop.view.rating.adapter.RatingAdapter.Companion.ITEM_IMAGE
import com.ptithcm.ptshop.viewmodel.QuestionsViewModel
import com.ptithcm.ptshop.viewmodel.RatingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RatingFragment : BaseFragment<FragmentRatingBinding>() {

    override val layoutId: Int = R.layout.fragment_rating

    private val viewModel: RatingViewModel by viewModel()

    private var productId: Int? = null
    private val adapter: RatingAdapter by lazy {
        RatingAdapter(this::adapterEvent, CoreApplication.instance.account?.id)
    }

    private fun adapterEvent(
        item: Rating?,
        position: Int?,
        typeEvent: Int,
        posImage: Int?
    ) {
        when (typeEvent) {
            ITEM_DEL -> {

            }
            ITEM_EDIT -> {

            }
            ITEM_IMAGE -> {
                val listImage: ArrayList<String> = arrayListOf()
                listImage.add(item?.imageUrl1 ?: "")
                listImage.add(item?.imageUrl2 ?: "")
                listImage.add(item?.videoUrl ?: "")
                navController.navigate(
                    R.id.fragment_over_view,
                    bundleOf(
                        "list" to listImage,
                        "pos" to posImage,
                        "fromRating" to true
                    )
                )
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewBinding.shimmerViewContainer.stopShimmer()
        viewBinding.shimmerViewContainer.gone()
    }

    override fun onResume() {
        super.onResume()
        viewBinding.shimmerViewContainer.startShimmer()
        viewBinding.shimmerViewContainer.visible()
    }

    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
        productId = arguments?.get("productId") as Int?
        viewModel.getRatings(productId ?: return)
        setupRecyclerview()
        viewBinding.tvWriteReview.setOnClickListener {
            navController.navigateAnimation(
                R.id.createReviewFragment,
                bundle = bundleOf(
                    "productId" to productId,
                    "rating" to viewBinding.ratingBar.rating
                )
            )
        }
        viewBinding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            navController.navigateAnimation(
                R.id.createReviewFragment,
                bundle = bundleOf(
                    "productId" to productId,
                    "rating" to viewBinding.ratingBar.rating
                )
            )
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
            initToolBar(viewBinding.layoutToolbar.toolbar, hasBackRight = false)
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                getString(R.string.rating)
            )
        }
    }


    private fun setupRecyclerview() {
        viewBinding.rvReview.adapter = adapter
        viewBinding.swlRefresh.setOnRefreshListener {
            viewModel.getRatings(productId ?: return@setOnRefreshListener)
        }
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.ratingList.observe(this, Observer {
            viewBinding.swlRefresh.isRefreshing = false
            viewBinding.shimmerViewContainer.stopShimmer()
            viewBinding.shimmerViewContainer.gone()
            if (it == null || it.size == 0) {
                viewBinding.groupNoRating.visible()
            } else {
                viewBinding.groupNoRating.gone()
                adapter.submitList(it)
            }
        })
        viewModel.networkState.observe(this, Observer {
            if (it == true) viewBinding.progressbar.visible() else viewBinding.progressbar.gone()
        })
        viewModel.error.observe(this, Observer {
            viewBinding.progressbar.gone()
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageHandler(it.first)
            }
        })
    }
}