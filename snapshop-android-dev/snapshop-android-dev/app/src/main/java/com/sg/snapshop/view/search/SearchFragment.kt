package com.sg.snapshop.view.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.textservice.*
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sg.core.model.*
import com.sg.core.param.CategoriesParam
import com.sg.snapshop.R
import com.sg.snapshop.base.BaseActivity
import com.sg.snapshop.base.BaseFragment
import com.sg.snapshop.constant.*
import com.sg.snapshop.databinding.FragmentSearchBinding
import com.sg.snapshop.ext.*
import com.sg.snapshop.util.SpellChecker
import com.sg.snapshop.view.MainActivity
import com.sg.snapshop.view.home.StoryDetailActivity
import com.sg.snapshop.view.search.adapter.BrandStoreAdapter
import com.sg.snapshop.viewmodel.ProductFilterViewModel
import com.sg.snapshop.viewmodel.ShopViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : BaseFragment<FragmentSearchBinding>(),
    SpellCheckerSession.SpellCheckerSessionListener {

    override val layoutId: Int
        get() = R.layout.fragment_search

    private lateinit var adapter: BrandStoreAdapter
    private val viewModel: ProductFilterViewModel by sharedViewModel(from = { requireActivity() })
    private val shopViewModel: ShopViewModel by viewModel()
    private var mainCategories: ArrayList<MainCategories> = arrayListOf()
    private var categories: ArrayList<Categories> = arrayListOf()
    private var sports: ArrayList<Sport> = arrayListOf()
    private var styles: ArrayList<Style> = arrayListOf()
    private var brands: ArrayList<Brand> = arrayListOf()
    private var stores: ArrayList<Stories> = arrayListOf()
    private var baseMainCategories: ArrayList<Categories> = arrayListOf()
    private var suggestions: ArrayList<String> = arrayListOf()
    private var mScs: SpellCheckerSession? = null
    private var spellChecker: SpellChecker? = null
    private var searchKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as? BaseActivity<*>)?.isShowLoading(false)
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        adapter =
            BrandStoreAdapter(
                KEY_EMPTY,
                this::clickCarouselListener,
                this::clickCategoryListener,
                this::clickSportListener
            )
        shopViewModel.getMainCategories(Gender.NONE)
    }

    override fun onResume() {
        super.onResume()
        val tsm =
            activity?.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager
        mScs = tsm.newSpellCheckerSession(null, Locale.ENGLISH, this, false)
    }


    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.productFilterLiveData.observe(this, Observer {
            mainCategories = it.main_category ?: arrayListOf()
            categories = it.category ?: arrayListOf()
            sports = it.sports ?: arrayListOf()
            styles = it.styles ?: arrayListOf()
            brands = it.mapBrands()
            stores = it.mapStories()
            adapter.setBaseMainCategories(mainCategories)
            val temp: ArrayList<String> = arrayListOf()
            brands.forEach { it1 ->
                temp.add(it1.text ?: "")
            }
            stores.forEach { it1 ->
                temp.add(it1.text ?: "")
            }
            spellChecker = SpellChecker(temp)
        })
        shopViewModel.categoriesLiveData.observe(this, Observer {
            if (it != null) {
                baseMainCategories = it
            }
        })
    }

    override fun bindEvent() {
        super.bindEvent()
        viewBinding.fragment = this
        (activity as? MainActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.gone()
            transparentStatusBar(false)
        }
        (activity as? StoryDetailActivity)?.apply {
            viewBinding.layoutToolbar.toolbar.gone()
            transparentStatusBar(false)
        }
        initAdapter()
        eventSearch()
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> navController.popBackStack()
            R.id.ivSearch -> {
                navController.navigate(
                    R.id.fragment_search_result,
                    bundleOf(KEY_SEARCH to viewBinding.edtSearchInput.text.toString())
                )
            }
            R.id.ivCancel -> {
                viewBinding.edtSearchInput.setText("")
            }
        }
    }

    private fun clickCarouselListener(id: Int?, type: TypeCarousel) {
        if (type == TypeCarousel.STORE) {
            navController.navigate(
                R.id.nav_carousel_detail,
                bundleOf(
                    KEY_ARGUMENT to Carousel(
                        storeId = id,
                        type = type.value,
                        gender = Gender.NONE.value
                    )
                )
            )
        } else {
            navController.navigate(
                R.id.nav_carousel_detail,
                bundleOf(
                    KEY_ARGUMENT to Carousel(
                        brand_id = id,
                        type = type.value,
                        gender = Gender.NONE.value
                    )
                )
            )
        }

    }

    private fun clickSportListener(id: String?, name: String, isSport: Boolean) {
        if (isSport) {
            navController.navigate(
                R.id.fragment_search_result,
                bundleOf("key_sport" to id, "title" to name)
            )
        } else {
            navController.navigate(
                R.id.fragment_search_result,
                bundleOf("key_style" to id, "title" to name)
            )
        }

    }


    private fun clickCategoryListener(main_category_id: Int, name: String, category: Categories?) {
        if (category == null) {
            navController.navigateAnimation(
                R.id.nav_categories_detail, bundle =
                bundleOf(
                    KEY_ARGUMENT to CategoriesParam(
                        name = "$VIEW_ALL $name",
                        main_category_id = main_category_id,
                        typeCategories = TypeCategories.MAIN_CATEGORIES,
                        gender = Gender.NONE.value
                    ),
                    KEY_BANNER_CATEGORY to baseMainCategories.getBannerCategory(
                        main_category_id,
                        Gender.NONE
                    )
                )
            )
        } else {
            navController.navigateAnimation(
                R.id.nav_categories_detail, bundle =
                bundleOf(
                    KEY_ARGUMENT to CategoriesParam(
                        name = name,
                        main_category_id = category.main_categories?.get(0) ?: 0,
                        category_id = category.value ?: 0,
                        typeCategories = TypeCategories.CATEGORIES,
                        gender = Gender.NONE.value
                    ),
                    KEY_BANNER_CATEGORY to baseMainCategories.getBannerCategory(
                        category.main_categories?.get(
                            0
                        ) ?: 0, Gender.NONE
                    )
                )
            )
        }

    }

    private fun initAdapter() {
        viewBinding.rcvSearchResult.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.rcvSearchResult.adapter = adapter
    }

    private fun eventSearch() {
        viewBinding.edtSearchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(key: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(key: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (key.toString().isBlank()) {
                    viewBinding.ivCancel.visibility = View.GONE
                    resetSearchResult()
                } else {
                    viewBinding.ivCancel.visibility = View.VISIBLE
                    if (key.toString().length >= 2) {
                        searchKey=key.toString()
                        adapter.setKey(key.toString())
                        adapter.setBrands(ArrayList(brands.filter {
                            it.text.removeAccent().startsWith(
                                key.removeAccent(),
                                true
                            )
                        }.take(3)))
                        adapter.setStores(ArrayList(stores.filter {
                            it.text.removeAccent().startsWith(
                                key.removeAccent(),
                                true
                            )
                        }.take(3)))
                        adapter.setCategories(arrayListOf())
                        adapter.setMainCategories(ArrayList(mainCategories.filter {
                            (it.text ?: "").startsWith(key.toString(), true)
                        }.take(3)))
                        var count = 3
                        for (category in categories) {
                            if ((category.text
                                    ?: "").startsWith(key.toString(), true)
                            ) {
                                for (mainCategory in (category.main_categories ?: arrayListOf())) {
                                    adapter.addCategory(category.copyCategorySearch(mainCategory))
                                    count--
                                    if (count == 0) break
                                }
                            }
                            if (count == 0) break
                        }
                        adapter.setSports(ArrayList(sports.filter {
                            (it.text ?: "").startsWith(key.toString(), true)
                        }.take(3)))
                        adapter.setStyles(ArrayList(styles.filter {
                            (it.text ?: "").startsWith(key.toString(), true)
                        }.take(3)))
                        mScs?.getSentenceSuggestions(arrayOf(TextInfo(key.toString())), 10)
                        if (mScs == null) {
                            adapter.setSuggestedBrands(arrayListOf())
                            adapter.setSuggestedStores(arrayListOf())
                            suggestions =
                                spellChecker?.makeSuggestions(key.toString()) ?: arrayListOf()
                            adaptSuggestion()
                        }

                    }
                }
            }

        })

        viewBinding.edtSearchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                navController.navigate(
                    R.id.fragment_search_result,
                    bundleOf(KEY_SEARCH to viewBinding.edtSearchInput.text.toString())
                )
            }
            true
        }
    }

    private fun resetSearchResult() {
        adapter.setKey(null)
        adapter.setBrands(arrayListOf())
        adapter.setStores(arrayListOf())
        adapter.setMainCategories(arrayListOf())
        adapter.setCategories(arrayListOf())
        adapter.setSports(arrayListOf())
        adapter.setStyles(arrayListOf())
        adapter.setSuggestedBrands(arrayListOf())
        adapter.setSuggestedStores(arrayListOf())
    }

    override fun onGetSentenceSuggestions(results: Array<out SentenceSuggestionsInfo>?) {
        suggestions = arrayListOf()
        adapter.setSuggestedBrands(arrayListOf())
        adapter.setSuggestedStores(arrayListOf())
        if (results != null) {
            for (result in results) {
                val n = result.suggestionsCount
                for (i in 0 until n) {
                    val m = result.getSuggestionsInfoAt(i).suggestionsCount
                    if (result.getSuggestionsInfoAt(i).suggestionsAttributes and SuggestionsInfo.RESULT_ATTR_LOOKS_LIKE_TYPO != SuggestionsInfo.RESULT_ATTR_LOOKS_LIKE_TYPO)
                        continue

                    for (k in 0 until m) {
                        suggestions.add(result.getSuggestionsInfoAt(i).getSuggestionAt(k))
                    }
                }
            }
            adaptSuggestion()
            if (adapter.brandsSuggested.size==0 && adapter.storesSuggested.size==0) {
                suggestions = spellChecker?.makeSuggestions(searchKey.split(" ")[0]) ?: arrayListOf()
                adaptSuggestion()
            }

        }

    }

    private fun adaptSuggestion() {
        for (suggestion in suggestions) {
            val brandSuggestions = ArrayList(brands.filter {
                (it.text ?: "").startsWith(
                    suggestion,
                    true
                )
            })
            for (brand in brandSuggestions) {
                if (!adapter.getBrands().contains(brand)) {
                    adapter.addSuggestedBrands(arrayListOf(brand))
                }
            }
            val storeSuggestions = ArrayList(stores.filter {
                (it.text ?: "").startsWith(
                    suggestion,
                    true
                )
            })
            for (store in storeSuggestions) {
                if (!adapter.getStores().contains(store)) {
                    adapter.addSuggestedStores(arrayListOf(store))
                }
            }

        }
        adapter.shortenSuggestedBrand()
        adapter.shortenSuggestedStores()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mScs?.close()
    }

    override fun onGetSuggestions(results: Array<out SuggestionsInfo>?) {
    }

}