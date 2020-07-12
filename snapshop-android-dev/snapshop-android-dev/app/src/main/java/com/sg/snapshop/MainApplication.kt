package com.sg.snapshop

import androidx.lifecycle.LifecycleObserver
import com.facebook.stetho.Stetho
import com.sg.core.CoreApplication
import org.koin.core.module.Module

class MainApplication: CoreApplication(), LifecycleObserver {

    override fun addMoreModule(): List<Module> = listOf(appModule)

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}