package com.sg.core.util

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sg.core.model.Basket
import com.sg.core.model.ProductVariant
import com.sg.core.model.Profile
import com.sg.core.model.SupportedCurrency

//KEY WORD
const val INIT_PAGE = 1
const val PAGE_SIZE = 24
const val USER_PROFILE = "USER_PROFILE"
const val CURRENCY = "CURRENCY"
const val BASKET = "BASKET"
const val BASKET_NOT_LOGIN = "BASKET_NOT_LOGIN"
const val PRODUCT_IN_WISH_LIST = "PRODUCT_IN_WISH_LIST"
const val SETTINGS = "SETTINGS"
const val ORDERING_HIGH = "-price_range_ordering"
const val ORDERING_LOW = "price_range_ordering"

class PrefUtil constructor(
    private val context: Context,
    private val prefs: SharedPreferences,
    private val gSon: Gson
) {

    fun isNetworkConnected(): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.getNetworkCapabilities(cm.activeNetwork)?.run {
            result = when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        return result
    }

    fun clearAllData() = prefs.edit().clear().commit()

    var profile: Profile?
        get() {
            return try {
                gSon.fromJson(
                    prefs.getString(USER_PROFILE, null),
                    Profile::class.java
                )
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(
            USER_PROFILE,
            gSon.toJson(value)
        ).apply()

    var currency: SupportedCurrency
        get() {
            return try {
                gSon.fromJson(
                    prefs.getString(CURRENCY, null),
                    SupportedCurrency::class.java
                )
            } catch (e: Exception) {
                SupportedCurrency()
            }
        }
        set(value) = prefs.edit().putString(
            CURRENCY,
            gSon.toJson(value)
        ).apply()

    var basket: Basket?
        get() {
            return try {
                gSon.fromJson<Basket>(
                    prefs.getString(BASKET, null)
                    , Basket::class.java
                )
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(
            BASKET,
            gSon.toJson(value)
        ).apply()

    var notLoginBasket: ArrayList<ProductVariant>?
        get() {
            val type = object : TypeToken<ArrayList<ProductVariant>>() {}.type
            return try {
                gSon.fromJson<ArrayList<ProductVariant>>(
                    prefs.getString(BASKET_NOT_LOGIN, null),
                    type
                )
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(
            BASKET_NOT_LOGIN,
            gSon.toJson(value)
        ).apply()

    var prodInWishList: ArrayList<Int>?
        get() {
            val type = object : TypeToken<ArrayList<Int>>() {}.type
            return try {
                gSon.fromJson<ArrayList<Int>>(
                    prefs.getString(PRODUCT_IN_WISH_LIST, null),
                    type
                )
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(
            PRODUCT_IN_WISH_LIST,
            gSon.toJson(value)
        ).apply()
}