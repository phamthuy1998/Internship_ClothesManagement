package com.ptithcm.core.util

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ptithcm.core.model.*

//KEY WORD
const val INIT_PAGE = 1
const val PAGE_SIZE = 24
const val USER_PROFILE = "USER_PROFILE"
const val CATEGORY = "CATEGORY"
const val USER_ACCOUNT = "USER_ACCOUNT"
const val CURRENCY = "CURRENCY"
const val BASKET = "BASKET"
const val CART = "CART"
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

    var account: Account?
        get() {
            return try {
                gSon.fromJson(
                    prefs.getString(USER_ACCOUNT, null),
                    Account::class.java
                )
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(
            USER_ACCOUNT,
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

    var cart: Cart?
        get() {
            return try {
                gSon.fromJson<Cart>(
                    prefs.getString(CART, null)
                    , Basket::class.java
                )
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(
            CART,
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

const val PREFS_NAME = "PREFERENCES"
const val USER_ID = "USER_ID"

fun Context.removeValueSharePrefs(KEY_NAME: String) {
    val pref: SharedPreferences = getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = pref.edit()
    editor.remove(KEY_NAME)
    editor.apply()
}

fun Context.getIntPref(valueName: String): Int {
    val pref = getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
    return pref.getInt(valueName, -1)
}

fun Context.getBooleanPref(valueName: String): Boolean {
    val pref = getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
    return pref.getBoolean(valueName, false)
}


var Context.darkMode
    get() = this.getIntPref("THEME")
    set(value) = this.setIntPref("THEME", value)

fun Context.setIntPref(valueName: String, value: Int) {
    val pref: SharedPreferences = getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putInt(valueName, value)
    editor.apply()
}


fun Context.getStringPref(valueName: String): String? {
    val pref = getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
    return pref.getString(valueName, "")
}

fun Context.setStringPref(valueName: String, value: String) {
    val pref: SharedPreferences = getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putString(valueName, value)
    editor.apply()
}

fun Context.setBooleanPref(valueName: String, value: Boolean) {
    val pref: SharedPreferences = getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putBoolean(valueName, value)
    editor.apply()
}