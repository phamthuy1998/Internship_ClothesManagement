package com.sg.snapshop.view.register

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.sg.core.CoreApplication
import com.sg.core.param.RegisterParam
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.constant.FROM_SHOPPING_BAG
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.databinding.FragmentRegisterBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.viewmodel.AuthenticateViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onFocusChange
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_register
    private var fromLogin = false
    private var fromShoppingBag = false
    private val authViewModel: AuthenticateViewModel by viewModel()
    private var typeface: Typeface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            fromLogin = bundle.getBoolean("fromLogin", false)
            fromShoppingBag = bundle.getBoolean(FROM_SHOPPING_BAG, false)
        }
        activity?.btnNav?.visibility = View.GONE
        typeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_regular)
        observeViewModel()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        viewBinding.tvHaveAccount.setOnClickListener {
            if (!fromLogin) {
                val bundle = Bundle()
                bundle.putBoolean("fromRegister", true)
                navController.navigate(R.id.loginFragment, bundle)

            } else {
                navController.popBackStack()
            }
        }

        viewBinding.btnTermsCons.makeLinks(
            Pair("Terms and Conditions", View.OnClickListener {
                navController.navigate(R.id.documentFragment, bundleOf(KEY_ARGUMENT to resources.getString(R.string.terms_and_conditions)))
            })
        )

    }


    private fun observeViewModel() {
        authViewModel.signUpLiveData.observe(this, Observer {
            if (it != null) {
                CoreApplication.instance.saveUser(it)
                messageHandler?.runMessageHandler(getString(R.string.sign_up_success))
                viewBinding.btnRegister.isLoading = false
                Handler().postDelayed({
                    finishRegister()
                }, 1000)
            }
        })

        authViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageErrorHandler(it.first)
                viewBinding.btnRegister.isLoading = false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindEvent() {
        super.bindEvent()
        setBackPressEvent()
        viewBinding.fragment = this
        viewBinding.isShowPassword = false
        viewBinding.isValid = false


        viewBinding.edtFirstName.onFocusChange { v, _ ->
            viewBinding.isFirstNameValid = checkEmpty(v as TextInputEditText)
        }

        viewBinding.edtLastName.onFocusChange { v, _ ->
            viewBinding.isLastNameValid = checkEmpty(v as TextInputEditText)
        }

        viewBinding.edtEmail.onFocusChange { _, _ ->
            viewBinding.isValid = checkValidEmail(viewBinding.edtEmail.text.toString())
        }

        viewBinding.edtPassword.onFocusChange { _, _ ->
            viewBinding.isPasswordValid = isValidPassword(viewBinding.edtPassword.text.toString())
        }
        viewBinding.scrollView.setOnTouchListener { _, event ->
            if (event != null && event.action == MotionEvent.ACTION_MOVE) {
                hideKeyboard()
            }
            false
        }
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_register -> {
                if (checkValidUpdate()) {
                    viewBinding.btnRegister.isLoading = true
                    authViewModel.signUp(
                        RegisterParam(
                            viewBinding.edtFirstName.text.toString(),
                            viewBinding.edtLastName.text.toString(),
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
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? BaseActivity<*>)?.apply {
            initToolbar(hasBackRight = false, hasLeft = false, hasRight = false)
            setupToolbar(getString(R.string.join_snapshop), false,
                messageQueue = {
                    navController.popBackStack()
                    if (fromLogin)
                        navController.popBackStack()
                })
            when(this){
                is MainActivity -> viewBinding.layoutToolbar.ivBack.setImageResource(R.drawable.ic_black_close)
                is StoryDetailActivity -> viewBinding.layoutToolbar.ivBack.setImageResource(R.drawable.ic_black_close)
            }
        }
    }

    private fun finishRegister() {
        navController.popBackStack()
        if (fromLogin)
            navController.popBackStack()
        if (fromShoppingBag)
            return
        (activity as? MainActivity)?.apply {
            viewBinding.btnNav.selectedItemId = R.id.nav_profile
        }
    }

    private fun checkValidUpdate(): Boolean {
        return when {
            !checkEmpty(viewBinding.edtFirstName) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        getString(R.string.first_name)
                    )
                )
                false
            }
            !checkEmpty(viewBinding.edtLastName) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        getString(R.string.last_name)
                    )
                )
                false
            }
            !checkEmpty(viewBinding.edtEmail) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        getString(R.string.email)
                    )
                )
                false
            }
            !checkEmpty(viewBinding.edtPassword) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        getString(R.string.password)
                    )
                )
                false
            }
            !checkValidEmail(viewBinding.edtEmail.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(getString(R.string.invalid_email))
                false
            }
            !checkSizePassword(viewBinding.edtPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_size,
                        "Password"
                    )
                )
                false
            }
            !checkUpperCaseLetter(viewBinding.edtPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_upper_case,
                        "Password"
                    )
                )
                false
            }
            !checkDigit(viewBinding.edtPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_digit,
                        "Password"
                    )
                )
                false
            }
            else -> true
        }
    }

    private fun checkEmpty(view: TextInputEditText): Boolean {
        if (view.text.toString().isBlank()) {
            return false
        }
        return true
    }

    private fun checkSizePassword(password: String?) = password?.length ?: 0 >= 8

    private fun checkUpperCaseLetter(password: String?) =
        Regex("^(?=.*[A-Z])").find(password ?: "") != null

    private fun checkDigit(password: String?) = Regex("^(?=.*\\d)").find(password ?: "") != null

    private fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }

    private fun checkValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    private fun setBackPressEvent() {
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (!fromLogin) activity?.btnNav?.visibility = View.VISIBLE
            }
            false
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireActivity().getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken, 0
        )
    }

}