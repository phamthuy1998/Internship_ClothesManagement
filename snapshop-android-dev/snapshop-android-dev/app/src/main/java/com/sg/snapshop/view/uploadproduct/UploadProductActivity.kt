package com.sg.snapshop.view.uploadproduct

import android.os.Bundle
import android.os.Handler
import androidx.core.view.isVisible
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.databinding.ActivityUploadProductBinding
import com.sg.snapshop.ext.gone
import com.sg.snapshop.ext.visible
import com.sg.snapshop.viewmodel.ShareDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UploadProductActivity : BaseActivity<ActivityUploadProductBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_upload_product

    private val shareDataViewmodel by viewModel<ShareDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shareDataViewmodel.getAllTags()
    }

    fun isShowLoading(isShow : Boolean){
        if (isShow){
            viewBinding.layoutLoading.visible()
        }else {
            viewBinding.layoutLoading.gone()
        }
    }

    fun showError(msg: String) {
        if (!viewBinding.tvError.isVisible) {
            viewBinding.tvError.apply {
                text = msg
                visible()
            }
            Handler().postDelayed({
                viewBinding.tvError.gone()
            }, 2500)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
