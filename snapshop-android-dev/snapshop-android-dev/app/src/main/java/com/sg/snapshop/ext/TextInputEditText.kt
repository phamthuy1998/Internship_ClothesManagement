package com.sg.snapshop.ext

import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputType
import com.google.android.material.textfield.TextInputEditText


fun TextInputEditText.togglePassword(isShowPassword:Boolean,typeface:Typeface?){
    if (isShowPassword) {
        this.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    } else {
        this.inputType =
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }
    typeface?.let{
        this.typeface=it
    }
   this.setSelection(this.length())
}
fun TextInputEditText.isEmpty():Boolean{
    return this.text.isNullOrEmpty()
}

fun TextInputEditText.setMaxLength(length: Int) {
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = InputFilter.LengthFilter(length)
    this.filters = filterArray
}