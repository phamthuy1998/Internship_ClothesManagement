package com.n16dccn159.admin.view.invoices

import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.KEY_ARGUMENT
import com.n16dccn159.admin.databinding.FragmentInvoiceDetailBinding
import com.n16dccn159.admin.ext.initToolBar
import com.n16dccn159.admin.ext.navigateAnimation
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.ext.visible
import com.n16dccn159.admin.util.DateUtils
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.home.StoryDetailActivity
import com.n16dccn159.admin.view.invoices.ProductInvoiceAdapter.Companion.ITEM_CLICK
import com.n16dccn159.admin.view.invoices.ProductInvoiceAdapter.Companion.ITEM_WRITE_REVIEW
import com.n16dccn159.admin.viewmodel.UserViewModel
import com.n16dccn159.core.model.InvoiceDetail
import com.n16dccn159.core.model.InvoiceProductDetail
import com.n16dccn159.core.util.PriceFormat.priceFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceDetailFragment : BaseFragment<FragmentInvoiceDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_invoice_detail

    private val productInvoiceAdapter = ProductInvoiceAdapter(this::itemClick)

    private val userViewModel: UserViewModel by viewModel()

    private var invoiceDetail: InvoiceDetail? = null

    private fun itemClick(item: InvoiceProductDetail, type: Int) {
        if(type ==ITEM_CLICK){
            navController.navigateAnimation(
                R.id.fragment_product_detail,
                bundle = bundleOf(
                    "productId" to item.id
                )
            )
        }else if(type==ITEM_WRITE_REVIEW) {
            navController.navigateAnimation(
                R.id.createReviewFragment,
                bundle = bundleOf(
                    "product" to item
                )
            )
        }
    }

    override fun bindEvent() {
        setUpRv()
        setUpToolbar()
        userViewModel.getInvoiceDetail(arguments?.getInt(KEY_ARGUMENT))
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