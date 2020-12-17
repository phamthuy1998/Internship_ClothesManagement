package com.n16dccn159.admin.view.login

import android.graphics.Typeface
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.n16dccn159.admin.BuildConfig
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.base.BaseFragment
import com.n16dccn159.admin.constant.ERROR_CODE_404
import com.n16dccn159.admin.constant.FROM_SHOPPING_BAG
import com.n16dccn159.admin.databinding.FragmentLoginBinding
import com.n16dccn159.admin.ext.*
import com.n16dccn159.admin.view.MainActivity
import com.n16dccn159.admin.view.home.StoryDetailActivity
import com.n16dccn159.admin.viewmodel.AuthenticateViewModel
import com.n16dccn159.core.CoreApplication
import com.n16dccn159.core.param.LogInParam
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_login

    private var fromRegister = false
    private var fromShoppingBag = false
    private val authViewModel: AuthenticateViewModel by viewModel()
    private var typeface: Typeface? = null
    private var fromQuestionFragment = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fromQuestionFragment = arguments?.get("fromQuestionFragment") as Boolean? ?: false
        (requireActivity() as? BaseActivity<*>)?.apply {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            transparentStatusBar(isFull = false, isBlack = true)
        }

        val bundle = this.arguments
        if (bundle != null) {
            fromRegister = bundle.getBoolean("fromRegister", false)
            fromShoppingBag = bundle.getBoolean(FROM_SHOPPING_BAG, false)
        }
        observeViewModel()
        typeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_regular)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.btnNav?.gone()
        setupToolbar()
        if(BuildConfig.DEBUG){
            viewBinding.edtEmail.setText("anna1998@gmail.com")
            viewBinding.edtPassword.setText("thuythuy")
        }
    }


    private fun observeViewModel() {
        authViewModel.logInLiveData.observe(this, Observer {
            if (it != null && it.status == true) {

                it.data?.let { it1 -> CoreApplication.instance.saveAccount(it1) }
                startActivity<MainActivity>()
                requireActivity().finish()
//                  (activity as? MainActivity)?.updateUIBottomNav()
//                  navController.popBackStack()
//                  if (fromShoppingBag||fromQuestionFragment) {
//                      return@Observer
//                  }
//                  if (fromRegister)
//                      navController.popBackStack()
//                  activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//                  (activity as? MainActivity)?.apply {
//                      viewBinding.btnNav.selectedItemId = R.id.nav_profile
//                  }
            } else {
                messageHandler?.runMessageErrorHandler(it.message ?: "")
                Toast.makeText(requireContext(),it.message ?: "", Toast.LENGTH_LONG).show()
                viewBinding.btnSignIn.isLoading = false
            }
        })

        authViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? BaseActivity<*>)?.isShowErrorNetwork(true)
            } else {
                viewBinding.btnSignIn.isLoading = false
                messageHandler?.runMessageErrorHandler(it.first)
            }
        })
    }

    override fun bindEvent() {
        super.bindEvent()
        setBackPressEvent()
        viewBinding.fragment = this
        viewBinding.isShowPassword = false
        viewBinding.isValid = false
        viewBinding.edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewBinding.isValid = viewBinding.edtEmail.text.toString().trim().isValidEmail()
            }
        }

    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sign_in -> {
                if (isValidInput()) {
                    viewBinding.btnSignIn.isLoading = true
                    authViewModel.logIn(
                        LogInParam(
                            viewBinding.edtEmail.text.toString(),
                            viewBinding.edtPassword.text.toString()
                        )
                    )
                }
            }
            R.id.toggle_password -> {
                viewBinding.isShowPassword = !viewBinding.isShowPassword!!
                viewBinding.edtPassword.togglePassword(!viewBinding.isShowPassword!!, typeface)
            }
            R.id.btn_forgot_pass -> {
                navController.navigate(
                    R.id.fragmentForgotPassword,
                    bundleOf("email" to viewBinding.edtEmail.text.toString())
                )
            }
        }
    }


    private fun setBackPressEvent() {
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
            false
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.gone()
        }
        (requireActivity() as? StoryDetailActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.gone()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.visible()
        }
        (requireActivity() as? StoryDetailActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.visible()
        }
    }

    private fun isValidInput(): Boolean {
        val email = viewBinding.edtEmail.text.toString().trim()
        val password = viewBinding.edtPassword.text.toString().trim()
        return when {
            (email.isEmpty()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty, getString(
                            R.string.email
                        )
                    )
                )
                false
            }
            (password.isEmpty()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty, getString(
                            R.string.password
                        )
                    )
                )
                false
            }
            !(email.isValidEmail()) -> {
                messageHandler?.runMessageErrorHandler(getString(R.string.invalid_email))
                false
            }
            else -> true
        }
    }
}