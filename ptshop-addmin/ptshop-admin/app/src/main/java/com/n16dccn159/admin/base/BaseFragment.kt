package com.n16dccn159.admin.base

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.n16dccn159.admin.ext.isShowErrorNetwork
import com.n16dccn159.admin.util.ShowMessageHandler
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.core.CoreApplication

abstract class BaseFragment<ViewBinding : ViewDataBinding> : Fragment() {

    lateinit var viewBinding: ViewBinding
    lateinit var navController: NavController
    var messageHandler : ShowMessageHandler? = null

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModelOnce()
    }

    override fun onResume() {
        super.onResume()
        Log.d("AnnaFragment", this.javaClass.simpleName)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("anna", this.javaClass.name)
        navController = Navigation.findNavController(view)
        messageHandler = ShowMessageHandler(requireActivity())
        viewBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }

        bindEvent()
        bindViewModel()
    }

    open fun bindViewModel(){}
    open fun bindEvent(){}
    open fun bindViewModelOnce(){}

    override fun onDestroyView() {
        super.onDestroyView()
        messageHandler?.removeHandler()
        if (CoreApplication.instance.isNetworkConnected()) {
            (requireActivity() as? MainActivity)?.isShowErrorNetwork(false)
        }
        val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    fun showErrorNetwork() {
        (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
    }


}