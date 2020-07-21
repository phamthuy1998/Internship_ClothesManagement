package com.ptithcm.ptshop.view.forgotpassword

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Observer
import com.ptithcm.core.param.ForgotPasswordParam
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.base.BaseFragment
import com.ptithcm.ptshop.constant.ERROR_CODE_404
import com.ptithcm.ptshop.databinding.FragmentForgotPasswordBinding
import com.ptithcm.ptshop.ext.*
import com.ptithcm.ptshop.view.MainActivity
import com.ptithcm.ptshop.view.home.StoryDetailActivity
import com.ptithcm.ptshop.viewmodel.AuthenticateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentForgotPassword : BaseFragment<FragmentForgotPasswordBinding>() {

    private val authViewModel: AuthenticateViewModel by viewModel()

    private var email = ""
    override val layoutId: Int
        get() = R.layout.fragment_forgot_password

    private fun setupToolbar() {
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(true, hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(getString(R.string.forgot_password_capital))
            when (this) {
                is MainActivity -> findViewById<AppCompatImageButton>(R.id.ivBack).setImageResource(
                    R.drawable.ic_back
                )
                is StoryDetailActivity -> findViewById<AppCompatImageButton>(R.id.ivBack).setImageResource(
                    R.drawable.ic_back
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString("email") ?: ""
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.edtEmail.setText(email)
        setupToolbar()
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        viewBinding.isValid = false
        viewBinding.edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                viewBinding.isValid = viewBinding.edtEmail.text.toString().trim().isValidEmail()
            }
        }

    }

    override fun bindViewModel() {
        super.bindViewModel()
        authViewModel.forgotPasswordLiveData.observe(this, Observer {
            navController.popBackStack()
        })
        authViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                Toast.makeText(requireContext(), it.first, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send -> {
                viewBinding.isValid = viewBinding.edtEmail.text.toString().trim().isValidEmail()
                if (isValidInput()) {
                    authViewModel.forgotPassword(ForgotPasswordParam(viewBinding.edtEmail.text.toString().trim()))
                }
            }
        }
    }

    private fun isValidInput(): Boolean {
        val email = viewBinding.edtEmail.text.toString().trim()
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
            !(email.isValidEmail()) -> {
                messageHandler?.runMessageErrorHandler(getString(R.string.invalid_email))
                false
            }
            else -> true
        }
    }
}