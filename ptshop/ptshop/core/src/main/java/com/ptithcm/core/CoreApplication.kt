package com.ptithcm.core

//import com.sendbird.uikit.SendBirdUIKit
//import com.sendbird.uikit.adapter.SendBirdUIKitAdapter
//import com.sendbird.uikit.interfaces.UserInfo
import android.app.Application
import com.ptithcm.core.di.localModule
import com.ptithcm.core.di.remoteModule
import com.ptithcm.core.di.repositoryModule
import com.ptithcm.core.model.*
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

//    fun setupSendBird(){
//        SendBirdUIKit.init(object : SendBirdUIKitAdapter {
//            override fun getAppId(): String {
//                return "DD3C17AC-2220-4213-BD77-BE06CF592099" // The ID of the Sendbird application which UIKit sample app uses.
//            }
//
//            override fun getAccessToken(): String {
//                return ""
//            }
//
//            override fun getUserInfo(): UserInfo {
//                return object : UserInfo {
//
//                    override fun getUserId(): String {
//                        return USER_ID
//                    }
//
//                    override fun getNickname(): String {
//                        return USER_ID
//                    }
//
//                    override fun getProfileUrl(): String {
//                        return ""
//                    }
//                }
//            }
//        }, this)
//    }

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