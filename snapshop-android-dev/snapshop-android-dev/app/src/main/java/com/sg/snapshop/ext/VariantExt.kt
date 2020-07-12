package com.sg.snapshop.ext

import com.sg.core.model.Brand
import com.sg.core.model.Option
import com.sg.core.model.Product
import com.sg.core.model.Variant

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