package com.ptithcm.core.util

import java.util.concurrent.CopyOnWriteArraySet

object AppEvent{
    private var networkListener: Set<NetworkListener> = CopyOnWriteArraySet()

    fun registerNetworkListener(listener: NetworkListener?) {
        if (listener != null)
            networkListener = networkListener.plus(listener)
    }

    fun unRegisterNetworkListener(listener: NetworkListener?) {
        networkListener = networkListener.minus(listener ?: return)
    }

    fun onNoInternet(){
        for (listener in networkListener)
            listener.onNoInternet()
    }
}

interface NetworkListener {
    fun onNoInternet()
}