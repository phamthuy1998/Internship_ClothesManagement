package com.n16dccn159.admin.view.profile

import android.app.Activity
import android.content.Intent
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.constant.KEY_FILE
import com.n16dccn159.admin.databinding.ActivityProfileCropImageBinding
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
