package com.ptithcm.ptshop.view.document

import android.os.Bundle
import android.view.View
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.databinding.FragmentDocumentBinding
import com.ptithcm.ptshop.ext.initToolBar
import com.ptithcm.ptshop.ext.setupToolbar
import com.ptithcm.ptshop.view.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class DocumentFragment :BaseFragment<FragmentDocumentBinding>(){
    override val layoutId: Int
        get() = R.layout.fragment_document
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.visibility= View.GONE
        when (arguments?.getString(KEY_ARGUMENT)){
            "About Us" -> viewBinding.webView.loadUrl("file:///android_asset/about_us.html")
            "Terms & Conditions" -> viewBinding.webView.loadUrl("file:///android_asset/terms_conditions.html")
            "Customer Service" -> viewBinding.webView.loadUrl("file:///android_asset/customer_service.html")
            "Delivery & Returns" -> viewBinding.webView.loadUrl("file:///android_asset/delivery_return.html")
        }

        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar, true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, (arguments?.getString(KEY_ARGUMENT)?:"").capitalize())
        }
    }
}