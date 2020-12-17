package com.n16dccn159.admin.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.n16dccn159.admin.R
import com.n16dccn159.admin.base.BaseActivity
import com.n16dccn159.admin.constant.PERMISSION_CAMERA
import com.n16dccn159.admin.constant.PERMISSION_GALLERY
import com.n16dccn159.admin.databinding.ActivityMainBinding
import com.n16dccn159.admin.ext.*
import com.n16dccn159.admin.view.uploadproduct.UploadProductActivity
import com.n16dccn159.admin.viewmodel.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var currentNavController: LiveData<NavController>? = null

    override val layoutId: Int = R.layout.activity_main
    private val viewModel: ProductFilterViewModel by viewModel()
    private val brandViewModel: BrandsViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    val shoppingViewModel: ShoppingViewModel by viewModel()
    val wishListViewModel: WishListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
        brandViewModel.getBrands()
        brandViewModel.getStories()
        viewModel.getProductFilter()
        homeViewModel.getAllTags()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun bindViewModel() {
        super.bindViewModel()

        brandViewModel.pagedList.observe(this, Observer {})
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        brandViewModel.brandsLiveData.observe(this, Observer { })
        brandViewModel.storesLiveData.observe(this, Observer {})
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.btnNav)

        val navGraphIds = listOf(
            R.navigation.nav_shop,
            R.navigation.nav_designer,
            R.navigation.nav_invoices,
            R.navigation.nav_message,
            R.navigation.nav_profile
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
//        controller.observe(this, Observer { navController ->
//            setupAction
//            BarWithNavController(navController)
//        })

        currentNavController = controller
        updateUIBottomNav()
    }

    fun updateUIBottomNav() {
        viewBinding.btnNav.disableLongClick()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CAMERA, PERMISSION_GALLERY -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermission()
                }
            }
        }
    }

    private fun checkPermission(){
        when {
            !hasCameraPermission() -> {
                requestCameraPermission(PERMISSION_CAMERA)
            }
            !hasReadStoragePermission() -> {
                requestReadAndWriteStoragePermission(PERMISSION_GALLERY)
            }
            else -> {
                startActivity<UploadProductActivity>()
            }
        }
    }
}
