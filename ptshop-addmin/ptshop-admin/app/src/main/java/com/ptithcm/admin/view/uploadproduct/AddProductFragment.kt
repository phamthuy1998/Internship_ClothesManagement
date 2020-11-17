package com.ptithcm.admin.view.uploadproduct

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ptithcm.admin.R
import com.ptithcm.admin.base.BaseFragment
import com.ptithcm.admin.constant.ERROR_CODE_404
import com.ptithcm.admin.databinding.FragmentAddProductBinding
import com.ptithcm.admin.ext.*
import com.ptithcm.admin.listener.EndlessRecyclerViewScrollListener
import com.ptithcm.admin.view.MainActivity
import com.ptithcm.admin.view.uploadproduct.adapter.AddProductAdapter
import com.ptithcm.admin.viewmodel.AddProductViewModel
import com.ptithcm.admin.viewmodel.ShareDataViewModel
import com.ptithcm.core.model.Product
import kotlinx.android.synthetic.main.layout_register_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductFragment : BaseFragment<FragmentAddProductBinding>(), View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_add_product

    private val addProductViewModel by viewModel<AddProductViewModel>()
    private val shareDataViewModel: ShareDataViewModel by sharedViewModel(from = { requireActivity() })
    private var page = 1
    private var keyword: String? = null
    private lateinit var addProductAdapter: AddProductAdapter
    private var listener: EndlessRecyclerViewScrollListener? = null
    private var selectedList = arrayListOf<Product>()
    private var productCounter = 0
    private var needLoadMore = false

    private fun setupToolbar() {
        (requireActivity() as? UploadProductActivity)?.apply {
            initToolBar(viewBinding.layoutToolbar.toolbar, true, hasBackRight = false)
            setupToolbar(viewBinding.layoutToolbar.toolbar, getString(R.string.add_product))
            viewBinding.layoutToolbar.ivBack.setOnClickListener {
                navController.popBackStack()
            }
            viewBinding.layoutToolbar.toolbar.tvTitleToolbar.textSize = 16f
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.tvProductFound.text = getString(R.string.x_products_found, "0")
        viewBinding.btnClear.setOnClickListener(this)
        viewBinding.btnApply.setOnClickListener(this)

        viewBinding.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                keyword = viewBinding.edtSearch.text.toString().trim()
                if (keyword?.isNotBlank() == true) {
                    needLoadMore = false
                    viewBinding.btnClear.visible()
                    page = 1
                    addProductAdapter.setList(arrayListOf())
                    addProductViewModel.searchProduct(keyword, page)
                    viewBinding.incEmpty.gone()
                }
            }
            true
        }

        setUpAdapter()
    }

    override fun bindViewModel() {
        super.bindViewModel()

        addProductViewModel.productLiveData.observe(this, Observer {
            addProductAdapter.isShowLoadMore(false)
            viewBinding.tvProductFound.text =
                getString(R.string.x_products_found, it.count.toString())
            if (!it.results.isNullOrEmpty()) {
                updateSelectedItem(it.results)
                viewBinding.rcvAddProduct.visible()
            } else {
                viewBinding.rcvAddProduct.gone()
            }
        })

        addProductViewModel.networkState.observe(this, Observer {
            if (!needLoadMore)
                viewBinding.isShowLoading = it
        })

        addProductViewModel.error.observe(this, Observer {
            if (it.second == ERROR_CODE_404) {
                (requireActivity() as? MainActivity)?.isShowErrorNetwork(true)
                addProductAdapter.isShowLoadMore(false)
            }
        })

        shareDataViewModel.currentSelectedProducts.observe(this, Observer {
            selectedList = it
            productCounter = it.size
        })
    }

    private fun setUpAdapter() {
        addProductAdapter = AddProductAdapter(arrayListOf(), this::onItemClickListener)
        viewBinding.rcvAddProduct.apply {
            adapter = addProductAdapter
            listener = object :
                EndlessRecyclerViewScrollListener(this.layoutManager as? LinearLayoutManager) {
                override fun onLoadMore(x: Int, y: Int) {
                    addProductAdapter.isShowLoadMore(true)
                    viewBinding.rcvAddProduct.scrollToPosition(addProductAdapter.itemCount - 1)
                    needLoadMore = true
                    page++
                    addProductViewModel.searchProduct(keyword, page)
                }
            }
            addOnScrollListener(listener ?: return)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnClear -> {
                viewBinding.edtSearch.setText("")
                viewBinding.btnClear.inVisible()
                if (addProductAdapter.getList().isNullOrEmpty()) {
                    viewBinding.incEmpty.visible()
                    viewBinding.rcvAddProduct.gone()
                } else {
                    viewBinding.incEmpty.gone()
                    viewBinding.rcvAddProduct.visible()
                }
            }
            R.id.btnApply -> {
                shareDataViewModel.requestSelectProduct(selectedList)
                navController.popBackStack()
            }
        }
    }

    private fun onItemClickListener(product: Product?) {
        when (product?.obserIsSelected?.get()) {
            true -> {
                if (productCounter > 0) {
                    productCounter -= 1
                    product.obserIsSelected.set(false)
                    updateUI(addProductAdapter.getList(), product)
                    selectedList.removeAll { it.id == product.id }
                }

            }
            false -> {
                if (productCounter < 10) {
                    productCounter += 1
                    product.obserCounter.set(productCounter)
                    product.obserIsSelected.set(true)
                    selectedList.add(product)
                } else {
                    (activity as? UploadProductActivity)?.showError(getString(R.string.error_select_product_limit))
                }

            }
        }
        viewBinding.btnApply.text = if (productCounter == 0) getString(R.string.apply) else
            getString(R.string.applys, productCounter.toString())
    }

    private fun updateUI(listProducts: ArrayList<Product>?, itemRemove: Product) {
        listProducts?.map { product: Product ->
            if (product.obserCounter.get() ?: 0 > itemRemove.obserCounter.get() ?: 0) {
                product.obserCounter.set(product.obserCounter.get()?.minus(1))
            }
        }
        addProductAdapter.setList(listProducts)
    }

    private fun updateSelectedItem(listProducts: ArrayList<Product>) {
        selectedList.forEach { product ->
            listProducts.find {
                it.id == product.id
            }.apply {
                this?.obserIsSelected?.set(product.obserIsSelected.get())
                this?.obserCounter?.set(product.obserCounter.get())
            }
        }
        addProductAdapter.addAll(listProducts)
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