package com.sg.snapshop.view.mydetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.sg.core.CoreApplication
import com.sg.core.param.EditAccountParam
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.databinding.FragmentMyDetailBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.isShowErrorNetwork
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.util.ShowMessageHandler
import com.sg.snapshop.util.checkPhonevalid
import com.sg.snapshop.util.checkValidEmail
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyDetailFragment : BaseFragment<FragmentMyDetailBinding>() {
    private val userViewModel: UserViewModel by viewModel()
    private var messageQueue: ShowMessageHandler? = null
    override val layoutId: Int
        get() = R.layout.fragment_my_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.btnNav?.visibility = View.GONE
        observeViewModel()
        setupToolbar()

    }

    private fun observeViewModel() {
        userViewModel.updateDetailLiveData.observe(this, Observer {
            changeStatusButton(false)
            if (it != null && it.status == true) {
                CoreApplication.instance.account?.apply {
                    name = viewBinding.edtName.text.toString()
                    phone = viewBinding.edtPhone.text.toString()
                    email = viewBinding.edtEmail.text.toString()
                }
                messageQueue?.runMessageHandler(getString(R.string.update_detail_success))
            } else {
                changeStatusButton(false)
                messageQueue?.runMessageErrorHandler(it.message?:"")
            }
        })
        userViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                changeStatusButton(false)
                messageQueue?.runMessageErrorHandler(it.first)
            }
        })
    }

    override fun bindEvent() {
        super.bindEvent()
        messageQueue = ShowMessageHandler(requireActivity())
        viewBinding.account = CoreApplication.instance.account
        viewBinding.fragment = this
    }

    override fun onDestroyView() {
        messageQueue?.removeHandler()
        super.onDestroyView()
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_update -> {
                changeStatusButton(true)
                if (checkValidUpdate()) {
                    val account = CoreApplication.instance.account
                    val param = EditAccountParam(
                        userid = account?.id,
                        name = viewBinding.edtName.text.toString(),
                        phone = viewBinding.edtPhone.text.toString(),
                        email = edt_email.text.toString()
                    )
                    userViewModel.updateProfile(param)
                } else {
                    changeStatusButton(false)
                }
            }
        }
    }

    private fun checkValidUpdate(): Boolean {
        return when {
            !checkValidEmail(viewBinding.edtEmail.text.toString()) -> {
                messageQueue?.runMessageErrorHandler(getString(R.string.invalid_email))
                false
            } !checkPhonevalid(viewBinding.edtPhone.text.toString())-> {
                messageQueue?.runMessageErrorHandler(getString(R.string.invalid_phone))
                false
            }
            !checkEmpty(viewBinding.edtName) -> {
                messageQueue?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        getString(R.string.name)
                    )
                )
                false
            }
            !checkEmpty(viewBinding.edtPhone) -> {
                messageQueue?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        getString(R.string.phone)
                    )
                )
                false
            }
            !checkEmpty(viewBinding.edtEmail) -> {
                messageQueue?.runMessageErrorHandler(
                    getString(
                        R.string.error_empty,
                        getString(R.string.email)
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

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            initToolBar(
                viewBinding.layoutToolbar.toolbar, true,
                hasBackRight = false,
                hasLeft = false,
                hasRight = false
            )
            viewBinding.layoutToolbar.ivBack.setImageResource(R.drawable.ic_back)
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.my_details))
        }
    }

    private fun changeStatusButton(isLoading: Boolean) {
        viewBinding.btnUpdate.isLoading = isLoading

    }

}