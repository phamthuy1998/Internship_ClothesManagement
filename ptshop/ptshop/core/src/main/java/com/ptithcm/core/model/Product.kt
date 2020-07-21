package com.ptithcm.core.model

import android.os.Parcelable
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.ptithcm.core.vo.ItemViewModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    var title: String? = "",
    var brand: Brand? = Brand(),
    var image: ArrayList<Image>? = arrayListOf(),
    var vendor: String? = "",
    var product_type: String? = "",
    var options: ArrayList<Option>? = arrayListOf(),
    var variants: ArrayList<Variant>? = arrayListOf(),
    var created_at: String? = "",
    var is_active: Boolean = false,
    var pin_ordering: Int? = 0,
    var pinned: Boolean = false,
    var description: String? = "",
    var vendor_brand: Brand? = Brand(),
    override val id: Int = 0,
    var obserIsSelected: ObservableBoolean = ObservableBoolean(false),
    var obserCounter: ObservableField<Int> = ObservableField(0),
    var isAddProduct : Boolean = false
) : Parcelable, ItemViewModel{

    fun getDescHtml(): Spanned {
        val range = description?.indexOfFirst {
            it.toString() == "{"
        }
        var result = description
        if (range != -1){
            result = description?.removeRange(range ?: 0, description?.length ?: 0)
        }

        if (result?.contains("<ul>") == true) {
            result = result.replace("<ul>", "")

            result = result.replace(
                "<li>",
                "<br>\t\t\t•\t\t\t"
            )

            result = result.replace("</li>", "").replace(" ", "")
        }

        return HtmlCompat.fromHtml(result ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    fun checkVariantWrongPrice(){
        variants?.map { it.checkIfWrongPrice() }
    }
}