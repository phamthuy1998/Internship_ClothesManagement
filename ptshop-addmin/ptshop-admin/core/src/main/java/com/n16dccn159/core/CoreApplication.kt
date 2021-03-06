package com.n16dccn159.core

import android.app.Application
import com.n16dccn159.core.di.localModule
import com.n16dccn159.core.di.remoteModule
import com.n16dccn159.core.di.repositoryModule
import com.n16dccn159.core.model.*
import com.n16dccn159.core.util.PrefUtil
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
    var defaultAddress: ShoppingAddress? = null

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
        defaultAddress = prefsUtil.defaultAddress

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
        this.cart = Cart()
        prefsUtil.cart = Cart()
    }

    fun saveCartToPref(cart: Cart?) {
        prefsUtil.cart = cart
    }

    fun getCartFromPref(): Cart? {
        cart = prefsUtil.cart
        return cart
    }

    fun saveDefaultAddress(address: ShoppingAddress?) {
        cart?.shippingAddress = defaultAddress
        saveCartToPref(cart)
        this.defaultAddress = address
        prefsUtil.defaultAddress = address
    }

    open fun addMoreModule(): List<Module> = emptyList()

}