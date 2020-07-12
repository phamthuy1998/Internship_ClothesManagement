package com.sg.snapshop.view.mydetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import com.sg.core.CoreApplication
import com.sg.core.param.UpdateDetailParam
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.databinding.FragmentMyDetailBinding
import com.sg.snapshop.ext.initToolBar
import com.sg.snapshop.ext.isShowErrorNetwork
import com.sg.snapshop.ext.setupToolbar
import com.sg.snapshop.util.ShowMessageHandler
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyDetailFragment : BaseFragment<FragmentMyDetailBinding>() {
    private val userViewModel: UserViewModel by viewModel()
    private var messageQueue : ShowMessageHandler? = null
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
            val profile = CoreApplication.instance.profile
            profile?.user?.apply {
                first_name = edt_firstName.text.toString()
                last_name = edt_lastName.text.toString()
                email = edt_email.text.toString()
            }
            if (profile != null) {
                CoreApplication.instance.saveUser(profile)
            }
            messageQueue?.runMessageHandler(getString(R.string.update_detail_success))
        })
        userViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404){
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
        viewBinding.profile = CoreApplication.instance.profile
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
                    val profile = CoreApplication.instance.profile
                    val param = UpdateDetailParam(
                        first_name = edt_firstName.text.toString(),
                        last_name = edt_lastName.text.toString(),
                        email = edt_email.text.toString(),
                        cover = profile?.user?.cover,
                        photo = profile?.user?.photo
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
            !checkValidEmail(viewBinding.edtEmail.text.toString())!! -> {
                messageQueue?.runMessageErrorHandler("Invalid email")
                false
            }
            !checkEmpty(viewBinding.edtFirstName) -> {
                messageQueue?.runMessageErrorHandler("First name can't be empty")
                false
            }
            !checkEmpty(viewBinding.edtLastName) -> {
                messageQueue?.runMessageErrorHandler("Last name can't be empty")
                false
            }
            else -> true
        }
    }

    private fun checkValidEmail(email: String): Boolean? {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
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