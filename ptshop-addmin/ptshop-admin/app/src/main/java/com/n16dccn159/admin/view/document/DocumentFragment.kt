package com.n16dccn159.admin.view.document

import android.os.Bundle
import android.view.View
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.KEY_ARGUMENT
import com.n16dccn159.admin.databinding.FragmentDocumentBinding
import com.n16dccn159.admin.ext.initToolBar
import com.n16dccn159.admin.ext.setupToolbar
import com.n16dccn159.admin.view.MainActivity
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