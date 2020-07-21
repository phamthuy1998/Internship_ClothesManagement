package com.ptithcm.core

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

open class CoreApplication : Application(){

    companion object{
        lateinit var instance: CoreApplication
            private set
    }

    var profile: Profile? = null
    var account: Account? = null
    var currency: SupportedCurrency = SupportedCurrency()
    var basket: Basket? = null
    // wait for login to sync all product variant save in local to BE
    var notLoginBasket: ArrayList<ProductVariant> = arrayListOf()

    var prodWishList = arrayListOf<Int>()

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
        currency = prefsUtil.currency.checkType()
        basket = prefsUtil.basket
        notLoginBasket = prefsUtil.notLoginBasket ?: arrayListOf()
        prodWishList = prefsUtil.prodInWishList ?: arrayListOf()
    }

    fun isNetworkConnected() : Boolean{
        return prefsUtil.isNetworkConnected()
    }

    private fun getModule(): List<Module> {
        val moduleList = arrayListOf<Module>()
        moduleList.addAll(listOf(remoteModule, repositoryModule, localModule))
        moduleList.addAll(addMoreModule())
        return moduleList
    }

    fun saveUser(profile: Profile){
        prefsUtil.profile = profile
        this.profile = profile
    }
    fun saveAccount(account: Account){
        prefsUtil.account = account
        this.account = account
    }

    fun saveCurrency(currency: SupportedCurrency){
        prefsUtil.currency = currency
        this.currency = currency
    }

    fun clearAccount(){
        prefsUtil.account = null
        this.account = null
        notLoginBasket.clear()
        removeAllFromWishList()
        clearBasket()
    }

    private fun clearCurrency(){
        this.currency = SupportedCurrency().checkType()
        prefsUtil.currency = this.currency
    }

    fun saveBasket(basket: Basket){
        this.basket = basket
        prefsUtil.basket = basket
        saveUser(profile?.copy(user = basket.saveAddressUser(profile?.user)) ?: return)
    }

    fun clearBasket() {
        this.basket = null
        prefsUtil.basket = null
    }

    fun clearLocalBasket(basket: Basket?){
        notLoginBasket.clear()
        prefsUtil.notLoginBasket = arrayListOf()
        saveBasket(basket ?: return)
    }

    fun saveBasketToPref(notLoginBasket: ArrayList<ProductVariant>){
        prefsUtil.notLoginBasket = notLoginBasket
    }

    fun saveWishListToPref(list: ArrayList<Int>){
        prefsUtil.prodInWishList = list
    }

    private fun removeAllFromWishList(){
        prodWishList.clear()
        prefsUtil.prodInWishList = prodWishList
    }

    open fun addMoreModule(): List<Module> = emptyList()

}