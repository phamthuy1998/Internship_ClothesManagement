package com.sg.snapshop.view.shop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.sg.core.CoreApplication
import com.sg.core.model.SupportedCurrency
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.ERROR_CODE_404
import com.sg.snapshop.databinding.FragmentShopBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.shop.adapter.CurrencySpinnerAdapter
import com.sg.snapshop.view.shop.adapter.ShopViewPagerAdapter
import com.sg.snapshop.viewmodel.CarouselViewModel
import com.sg.snapshop.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopFragment : BaseFragment<FragmentShopBinding>(), AdapterView.OnItemSelectedListener {

    override val layoutId: Int = R.layout.fragment_shop

    private val viewModel: CurrencyViewModel by sharedViewModel(from = { requireActivity() })
    private val carouselViewModel: CarouselViewModel by viewModel()
    private lateinit var adapter: ShopViewPagerAdapter
    private lateinit var currencyAdapter: CurrencySpinnerAdapter
    private lateinit var currencies: ArrayList<SupportedCurrency>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carouselViewModel.getCarousels()
    }

    override fun bindEvent() {
        super.bindEvent()
        setupToolbar()
        initViewPager()
        initCurrencyAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.currencyLiveData.observe(this, Observer {
            currencies = it
            currencyAdapter.setCurrencies(currencies)
            eventCurrency(getPosition())
        })

        carouselViewModel.carouselLiveData.observe(this, Observer {})
        carouselViewModel.errorCarousel.observe(this, Observer {
            (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
        })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (CoreApplication.instance.currency.code?.equals(currencies[position].code) == false){
            CoreApplication.instance.saveCurrency(currencies[position])
            eventCurrency(position)
            startActivity(
                Intent(requireContext(), MainActivity::class.java)
                    .clearTop()
                    .clearTask()
            )
        }
    }

    private fun initViewPager() {
        adapter = ShopViewPagerAdapter(childFragmentManager)
        viewBinding.viewPager.offscreenPageLimit = ShopViewPagerAdapter.PAGE_NUMBER
        viewBinding.viewPager.adapter = adapter
        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)
        viewBinding.tabLayout.getTabAt(0)?.text = getString(R.string.women)
        viewBinding.tabLayout.getTabAt(1)?.text = getString(R.string.men)
        viewBinding.tabLayout.getTabAt(2)?.text = getString(R.string.kids)
        viewBinding.tabLayout.getTabAt(3)?.text = getString(R.string.unisex)
    }

    private fun setupToolbar() {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.btnNav.visible()
            initToolBar(viewBinding.layoutToolbar.toolbar, false)
            viewBinding.layoutToolbar.toolbar.tvTitleToolbar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_flash, 0, 0, 0)
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.shop),
                messageQueue = {
                    when (it.id) {
                        R.id.ivRight, R.id.tvCount -> {
                            navController.navigateAnimation(R.id.nav_shopping_card,
                                isBotToTop = true
                            )
                        }
                        R.id.ivLeft->{
                            navController.navigateAnimation(R.id.nav_search)
                        }
                    }
                }
            )
        }
    }

    private fun initCurrencyAdapter() {
        currencies = arrayListOf()
        currencyAdapter = CurrencySpinnerAdapter(
            requireContext(),
            currencies = currencies
        )
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.layoutToolbar.spCurrency.adapter = currencyAdapter
            viewBinding.layoutToolbar.spCurrency.onItemSelectedListener = this@ShopFragment
        }
    }

    private fun eventCurrency(position: Int) {
        (requireActivity() as? MainActivity)?.apply {
            viewBinding.layoutToolbar.spCurrency.setSelection(position)
            viewBinding.layoutToolbar.tvBackRight.text = currencies[position].code
        }
    }

    private fun getPosition(): Int {
        var position = 0
        currencies.forEachIndexed { index, supportedCurrency ->
            if (CoreApplication.instance.currency.code?.equals(supportedCurrency.code) == true) {
                position = index
            }
        }
        return position
    }

}