package com.sg.snapshop.view.changepassword

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.sg.core.CoreApplication
import com.sg.core.param.ChangePassParam
import com.sg.core.util.capitalize
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.databinding.FragmentChangePasswordBinding
import com.sg.snapshop.databinding.LayoutPopUpBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.isShowErrorNetwork
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.ext.togglePassword
import com.sg.snapshop.util.PopUp
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {
    private val userViewModel: UserViewModel by viewModel()
    private var typeface: Typeface? = null
    override val layoutId: Int
        get() = R.layout.fragment_change_password

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        observeViewModel()
        typeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_regular)
        setupToolbar()

    }

    private fun observeViewModel() {
        userViewModel.changePasswordLiveData.observe(this, Observer {
            if (it != null && it.status == true) {
                changeStatusButton(false)
                messageHandler?.runMessageHandler(getString(R.string.changePass_success))
                (requireActivity() as? BaseActivity<*>)?.showPopup(
                    PopUp(
                        R.layout.layout_pop_up,
                        messageQueue = this::popEvent
                    )
                )
            } else {
                messageHandler?.runMessageErrorHandler(it.message ?: "")
                viewBinding.btnChange.isLoading = false
            }
        })
        userViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                changeStatusButton(false)
                messageHandler?.runMessageErrorHandler(it.first)
            }
        })
    }

    private fun popEvent(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutPopUpBinding)?.apply {
            title = getString(R.string.changePass_success)
            left = getString(R.string.ok)
            right = getString(R.string.cancel)
            btnOk.visibility = View.GONE
            btnCancel.setOnClickListener{
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                navController.popBackStack()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        viewBinding.isShow1 = false
        viewBinding.isShow2 = false
        viewBinding.isShow3 = false
        viewBinding.container.setOnTouchListener { _, event ->
            if (event != null && event.action == MotionEvent.ACTION_MOVE) {
                hideKeyboard()
            }
            false
        }
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_change -> {
                changeStatusButton(true)
                if (checkValidUpdate()) {
                    userViewModel.changePassword(
                        ChangePassParam(
                            userid = CoreApplication.instance.account?.id,
                            oldpass = viewBinding.edtOldPassword.text.toString(),
                            newpassword = viewBinding.edtNewPassword.text.toString()
                        )
                    )
                } else {
                    changeStatusButton(false)
                }
            }
            R.id.toggle_password -> {
                viewBinding.isShow1 = !viewBinding.isShow1!!
                viewBinding.edtOldPassword.togglePassword(!viewBinding.isShow1!!, typeface)
            }
            R.id.toggle_new_password -> {
                viewBinding.isShow2 = !viewBinding.isShow2!!
                viewBinding.edtNewPassword.togglePassword(!viewBinding.isShow2!!, typeface)
            }
            R.id.toggle_confirm_password -> {
                viewBinding.isShow3 = !viewBinding.isShow3!!
                viewBinding.edtConfirmPassword.togglePassword(!viewBinding.isShow3!!, typeface)
            }
        }
    }

    private fun checkValidUpdate(): Boolean {
        return when {
            !checkEmpty(viewBinding.edtOldPassword) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        "Old password"
                    )
                )
                false
            }
            !checkEmpty(viewBinding.edtNewPassword) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        "New password"
                    )
                )
                false
            }
            !checkEmpty(viewBinding.edtConfirmPassword) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        "Confirm new password"
                    )
                )
                false
            }
            !checkSizePassword(viewBinding.edtNewPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_size,
                        "New Password"
                    )
                )
                false
            }
            !checkUpperCaseLetter(viewBinding.edtNewPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_upper_case,
                        "New Password"
                    )
                )
                false
            }
            !checkDigit(viewBinding.edtNewPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_digit,
                        "New Password"
                    )
                )
                false
            }
            !checkSizePassword(viewBinding.edtConfirmPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_size,
                        "Confirm New Password"
                    )
                )
                false
            }
            !checkUpperCaseLetter(viewBinding.edtConfirmPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_upper_case,
                        "Confirm New Password"
                    )
                )
                false
            }
            !checkDigit(viewBinding.edtConfirmPassword.text.toString()) -> {
                messageHandler?.runMessageErrorHandler(
                    getString(
                        R.string.password_digit,
                        "Confirm New Password"
                    )
                )
                false
            }
            viewBinding.edtConfirmPassword.text.toString() != viewBinding.edtNewPassword.text.toString() -> {
                messageHandler?.runMessageErrorHandler(getString(R.string.new_password_not_match))
                false
            }
            else -> true

        }

    }

    private fun checkSizePassword(password: String?) = password?.length ?: 0 >= 8

    private fun checkUpperCaseLetter(password: String?) =
        Regex("^(?=.*[A-Z])").find(password ?: "") != null

    private fun checkDigit(password: String?) = Regex("^(?=.*\\d)").find(password ?: "") != null
    private fun checkEmpty(view: TextInputEditText): Boolean {
        if (view.text.toString().isBlank()) {
            return false
        }
        return true
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar, true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            setupToolbar(
                viewBinding.layoutToolbar.toolbar,
                getString(R.string.change_password).capitalize()
            )
        }

    }

    private fun changeStatusButton(isLoading: Boolean) {
        viewBinding.btnChange.isLoading = isLoading

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