package com.sg.snapshop.view.home

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.sg.core.model.Stories
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.constant.KEY_ARGUMENT
import com.sg.snapshop.constant.KEY_ID
import com.sg.snapshop.constant.KEY_TYPE
import com.sg.snapshop.databinding.FragmentStoryDetailBinding
import com.sg.snapshop.ext.isShowErrorNetwork
import com.sg.snapshop.viewmodel.BrandsViewModel
import com.sg.snapshop.viewmodel.ProductFilterViewModel
import com.sg.snapshop.viewmodel.ShoppingViewModel
import com.sg.snapshop.viewmodel.WishListViewModel
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
        shoppingViewModel.cardResult.observe(this, Observer {  })
        wishListViewModel.wishListResult.observe(this, Observer { })
        brandViewModel.brandsLiveData.observe(this, Observer { })
        brandViewModel.storesLiveData.observe(this, Observer {})
    }

    override fun onBackPressed() {
        if (!navController.popBackStack()){
            super.onBackPressed()
        }
    }
}