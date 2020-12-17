package com.n16dccn159.admin.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T : Any?> clone(obj: T?): T? =
    Gson().fromJson(Gson().toJson(obj), object : TypeToken<T>() {}.type)