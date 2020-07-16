package com.sg.snapshop.view.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.services.s3.AmazonS3Client
import com.bumptech.glide.Glide
import com.sg.core.BuildConfig
import com.sg.core.CoreApplication
import com.sg.core.model.Account
import com.sg.core.model.Carousel
import com.sg.core.model.Profile
import com.sg.core.model.TypeCarousel
import com.sg.core.param.UpdateDetailParam
import com.sg.core.util.ObjectHandler
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.*
import com.sg.snapshop.databinding.FragmentProfileBinding
import com.sg.snapshop.databinding.LayoutBottomSheetBinding
import com.sg.snapshop.databinding.LayoutPopUpBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.util.FileUtil
import com.sg.snapshop.util.PopUp
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.viewmodel.AuthenticateViewModel
import com.sg.snapshop.viewmodel.ShoppingViewModel
import com.sg.snapshop.viewmodel.UserViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import pub.devrel.easypermissions.AfterPermissionGranted
import java.io.File

class ProfileFragment : BaseFragment<FragmentProfileBinding>(), View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_profile

    private val authViewModel: AuthenticateViewModel by viewModel()
    private val userViewModel: UserViewModel by viewModel()
    private var captureFile: File? = null
    private val transferUtility: TransferUtility by inject()
    private val s3Client: AmazonS3Client by inject()
    private var photoStoragePath: String? = null
    private var photoType: PhotoType? = null
    private var currentProfile: Profile? = null
    private var currentAccount: Account? = null
    private val shoppingViewModel: ShoppingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (CoreApplication.instance.profile != null) {
            userViewModel.getProfile()
        }
        observeViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
            isShowLoading(false)
        }
        //move to brand profile
        if (currentProfile?.user?.brand != null) {
            (requireActivity() as? MainActivity)?.apply {
                viewBinding.layoutToolbar.toolbar.gone()
            }
            val typeCarousel=if(currentProfile?.user?.brand?.stores.isNullOrEmpty()) TypeCarousel.STORE else TypeCarousel.BRAND
            navController.navigate(
                R.id.brandProfileFragment,
                bundleOf(
                    KEY_ARGUMENT to Carousel(
                        brand_id = currentProfile?.user?.brand?.id,
                        gender = -1,
                        type = typeCarousel.value
                    )
                )
            )
        } else {
            val glideApp = Glide.with(context!!)
            glideApp.load(currentProfile?.user?.cover)
                .placeholder(R.color.white)
                .centerCrop()
                .error(R.drawable.bg_login)
                .into(viewBinding.profileCustomer.cover)

            glideApp.load(currentProfile?.user?.photo)
                .placeholder(R.color.white)
                .error(R.drawable.bg_login)
                .into(viewBinding.profileCustomer.avatar)
        }
        setupToolbar()
    }

    override fun bindEvent() {
        currentAccount = CoreApplication.instance.account
        viewBinding.fragment = this
        viewBinding.isLogin = currentAccount != null
        viewBinding.account = currentAccount
        if (ObjectHandler.isLogin()){
            shoppingViewModel.updateBasketFromLocal()
        }
    }

    private fun observeViewModel() {
        authViewModel.logOutLiveData.observe(this, Observer {
            if (it != null) {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                CoreApplication.instance.clearAccount()
                CoreApplication.instance.clearBasket()
                viewBinding.isLogin = false
                viewBinding.isBrand = false
                setupToolbar()
                (activity as? MainActivity)?.updateUIBottomNav()
            }

        })
        userViewModel.getProfileLiveData.observe(this, Observer {
            CoreApplication.instance.profile?.user=it
            currentProfile=CoreApplication.instance.profile
            if (currentProfile?.user?.brand != null) {
                (requireActivity() as? MainActivity)?.apply {
                    viewBinding.layoutToolbar.toolbar.gone()
                }
                val typeCarousel=if(currentProfile?.user?.brand?.stores.isNullOrEmpty()) TypeCarousel.STORE else TypeCarousel.BRAND
                navController.navigate(
                    R.id.brandProfileFragment,
                    bundleOf(
                        KEY_ARGUMENT to Carousel(
                            brand_id = currentProfile?.user?.brand?.id,
                            gender = -1,
                            type = typeCarousel.value
                        )
                    )
                )
            }
            userViewModel.getProfileLiveData.removeObservers(this)
        })
        userViewModel.updateDetailLiveData.observe(this, Observer {
            CoreApplication.instance.saveUser(currentProfile ?: return@Observer)
        })
        userViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404){
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                messageHandler?.runMessageErrorHandler(it.first)
            }
        })
        authViewModel.error.observe(activity!!, Observer {
            if (it.second == ERROR_CODE_404){
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
            } else {
                Toast.makeText(requireContext(), it.first, Toast.LENGTH_SHORT).show()
            }
        })

        shoppingViewModel.updateResult.observe(this, Observer {
            setupToolbar()
        })
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_btn -> {
                navController.navigate(R.id.loginFragment)
            }
            R.id.register_btn -> {
                navController.navigate(R.id.registerFragment)
            }
            R.id.log_out_btn -> {
                (requireActivity() as? BaseActivity<*>)?.showPopup(
                    PopUp(
                        R.layout.layout_pop_up,
                        messageQueue = this::popEvent
                    )
                )
            }
            R.id.details_btn -> {
                navController.navigate(R.id.myDetailFragment)
            }
            R.id.change_pass_btn -> {
                navController.navigate(R.id.changePasswordFragment)
            }
            R.id.customer_service_btn -> {
                navController.navigate(R.id.documentFragment, bundleOf(KEY_ARGUMENT to resources.getString(R.string.customer_service)))
            }
            R.id.delivery_return_btn -> {
                navController.navigate(R.id.documentFragment, bundleOf(KEY_ARGUMENT to resources.getString(R.string.delivery_and_returns)))
            }
            R.id.about_us_btn -> {
                navController.navigate(R.id.documentFragment, bundleOf(KEY_ARGUMENT to resources.getString(R.string.about_us)))
            }
            R.id.terms_btn -> {
                navController.navigate(R.id.documentFragment, bundleOf(KEY_ARGUMENT to resources.getString(R.string.terms_and_conditions)))
            }
            R.id.address_book_btn -> {
                navController.navigate(R.id.nav_book_address)
            }
            R.id.btnCancel, R.id.tvCancel -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
            }
            R.id.btnOk -> {
                authViewModel.logOut()
            }
            R.id.payment_method_btn -> {
                navController.navigate(R.id.nav_payment_methods)
            }
            R.id.avatar -> {
                photoType = PhotoType.PROFILE_PHOTO
                showBottomSheet()
            }
            R.id.cover -> {
                photoType = PhotoType.COVER_PHOTO
                showBottomSheet()
            }
            R.id.tvOption1 -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                openCamera()
            }
            R.id.tvOption2 -> {
                (requireActivity() as? BaseActivity<*>)?.closePopup()
                openGallery()
            }
        }
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.visible()
            initToolBar(
                viewBinding.layoutToolbar.toolbar,
                false,
                hasBackRight = false
            )
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.my_account))
            viewBinding.layoutToolbar.ivLeft.setOnClickListener {
                navController.navigate(R.id.nav_search)
            }
            viewBinding.layoutToolbar.ivRight.setImageResource(R.drawable.ic_shopping_bag)
            viewBinding.layoutToolbar.ivRight.setOnClickListener {
                navController.navigateAnimation(R.id.nav_shopping_card, isBotToTop = true)
            }
        }
        if (currentProfile?.user?.brand != null) {
            (requireActivity() as? MainActivity)?.apply {
                viewBinding.layoutToolbar.toolbar.gone()
            }
        }
    }

    private fun popEvent(popupBinding: ViewDataBinding?) {
        (popupBinding as? LayoutPopUpBinding)?.apply {
            title = getString(R.string.continue_logout)
            left = getString(R.string.cancel)
            right = getString(R.string.logout_me_out)
            btnCancel.setOnClickListener(this@ProfileFragment)
            btnOk.setOnClickListener(this@ProfileFragment)
        }
    }

    @AfterPermissionGranted(PERMISSION_GALLERY)
    private fun openGallery() {
        if (context?.hasReadStoragePermission() == false) {
            requestReadAndWriteStoragePermission(PERMISSION_GALLERY)
        } else {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST)
        }
    }

    @AfterPermissionGranted(PERMISSION_CAMERA)
    private fun openCamera() {
        if (context?.hasCameraPermission() == false)
            requestCameraPermission(PERMISSION_CAMERA)
        else {
            captureFile = FileUtil().createFile(context ?: return)
            val capturedImgUri = FileProvider.getUriForFile(
                context ?: return,
                getString(R.string.file_provider_authority),
                captureFile ?: return
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImgUri)
            startActivityForResult(intent, CAMERA_REQUEST)
        }
    }

    private fun showBottomSheet() {
        (requireActivity() as? BaseActivity<*>)?.showPopup(
            PopUp(
                R.layout.layout_bottom_sheet,
                isBottomSheet = true,
                isCancelable = true,
                messageQueue = { binding: ViewDataBinding? ->
                    (binding as? LayoutBottomSheetBinding)?.apply {
                        title =
                            if (photoType == PhotoType.PROFILE_PHOTO) getString(R.string.update_avatar)
                            else getString(R.string.update_cover)
                        option1 = getString(R.string.take_photo)
                        option2 = getString(R.string.choose_photo)
                        this.tvCancel.setOnClickListener(this@ProfileFragment)
                        this.tvOption1.setOnClickListener(this@ProfileFragment)
                        this.tvOption2.setOnClickListener(this@ProfileFragment)
                    }
                }
            )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST -> {
                    data?.data?.let {
                        toCropImage(it)
                    }
                }
                CAMERA_REQUEST -> {
                    captureFile?.let {
                        when (photoType) {
                            PhotoType.PROFILE_PHOTO -> {
                                Glide.with(requireContext())
                                    .load(FileUtil().getUriFromFile(requireContext(), it))
                                    .placeholder(R.color.white)
                                    .into(viewBinding.profileCustomer.avatar)
                            }
                            PhotoType.COVER_PHOTO -> {
                                Glide.with(requireContext())
                                    .load(FileUtil().getUriFromFile(requireContext(), it))
                                    .placeholder(R.color.white)
                                    .centerCrop()
                                    .into(viewBinding.profileCustomer.cover)
                            }
                        }
                        uploadFileToS3(it)
                    }
                }
                CROP_IMAGE_REQUEST -> {
                    val file = data?.getSerializableExtra(KEY_FILE) as? File
                    file?.let {
                        when (photoType) {
                            PhotoType.PROFILE_PHOTO -> {
                                Glide.with(requireContext())
                                    .load(FileUtil().getUriFromFile(requireContext(), it))
                                    .placeholder(R.color.white)
                                    .into(viewBinding.profileCustomer.avatar)
                            }
                            PhotoType.COVER_PHOTO -> {
                                Glide.with(requireContext())
                                    .load(FileUtil().getUriFromFile(requireContext(), it))
                                    .placeholder(R.color.white)
                                    .centerCrop()
                                    .into(viewBinding.profileCustomer.cover)
                            }
                        }
                        uploadFileToS3(it)
                    }
                }
            }
        }
    }

    private fun toCropImage(uri: Uri) {
        val intent = Intent(activity, ProfileCropImageActivity::class.java)
        intent.putExtra(KEY_ARGUMENT_OBJECT, uri)
        startActivityForResult(intent, CROP_IMAGE_REQUEST)
    }

    private fun uploadFileToS3(file: File) {
        photoStoragePath = "$PROFILE_PHOTO_STORAGE${System.currentTimeMillis()}"
        val transferObserver = transferUtility.upload(
            BuildConfig.AWS_BUCKET,
            photoStoragePath,
            file
        )
        transferObserverListener(transferObserver)
    }

    private fun transferObserverListener(transferObserver: TransferObserver) {
        transferObserver.setTransferListener(object : TransferListener {
            override fun onStateChanged(id: Int, state: TransferState) {
                if (state == TransferState.COMPLETED) {
                    val url = s3Client.getUrl(BuildConfig.AWS_BUCKET, photoStoragePath)
                    when (photoType) {
                        PhotoType.PROFILE_PHOTO -> {
                            currentProfile?.user?.photo = "$url"
                        }
                        PhotoType.COVER_PHOTO -> {
                            currentProfile?.user?.cover = "$url"
                        }
                    }
                    updateProfile()
                }
            }

            override fun onProgressChanged(id: Int, bytesCurrent: Long, bytesTotal: Long) {}

            override fun onError(id: Int, ex: Exception) {
                ex.printStackTrace()
            }
        })
    }

    private fun updateProfile() {
        val param = UpdateDetailParam(
            first_name = currentProfile?.user?.first_name ?: "",
            last_name = currentProfile?.user?.last_name ?: "",
            email = currentProfile?.user?.email ?: "",
            cover = currentProfile?.user?.cover ?: "",
            photo = currentProfile?.user?.photo ?: ""
        )
//        userViewModel.updateProfile(param)
    }
}