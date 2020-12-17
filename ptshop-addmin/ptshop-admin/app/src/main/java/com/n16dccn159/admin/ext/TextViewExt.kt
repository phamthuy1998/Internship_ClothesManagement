package com.n16dccn159.admin.ext

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.n16dccn159.core.model.Gender
import com.n16dccn159.core.param.BrandsRefine
import com.n16dccn159.core.param.CategoriesRefine
import com.n16dccn159.core.param.ColourRefine
import com.n16dccn159.core.param.SizeRefine
import com.n16dccn159.admin.constant.KEY_EMPTY
import com.n16dccn159.admin.constant.VIEW_ALL

fun TextView.makeLinks(
    vararg links: Pair<String, View.OnClickListener>,
    isUnderline: Boolean = true
) {
    val spannableString = SpannableString(this.text)
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
//                Selection.setSelection((view as TextView).text as Spannable, 0)
//                view.invalidate()
                link.second.onClick(view)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = isUnderline
                ds.color = Color.parseColor("#989898")
            }
        }
        val startIndexOfLink = this.text.toString().indexOf(link.first)
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}

fun TextView.gendersRefine(genders: ArrayList<Gender>?) {
    var strGenders = if ((genders?.size ?: 0) > 1) "(${genders?.size}) " else KEY_EMPTY
    genders?.forEachIndexed { index, item ->
        strGenders += if (index == genders.size - 1) {
            getValueGender(item)
        } else {
            "${getValueGender(item)}, "
        }
    }
    this.text = strGenders
}

fun TextView.coloursRefine(colours: ArrayList<ColourRefine>?) {
    var strColours = if ((colours?.size ?: 0) > 1) "(${colours?.size}) " else KEY_EMPTY
    colours?.forEachIndexed { index, item ->
        strColours += if (index == colours.size - 1) {
            item.name
        } else {
            "${item.name}, "
        }
    }
    this.text = strColours
}

fun TextView.sizesRefine(sizes: ArrayList<SizeRefine>?) {
    var strSizes = if ((sizes?.size ?: 0) > 1) "(${sizes?.size}) " else KEY_EMPTY
    sizes?.forEachIndexed { index, item ->
        strSizes += if (index == sizes.size - 1) {
            item.name
        } else {
            "${item.name}, "
        }
    }
    this.text = strSizes
}

fun TextView.sizeCategories(categories: ArrayList<CategoriesRefine>?) {
    var strCategories = if ((categories?.size ?: 0) < 2) KEY_EMPTY else "(${categories?.size}) "
    categories?.forEachIndexed { index, item ->
        strCategories += if (index == categories.size - 1) {
            if (item.name?.contains(VIEW_ALL) == true) {
                item.name?.substringAfterLast(VIEW_ALL)
            } else {
                item.name
            }
        } else {
            if (item.name?.contains(VIEW_ALL) == true) {
                "${item.name?.substringAfterLast(VIEW_ALL)}, "
            } else {
                "${item.name}, "
            }
        }
    }
    this.text = strCategories
}

fun TextView.sizeBrands(brands: ArrayList<BrandsRefine>?) {
    var strBrands = if ((brands?.size ?: 0) < 2) KEY_EMPTY else "(${brands?.size}) "
    brands?.forEachIndexed { index, item ->
        strBrands += if (index == brands.size - 1) {
            item.name
        } else {
            "${item.name}, "
        }
    }
    this.text = strBrands
}