package com.n16dccn159.core.util

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.n16dccn159.core.model.Account
import com.n16dccn159.core.model.Cart
import com.n16dccn159.core.model.Profile
import com.n16dccn159.core.model.ShoppingAddress

//KEY WORD
const val INIT_PAGE = 1
const val PAGE_SIZE = 20
const val USER_PROFILE = "USER_PROFILE"
const val USER_ACCOUNT = "USER_ACCOUNT"
const val DEFAULT_ADDRESS = "DEFAULT_ADDRESS"
const val CART = "CART"
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

    var cart: Cart?
        get() {
            return try {
                gSon.fromJson<Cart>(
                    prefs.getString(CART, null)
                    , Cart::class.java
                )?.apply {
                    products.sortBy { it.id }
                }
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(
            CART,
            gSon.toJson(value?.apply { products.sortBy { it.id } })
        ).apply()

    var defaultAddress: ShoppingAddress?
        get() {
            return try {
                gSon.fromJson(
                    prefs.getString(DEFAULT_ADDRESS, null),
                    ShoppingAddress::class.java
                )
            } catch (e: Exception) {
                null
            }
        }
        set(value) = prefs.edit().putString(
            DEFAULT_ADDRESS,
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