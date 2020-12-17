package com.n16dccn159.admin.util

import android.app.Activity
import android.os.Handler
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.n16dccn159.admin.R
import com.n16dccn159.admin.ext.gone
import com.n16dccn159.admin.ext.visible
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.home.StoryDetailActivity

class ShowMessageHandler(activity : Activity) {

    private var handler: Handler? = null
    private var viewMessage: View? = null
    private var viewError : View? = null
    private val updater = object : Runnable {
        override fun run() {
            viewMessage?.gone()
            handler?.postDelayed(this, 1000)
        }
    }

    private val updaterError = object : Runnable {
        override fun run() {
            viewError?.gone()
            handler?.postDelayed(this, 3000)
        }
    }


    init {
        handler = Handler()
        when(activity){
            is MainActivity -> {
                viewMessage = activity.viewBinding.layoutToastSuccess.root
                viewError = activity.viewBinding.layoutToast.root
            }
            is StoryDetailActivity -> {
                viewMessage = activity.viewBinding.layoutToastSuccess.root
                viewError = activity.viewBinding.layoutToast.root
            }
        }
    }

    fun runMessageHandler(text : String){
        removeMessageHandler()
        viewMessage.apply {
            this?.findViewById<AppCompatTextView>(R.id.tvToast)?.text = text
            this?.visible()
        }
        handler?.postDelayed(updater, 1000)
    }

    fun runMessageErrorHandler(text : String){
        removeMessageHandler()
        viewError.apply {
            this?.findViewById<AppCompatTextView>(R.id.tvToast)?.text = text
            this?.visible()
        }
        handler?.postDelayed(updaterError, 3000)
    }

     private fun removeMessageHandler(){
         viewMessage?.gone()
         viewError?.gone()
        handler?.removeCallbacks(updater)
        handler?.removeCallbacks(updaterError)
    }

    fun removeHandler(){
        viewMessage?.gone()
        viewError?.gone()
        handler?.removeCallbacks(updater)
        handler?.removeCallbacks(updaterError)
        handler = null
    }
}