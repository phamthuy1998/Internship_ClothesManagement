package com.ptithcm.core.model

data class ProductsFilter(
    var main_category: ArrayList<MainCategories>? = arrayListOf(),
    var category: ArrayList<Categories>? = arrayListOf(),
    var filters: ArrayList<ExFilter>? = arrayListOf(),
    var colour: ArrayList<Colour>? = arrayListOf(),
    var size: ArrayList<Size>? = arrayListOf(),
    var gender: ArrayList<GenderFilter>? = arrayListOf(),
    var sports: ArrayList<Sport>? = arrayListOf(),
    var styles: ArrayList<Style>? = arrayListOf(),
    var brand: ArrayList<BrandFilter>? = arrayListOf(),
    var vendor_brand: ArrayList<BrandFilter>? = arrayListOf()
) {
    fun mapBrands() : ArrayList<Brand>{
        val results = arrayListOf<Brand>()
        vendor_brand?.forEach {
            results.add(it.copyBrand())
        }
        return results
    }

    fun mapStories() : ArrayList<Stories>{
        val results = arrayListOf<Stories>()
        brand?.forEach {
            results.add(it.copyStories())
        }
        return results
    }
}

data class BrandFilter(
    var text: String? = "",
    var order: Int? = 0,
    var value: Int? = 0
) {
    fun copyBrand() : Brand{
        return Brand(value = value, text = text)
    }

    fun copyStories() : Stories {
        return Stories(value = value, text = text)
    }
}