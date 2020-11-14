package com.ptithcm.ptshop.ext

import com.ptithcm.core.model.Brand
import com.ptithcm.core.model.Option
import com.ptithcm.core.model.Product
import com.ptithcm.core.model.Variant

fun Variant.copyFrom(oldVariant: Variant): Variant{
    return this.copy(
        product = oldVariant.product?.copyToNew()
    )
}

fun Product.copyToNew(): Product{
    return this.copy(
        brand = Brand(brand_name = brand?.brand_name, id = brand?.id)
    )
}

// just use in product detail page
fun Option?.containValue(value: String): Boolean{
    return if (this == null){
        true
    }else{
        values?.contains(value) == true
    }
}