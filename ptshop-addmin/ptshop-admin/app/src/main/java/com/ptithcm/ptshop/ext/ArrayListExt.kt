package com.ptithcm.ptshop.ext

import com.ptithcm.core.model.*
import com.ptithcm.core.param.CategoriesRefine
import com.ptithcm.core.param.ProductsParam
import com.ptithcm.ptshop.constant.COLOR_OPTION
import com.ptithcm.ptshop.constant.SIZE_OPTION
import com.ptithcm.ptshop.ext.refine.getItemCategory

fun ArrayList<SupportedCurrency>.convertArrayListToArray(): Array<String?> {
    val nameCurrencies: ArrayList<String> = arrayListOf()
    this.forEach { item ->
        nameCurrencies.add("${item.name?.toValue()}")
    }
    val array = arrayOfNulls<String>(this.size)
    nameCurrencies.toArray(array)
    return array
}

fun getMainCategories(type: Gender?, productsFilter: ProductsFilter?): ArrayList<MainCategories>? {
    val results = arrayListOf<MainCategories>()
    if (type != null) {
        productsFilter?.main_category?.forEach { item ->
            val isGender = (item.genders?.filter { it == type.value }?.size ?: 0) > 0
            if (isGender) {
                item.initFeature()
                results.add(item)
            }
        }
    } else {
        productsFilter?.main_category?.map {
            it.initFeature()
        }
        results.addAll(productsFilter?.main_category ?: arrayListOf())
    }
    results.sortBy {
        it.order
    }
    getCategories(results, productsFilter, type)
    return results
}

private fun getCategories(
    mainCategory: ArrayList<MainCategories>, productsFilter: ProductsFilter?,
    type: Gender?
): ArrayList<MainCategories>? {
    mainCategory.map { item ->
        val results: ArrayList<MainCategories> = arrayListOf()
        val itemViewAll = item.copyItemAll()
        results.add(itemViewAll)
        productsFilter?.category?.forEach {
            if (type != null) {
                if (it.main_categories?.contains(item.value) == true && it.genders?.contains(
                        type.value
                    ) == true
                ) {
                    val mainCategories = it.copyMainCategory()
                    results.add(mainCategories)
                }
            } else {
                if (it.main_categories?.contains(item.value) == true) {
                    val mainCategories = it.copyMainCategory()
                    results.add(mainCategories)
                }
            }
        }
        results.sortBy {
            it.order
        }
        getFilters(results, productsFilter, type)
        item.childCategories = results
    }
    return mainCategory
}

private fun getFilters(
    mainCategory: ArrayList<MainCategories>, productsFilter: ProductsFilter?,
    type: Gender?
): ArrayList<MainCategories>? {
    mainCategory.map { item ->
        if (item.order == 0) return@map
        val results: ArrayList<MainCategories> = arrayListOf()
        val itemViewAll = item.copyItemAll()
        results.add(itemViewAll)
        productsFilter?.filters?.forEach {
            val isCategory = if (type != null) {
                (it.categories?.filter { category ->
                    category.value == item.value && category.gender == type.value
                }?.size ?: 0) > 0
            } else {
                (it.categories?.filter { category -> category.value == item.value }?.size
                    ?: 0) > 0
            }
            if (isCategory) {
                val mainCategories = it.copyMainCategory()
                results.add(mainCategories)
            }
        }
        results.sortBy {
            it.order
        }
        item.childCategories = results
    }
    return mainCategory
}

fun ArrayList<MainCategories>.getItemCategories(productParam: ProductsParam): ArrayList<CategoriesRefine> {
    val mainCategories = productParam.mainCategories
    val categories = productParam.categories
    val positionParent = arrayListOf<Int>()
    val positionResult = hashMapOf<Int, Int>()
    val results = arrayListOf<CategoriesRefine>()
    mainCategories?.forEach {
        this.forEachIndexed { index, itemMain ->
            if (it == itemMain.value) {
                positionParent.add(index)
                return@forEachIndexed
            }
        }
    }
    positionParent.forEach {
        positionResult[it] = 0
        if (categories?.isEmpty() != true) {
            this[it].childCategories?.forEachIndexed { index, itemCat ->
                categories?.forEach { item ->
                    if (item == itemCat.value) {
                        positionResult[index] = it
                    }
                }
            }
        }
    }
    positionResult.forEach {
        val position = if (it.value == 0) Triple(it.key, 0, -1)
        else Triple(it.value, it.key, 0)
        results.add(
            getItemCategory(this, position)?.copyMainCategoryRefine(position) ?: CategoriesRefine()
        )
    }
    return results
}

fun ArrayList<MainCategories>.getArrayItemCategories(param: CategoriesRefine): ArrayList<MainCategories>? {
    return when (param.type) {
        TypeCategories.MAIN_CATEGORIES -> {
            this.first {
                it.value == param.id
            }.childCategories
        }
        TypeCategories.CATEGORIES -> {
            if (param.position.second == 0) {
                this[param.position.first].childCategories
            } else {
                this[param.position.first].childCategories?.get(param.position.second)
                    ?.childCategories
            }
        }
        else -> {
            arrayListOf()
        }
    }
}

fun getCategoriesRefine(
    productsFilter: ProductsFilter?, categoriesRefine: CategoriesRefine,
    gender: Gender
): ArrayList<MainCategories>? {
    val results = arrayListOf<MainCategories>()
    val itemViewAll = categoriesRefine.copyMainCategory()
    results.add(itemViewAll)
    productsFilter?.filters?.forEach {
        val isCategory =
            (it.categories?.filter { category ->
                category.value == categoriesRefine.id && category.gender == gender.value
            }?.size ?: 0) > 0
        if (isCategory) {
            val mainCategories = it.copyMainCategory()
            results.add(mainCategories)
        }
    }
    results.sortBy {
        it.order
    }
    return results
}

fun ArrayList<Categories>.getBannerCategory(id: Int?, type: Gender): String? {
    var banner: String? = null
    this.forEach { item ->
        if (item.id == id) {
            banner = if (type != Gender.NONE) {
                item.images?.firstOrNull { it.gender == type.value }?.banner_image_url
            } else {
                item.images?.firstOrNull { it.gender == Gender.UNISEX.value }?.banner_image_url
            }

            return@forEach
        }
    }
    return banner
}

fun ArrayList<Product>.copyArrProd(): ArrayList<Product> {
    return this.map {
        it.copy(brand = null)
    } as ArrayList<Product>
}

fun ArrayList<Option>.colorOption() = this.find {
    it.name == COLOR_OPTION
}

fun ArrayList<Option>.sizeOption() = this.find {
    it.name == SIZE_OPTION
}

// check for when change shipping rate in checkout page
fun ArrayList<Pair<Int, String>>.removeOrAdd(item: Pair<Int, String>) {
    val oldItem = find {
        it.second == item.second
    }
    remove(oldItem)
    add(item)
}

fun ArrayList<Pair<Int, String>>.findShippingRatePosition(second: String?) =
    find {
        it.second == second
    }?.first ?: 0

fun ArrayList<String>.checkProdAvailable(hasQuantity: Boolean, isAvailable: Boolean): List<String>{
    val newArr = this.clone() as ArrayList<String>
    if(!hasQuantity && !isAvailable){
        newArr.add(0,"")
    }
    return newArr
}

fun ArrayList<ProductVariant>.calculatePriceAfterTax(): Double = this.fold(0.0, {sum, variant ->
    (variant.product_variant.price_after_tax?.toDouble() ?: 0.0) * variant.quantity + sum
})

fun ArrayList<ProductClothesDetail>?.calculateFinalPrice(): Double = this?.fold(0.0, {sum, prod -> sum + (prod.getFinalPrice() * (prod.quantityInCart?.quantity ?: 1))}) ?: 0.0

fun ArrayList<Boolean>.finalBoolean() = if (this.size == 0)
    false else this.fold(true, {result, nextBoolean ->
    result && nextBoolean
})