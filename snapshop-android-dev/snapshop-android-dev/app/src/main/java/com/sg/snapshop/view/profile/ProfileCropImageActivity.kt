package com.sg.snapshop.view.profile

import android.app.Activity
import android.content.Intent
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.constant.KEY_FILE
import com.sg.snapshop.databinding.ActivityProfileCropImageBinding
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
