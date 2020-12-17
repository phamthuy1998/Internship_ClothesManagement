package com.n16dccn159.admin.view.splash

import android.os.Bundle
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.databinding.ActivitySplashBinding
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.login.LoginActivity
import com.n16dccn159.core.CoreApplication
import org.jetbrains.anko.startActivity

class SplashActivity :BaseActivity<ActivitySplashBinding>(){
    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentAccount = CoreApplication.instance.account
        if(currentAccount==null){
            startActivity<LoginActivity>()
        }else{
            startActivity<MainActivity>()
        }
    }
}