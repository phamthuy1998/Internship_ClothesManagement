package com.ptithcm.ptshop.view.profile

import android.app.Activity
import android.content.Intent
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.constant.KEY_FILE
import com.ptithcm.ptshop.databinding.ActivityProfileCropImageBinding
import java.io.File

class ProfileCropImageActivity : BaseActivity<ActivityProfileCropImageBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_profile_crop_image

    fun returnIntent(file: File?) {
        val resultIntent = Intent()
        resultIntent.putExtra(KEY_FILE, file)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
