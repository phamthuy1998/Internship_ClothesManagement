package com.n16dccn159.admin.ext

import com.n16dccn159.core.model.Brand
import com.n16dccn159.core.model.Option
import com.n16dccn159.core.model.Product
import com.n16dccn159.core.model.Variant

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