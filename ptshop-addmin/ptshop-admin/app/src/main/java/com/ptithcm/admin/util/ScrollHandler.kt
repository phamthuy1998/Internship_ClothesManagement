package com.ptithcm.admin.util

import android.os.Handler
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ScrollHandler(private val recyclerView: RecyclerView) {

    private var position: Int = 0
    private var handler: Handler? = null
    private val updater = object : Runnable {
        override fun run() {
            if (position > 0) {
                when {
                    position < 50 -> {
                        recyclerView.scrollToPosition(0)
                        position = 0
                    }
                    else -> {
                        position /= 10
                        recyclerView.smoothScrollToPosition(position)
                    }
                }
            }
            handler?.postDelayed(this, 1000)
        }
    }

    init {
        handler = Handler()
    }

    fun runHandler() {
        removeScrollHandler()
        position = if (recyclerView.layoutManager is GridLayoutManager) {
            (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition()
                ?: 0
        } else {
            (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                ?: 0
        }
        when {
            position < 50 -> recyclerView.smoothScrollToPosition(0)
            else -> handler?.postDelayed(updater, 1000)
        }
    }

    private fun removeScrollHandler(){
        handler?.removeCallbacks(updater)
    }

    fun removeHandler() {
        handler?.removeCallbacks(updater)
        handler = null
    }
}