package com.ptithcm.ptshop.view.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ptithcm.core.model.Stories
import com.ptithcm.ptshop.R
import com.ptithcm.ptshop.base.BaseActivity
import com.ptithcm.ptshop.constant.KEY_ARGUMENT
import com.ptithcm.ptshop.constant.KEY_ID
import com.ptithcm.ptshop.constant.KEY_TYPE
import com.ptithcm.ptshop.databinding.FragmentStoryDetailBinding
import com.ptithcm.ptshop.viewmodel.BrandsViewModel
import com.ptithcm.ptshop.viewmodel.ProductFilterViewModel
import com.ptithcm.ptshop.viewmodel.ShoppingViewModel
import com.ptithcm.ptshop.viewmodel.WishListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoryDetailActivity : BaseActivity<FragmentStoryDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_story_detail

    private lateinit var navController: NavController
    private val shoppingViewModel: ShoppingViewModel by viewModel()
    private val wishListViewModel: WishListViewModel by viewModel()
    private val viewModel: ProductFilterViewModel by viewModel()
    private val brandViewModel: BrandsViewModel by viewModel()

    var brandId: Int? = null
    var brandType: String? = ""
    var storyObj: Stories? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.nav_host)
        intent?.apply {
            brandId = getIntExtra(KEY_ID, -1)
            brandType = getStringExtra(KEY_TYPE)
            storyObj = getParcelableExtra(KEY_ARGUMENT)
        }
        brandViewModel.getBrands()
        brandViewModel.getStories()
        viewModel.getProductFilter()
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()


    override fun bindViewModel() {
        brandViewModel.brandsLiveData.observe(this, Observer { })
        brandViewModel.storesLiveData.observe(this, Observer {})
    }

    override fun onBackPressed() {
        if (!navController.popBackStack()){
            super.onBackPressed()
        }
    }
}