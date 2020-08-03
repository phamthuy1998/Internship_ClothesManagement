package com.ptithcm.core

import android.app.Application
import com.ptithcm.core.di.localModule
import com.ptithcm.core.di.remoteModule
import com.ptithcm.core.di.repositoryModule
import com.ptithcm.core.model.Account
import com.ptithcm.core.model.Basket
import com.ptithcm.core.model.Cart
import com.ptithcm.core.model.Profile
import com.ptithcm.core.util.PrefUtil
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

open class CoreApplication : Application() {

    companion object {
        lateinit var instance: CoreApplication
            private set
    }

    var profile: Profile? = null
    var account: Account? = null
    var basket: Basket? = null
    var cart: Cart? = null

    private val prefsUtil: PrefUtil by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CoreApplication)
            modules(getModule())
        }

        instance = this
        profile = prefsUtil.profile
        account = prefsUtil.account
        cart = prefsUtil.cart ?: Cart()
    }

    fun isNetworkConnected(): Boolean {
        return prefsUtil.isNetworkConnected()
    }

    private fun getModule(): List<Module> {
        val moduleList = arrayListOf<Module>()
        moduleList.addAll(listOf(remoteModule, repositoryModule, localModule))
        moduleList.addAll(addMoreModule())
        return moduleList
    }

    fun saveUser(profile: Profile) {
        prefsUtil.profile = profile
        this.profile = profile
    }

    fun saveAccount(account: Account) {
        prefsUtil.account = account
        this.account = account
    }

    fun clearAccount() {
        prefsUtil.account = null
        this.account = null
    }

    fun clearCart() {
        this.cart = null
        prefsUtil.cart = null
    }

    fun saveCartToPref(cart: Cart?) {
        prefsUtil.cart = cart
    }

    fun getCartFromPref(): Cart? {
        cart = prefsUtil.cart
        return cart
    }

    open fun addMoreModule(): List<Module> = emptyList()

}