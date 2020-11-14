package com.ptithcm.core.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ptithcm.core.util.PrefUtil
import org.koin.dsl.module

const val PREFS_FILENAME = "com.sg.snapShop"
const val DB_NAME = "snapShop_db"

val localModule = module {
    single { Gson() }
    single { provideSharedPreference(get()) }
    single { providePreferenceHelper(get(), get(), get()) }
//    single {
//        Room.databaseBuilder(
//            androidApplication(),
//            SnapShopDb::class.java,
//            DB_NAME
//        )
//            .build()
//    }

    // init room interface
//    factory { get<SnapShopDb>().productDao() }
}

fun providePreferenceHelper(context: Context,
                            sharedPreferences: SharedPreferences, gSon: Gson) =
    PrefUtil(context, sharedPreferences, gSon)

fun provideSharedPreference(context: Context): SharedPreferences =
    context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)