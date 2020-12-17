package com.n16dccn159.admin.view.rating

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.n16dccn159.core.CoreApplication
import com.n16dccn159.core.model.ProductClothesDetail
import com.n16dccn159.core.model.Rating
import com.n16dccn159.core.model.RatingAvg
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.ERROR_CODE_404
import com.n16dccn159.admin.databinding.FragmentRatingBinding
import com.n16dccn159.admin.databinding.LayoutPopUpBinding
import com.n16dccn159.admin.ext.*
import com.n16dccn159.admin.util.PopUp
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.question.adapter.ITEM_DEL
import com.n16dccn159.admin.view.question.adapter.ITEM_EDIT
import com.n16dccn159.admin.view.rating.adapter.RatingAdapter
import com.n16dccn159.admin.view.rating.adapter.RatingAdapter.Companion.ITEM_IMAGE
import com.n16dccn159.admin.viewmodel.RatingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RatingFragment : BaseFragment<FragmentRatingBinding>() {

    override val layoutId: Int = R.layout.fragment_rating

    private val viewModel: RatingViewModel by viewModel()

    private var productDetail: ProductClothesDetail? = null
    private val adapter: RatingAdapter by lazy {
        RatingAdapter(this::adapterEvent, CoreApplication.instance.account?.id)
    }

    companion object {
        var fragment: RatingFragment? = null
        fun newInstance(productDetail: ProductClothesDetail): RatingFragment? {
            if (fragment == null) fragment = RatingFragment()
            fragment?.productDetail = productDetail
            return fragment
        }
    }

    private fun adapterEvent(
        item: Rating?,
        position: Int?,
        typeEvent: Int,
        posImage: Int?
    ) {
        when (typeEvent) {
            ITEM_DEL -> {
                (requireActivity() as? BaseActivity<*>)?.showPopup(
                    PopUp(R.layout.layout_pop_up, messageQueue = { popupBinding ->
                        (popupBinding as? LayoutPopUpBinding)?.apply {
                            title = getString(R.string.confirmDelQuestion)
                            left = getString(R.string.ok)
                            right = getString(R.string.cancel)
                            btnOk.setOnClickListener {
                                item?.ratingID?.let { ratingID ->
                                    viewModel.delRating(ratingID)
                                }
                                (requireActivity() as? BaseActivity<*>)?.closePopup()
                                adapter.removeItem(position)
                                refreshData()
                            }
                            btnCancel.setOnClickListener {
                                (requireActivity() as? BaseActivity<*>)?.closePopup()
                            }
                        }

                    })
                )

            }
            ITEM_EDIT -> {
                navController.navigate(
                    R.id.createReviewFragment,
                    bundleOf(
                        "rating" to item
                    )
                )
            }
            ITEM_IMAGE -> {
                val listImage: ArrayList<String> = arrayListOf()
                listImage.add(item?.imageUrl1 ?: "")
                listImage.add(item?.imageUrl2 ?: "")
                listImage.add(item?.videoUrl ?: "")
                navController.navigate(
                    R.id.imageVideoFragment,
                    bundleOf(
                        "list" to listImage,
                        "pos" to posImage
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
        activity?.btnNav?.visibility = View.GONE
    }

    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
        productDetail = arguments?.get("productDetail") as ProductClothesDetail?
        setupRecyclerview()
        refreshData()
        /*   viewBinding.tvWriteReview.setOnClickListener {
               navController.navigateAnimation(
                   R.id.createReviewFragment,
                   bundle = bundleOf(
                       "productDetail" to productDetail,
                       "rating" to viewBinding.ratingBar.rating
                   )
               )
           }
           viewBinding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
               navController.navigateAnimation(
                   R.id.createReviewFragment,
                   bundle = bundleOf(
                       "productDetail" to productDetail,
                       "rating" to viewBinding.ratingBar.rating
                   )
               )
           }*/
    }

    private fun refreshData() {
        viewModel.getRatings(productDetail?.id ?: return)
//        viewModel.checkRating(productDetail?.id ?: 0, CoreApplication.instance.account?.id ?: 0)
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(viewBinding.layoutToolbar.toolbar, hasBackRight = false)
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                getString(R.string.rating)
            )
            initToolbar(
                hasBackRight = false,
                hasLeft = false,
                hasRight = false,
                hasCount = false,
                isProductPage = false
            )
        }
    }


    private fun setupRecyclerview() {
        viewBinding.rvReview.adapter = adapter
        viewBinding.swlRefresh.setOnRefreshListener { refreshData() }
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
                setRatingAverage(it)
            }
        })
        viewModel.networkState.observe(this, Observer {
            if (it == true) viewBinding.progressbar.visible() else viewBinding.progressbar.gone()
        })
//        viewModel.checkRating.observe(this, Observer {
//            if (it.data != "0") {
//                viewBinding.groupWriteRating.gone()
//            } else viewBinding.groupWriteRating.visible()
//        })
        viewModel.error.observe(this, Observer {
            viewBinding.progressbar.gone()
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageHandler(it.first)
            }
        })
    }

    private fun setRatingAverage(
        arrRatings: java.util.ArrayList<Rating>?,
        fromAdapter: Boolean = false
    ) {
        val ratingAvg = RatingAvg()
        ratingAvg.totalRating = arrRatings?.size?.toFloat()
        if (fromAdapter) ratingAvg.totalRating = ratingAvg.totalRating?.minus(1)
        if (ratingAvg.totalRating == null || ratingAvg.totalRating == 0F) return
        var rating1Count = 0F
        var rating2Count = 0F
        var rating3Count = 0F
        var rating4Count = 0F
        var rating5Count = 0F
        arrRatings?.forEach { rating: Rating ->
            when (rating.rating) {
                1 -> rating1Count++
                2 -> rating2Count++
                3 -> rating3Count++
                4 -> rating4Count++
                5 -> rating5Count++
            }
        }
        ratingAvg.rating1Count = rating1Count
        ratingAvg.rating2Count = rating2Count
        ratingAvg.rating3Count = rating3Count
        ratingAvg.rating4Count = rating4Count
        ratingAvg.rating5Count = rating5Count
        ratingAvg.ratingAvg =
            (rating1Count + rating2Count * 2 + rating3Count * 3 + rating4Count * 4 + rating5Count * 5.toFloat()).div(
                ratingAvg.totalRating ?: 0F
            )
        ratingAvg.ratingAvg = ratingAvg.ratingAvg?.round(2)
        ratingAvg.rating5Percent =
            ratingAvg.rating5Count?.div(ratingAvg.totalRating ?: 1F)?.times(100)?.toInt() ?: 0
        ratingAvg.rating4Percent =
            ratingAvg.rating4Count?.div(ratingAvg.totalRating ?: 1F)?.times(100)?.toInt() ?: 0
        ratingAvg.rating3Percent =
            ratingAvg.rating3Count?.div(ratingAvg.totalRating ?: 1F)?.times(100)?.toInt() ?: 0
        ratingAvg.rating2Percent =
            ratingAvg.rating2Count?.div(ratingAvg.totalRating ?: 1F)?.times(100)?.toInt() ?: 0
        ratingAvg.rating1Percent =
            ratingAvg.rating1Count?.div(ratingAvg.totalRating ?: 1F)?.times(100)?.toInt() ?: 0

        adapter.setDataRating(ratingAvg)
    }
}