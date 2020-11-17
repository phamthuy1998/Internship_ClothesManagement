package com.ptithcm.admin.view.profile

import android.app.Activity
import android.content.Intent
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseActivity
import com.ptithcm.admin.constant.KEY_FILE
import com.ptithcm.admin.databinding.ActivityProfileCropImageBinding
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
