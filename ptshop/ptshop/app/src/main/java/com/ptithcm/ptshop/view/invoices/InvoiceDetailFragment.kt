package com.ptithcm.ptshop.view.invoices

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.ptithcm.core.model.InvoiceDetail
import com.ptithcm.core.model.InvoiceProductDetail
import com.ptithcm.core.util.PriceFormat.priceFormat
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentInvoiceDetailBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.navigateAnimation
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.ext.visible
import com.ptithcm.ptshop.util.DateUtils
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.view.invoices.ProductInvoiceAdapter.Companion.ITEM_CLICK
import com.ptithcm.ptshop.view.invoices.ProductInvoiceAdapter.Companion.ITEM_WRITE_REVIEW
import com.ptithcm.ptshop.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceDetailFragment : BaseFragment<FragmentInvoiceDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_invoice_detail

    private val productInvoiceAdapter = ProductInvoiceAdapter(this::itemClick)

    private val userViewModel: UserViewModel by viewModel()

    private var invoiceDetail: InvoiceDetail? = null

    private fun itemClick(item: InvoiceProductDetail, type: Int) {
        if (type == ITEM_CLICK) {
            navController.navigateAnimation(
                R.id.fragment_product_detail,
                bundle = bundleOf(
                    "productId" to item.id
                )
            )
        } else if (type == ITEM_WRITE_REVIEW) {
            if (item.statusRating == 0)
                navController.navigateAnimation(
                    R.id.createReviewFragment,
                    bundle = bundleOf(
                        "product" to item,
                        "isViewRating" to false
                    )
                )
            else navController.navigateAnimation(
                R.id.createReviewFragment,
                bundle = bundleOf(
                    "product" to item,
                    "isViewRating" to true
                )
            )
        }
    }

    override fun bindEvent() {
        setUpRv()
        setUpToolbar()
        userViewModel.getInvoiceDetail(arguments?.getInt(KEY_ARGUMENT))

        viewBinding.swRefreshInvoiceDetail.setOnRefreshListener {
            userViewModel.getInvoiceDetail(arguments?.getInt(KEY_ARGUMENT))
            viewBinding.swRefreshInvoiceDetail.isRefreshing = false
        }
    }

    override fun bindViewModelOnce() {
        userViewModel.invoiceDetailLiveData.observe(this, Observer {
            invoiceDetail = it
            updateUIAddress()
            updateUIProduct()
            updateUINote()
            updateUIPrice()
        })
    }

    private fun setUpRv() {
        viewBinding.rvProduct.adapter = productInvoiceAdapter
    }

    private fun updateUIAddress() {
        viewBinding.shippingAddress.tvName.text = invoiceDetail?.name
        viewBinding.shippingAddress.tvPhone.text = invoiceDetail?.phone
        viewBinding.shippingAddress.tvAddress.text = invoiceDetail?.address
        viewBinding.shippingAddress.root.visible()
    }

    private fun updateUIProduct() {
        productInvoiceAdapter.setCanRating(invoiceDetail?.statusOrderId == 3)
        productInvoiceAdapter.addToList(invoiceDetail?.products)
    }

    private fun updateUINote() {
        viewBinding.includeNote.root.isVisible = !invoiceDetail?.note.isNullOrEmpty()
        viewBinding.includeNote.edtNote.text = invoiceDetail?.note
    }

    private fun updateUIPrice() {
        viewBinding.includePrice.subtotal = priceFormat(invoiceDetail?.price ?: 0.0)
        viewBinding.includePrice.total = priceFormat(invoiceDetail?.price ?: 0.0)

        viewBinding.includePrice.boughtDate = DateUtils.convertFormat(
            invoiceDetail?.buyDate,
            DateUtils.DATE_FORMAT_SERVER,
            DateUtils.DEFAULT_SERVER_DATE_FORMAT
        )
        viewBinding.includePrice.status = invoiceDetail?.statusInvoice
    }

    private fun setUpToolbar() {
        (activity as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                hasBack = true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.invoice_detail))
        }
        (activity as? StoryDetailActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                hasBack = true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.checkout))
        }
    }
}