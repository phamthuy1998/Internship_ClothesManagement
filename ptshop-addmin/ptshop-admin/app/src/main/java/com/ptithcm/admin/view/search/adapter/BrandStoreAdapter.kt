package com.ptithcm.admin.view.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ptithcm.core.model.*
import com.ptithcm.admin.R
import com.ptithcm.admin.constant.KEY_EMPTY
import com.ptithcm.admin.databinding.ItemSearchResultBinding

class BrandStoreAdapter(
    private var key: String?,
    private val carouselListener: (id: Int, type: TypeCarousel) -> Unit,
    private val categoryListener: (id: Int, name: String, category: Categories?) -> Unit,
    private val sportListener: (id:String,name:String,isSport:Boolean) -> Unit
) :
    RecyclerView.Adapter<BrandStoreAdapter.SearchResultViewHolder>() {
    private var brands: ArrayList<Brand> = arrayListOf()
    private var stores: ArrayList<Stories> = arrayListOf()
    private var mainCategories: ArrayList<MainCategories> = arrayListOf()
    private var baseMainCategories: ArrayList<MainCategories> = arrayListOf()
    private var categories: ArrayList<Categories> = arrayListOf()
    private var sports: ArrayList<Sport> = arrayListOf()
    private var styles: ArrayList<Style> = arrayListOf()
    var brandsSuggested: ArrayList<Brand> = arrayListOf()
    var storesSuggested: ArrayList<Stories> = arrayListOf()

    fun setBaseMainCategories(mainCategories: ArrayList<MainCategories>) {
        this.baseMainCategories = mainCategories
    }

    fun setKey(key: String?) {
        this.key = key
    }

    fun setBrands(brands: ArrayList<Brand>) {
        this.brands = brands
        notifyDataSetChanged()
    }

    fun getBrands():ArrayList<Brand>{
        return brands
    }

    fun setStores(stores: ArrayList<Stories>) {
        this.stores = stores
        notifyDataSetChanged()
    }

    fun getStores():ArrayList<Stories>{
        return this.stores
    }

    fun setMainCategories(mainCategories: ArrayList<MainCategories>) {
        this.mainCategories = mainCategories
        notifyDataSetChanged()
    }

    fun setCategories(Categories: ArrayList<Categories>) {
        this.categories = Categories
        notifyDataSetChanged()
    }

    fun addCategory(Category: Categories) {
        this.categories.add(Category)
        notifyDataSetChanged()
    }

    fun setSports(sports: ArrayList<Sport>) {
        this.sports = sports
        notifyDataSetChanged()
    }

    fun setStyles(styles: ArrayList<Style>) {
        this.styles = styles
        notifyDataSetChanged()
    }

    fun addSuggestedBrands( suggestedBrands: ArrayList<Brand>){
        this.brandsSuggested.addAll(suggestedBrands)
        notifyDataSetChanged()
    }

    fun setSuggestedBrands( suggestedBrands: ArrayList<Brand>){
        this.brandsSuggested=suggestedBrands
        notifyDataSetChanged()
    }

    fun shortenSuggestedBrand(){
        this.brandsSuggested= ArrayList(this.brandsSuggested.distinct().take(3))
        notifyDataSetChanged()
    }

    fun setSuggestedStores( suggestedStores: ArrayList<Stories>){
        this.storesSuggested=suggestedStores
        notifyDataSetChanged()
    }

    fun addSuggestedStores( suggestedStores: ArrayList<Stories>){
        this.storesSuggested.addAll(suggestedStores)
        notifyDataSetChanged()
    }

    fun shortenSuggestedStores(){
        this.storesSuggested=ArrayList(this.storesSuggested.distinct().take(3))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val viewBinding = DataBindingUtil.inflate<ItemSearchResultBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_search_result,
            parent,
            false
        )
        return SearchResultViewHolder(viewBinding)
    }

    override fun getItemCount(): Int =
        brands.size + stores.size + mainCategories.size + categories.size + sports.size + styles.size +storesSuggested.size+brandsSuggested.size

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        when {
            position < brands.size -> holder.bind(brands[position])
            position < brands.size + stores.size -> holder.bind(stores[position - brands.size])
            position < brands.size + stores.size + mainCategories.size -> holder.bind(mainCategories[position - brands.size - stores.size])
            position < brands.size + stores.size + mainCategories.size + categories.size -> holder.bind(
                categories[position - brands.size - stores.size - mainCategories.size]
            )
            position < brands.size + stores.size + mainCategories.size + categories.size + sports.size -> holder.bind(
                sports[position - brands.size - stores.size - mainCategories.size - categories.size]
            )
            position < brands.size + stores.size + mainCategories.size + categories.size + sports.size + styles.size -> holder.bind(
                styles[position - brands.size - stores.size - mainCategories.size - categories.size - sports.size]
            )
            position < brands.size + stores.size + mainCategories.size + categories.size + sports.size + styles.size + brandsSuggested.size -> holder.bind(
                brandsSuggested[position - brands.size - stores.size - mainCategories.size - categories.size - sports.size - styles.size]
            )
            else -> holder.bind(storesSuggested[position - brands.size - stores.size - mainCategories.size - categories.size - sports.size - styles.size - brandsSuggested.size])
        }
    }

    inner class SearchResultViewHolder(val viewBinding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(brand: Brand?) {
            viewBinding.item.text = brand?.text ?: KEY_EMPTY
            viewBinding.item.setOnClickListener {
                carouselListener(brand?.value ?: 0, TypeCarousel.BRAND)
            }
        }

        fun bind(store: Stories?) {
            viewBinding.item.text = store?.text ?: KEY_EMPTY
            viewBinding.item.setOnClickListener {
                carouselListener(store?.value ?: 0, TypeCarousel.STORE)
            }
        }

        fun bind(mainCategory: MainCategories) {
            viewBinding.item.text = mainCategory.text ?: KEY_EMPTY
            viewBinding.item.setOnClickListener {
                categoryListener(mainCategory.value ?: 0, mainCategory.text ?: "",null)
            }

        }

        fun bind(category: Categories) {
            viewBinding.item.text =
                baseMainCategories.filter { it.value == category.main_categories?.get(0) }[0].text + " > " + category.text
            viewBinding.item.setOnClickListener {
                categoryListener(category.value ?: 0, category.text ?: "",category)
            }

        }

        fun bind(sport: Sport) {
            viewBinding.item.text = sport.text
            viewBinding.item.setOnClickListener {
                sportListener(sport.value?:"",sport.text?:"",true)
            }
        }

        fun bind(style:Style) {
            viewBinding.item.text = style.text
            viewBinding.item.setOnClickListener {
                sportListener(style.value?:"",style.text?:"",false)
            }
        }
    }
}
