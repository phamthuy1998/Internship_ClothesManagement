package com.ptithcm.admin.ext

import android.app.Activity
import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputType
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText


fun TextInputEditText.togglePassword(isShowPassword: Boolean, typeface: Typeface?) {
    if (isShowPassword) {
        this.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    } else {
        this.inputType =
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }
    typeface?.let {
        this.typeface = it
    }
    this.setSelection(this.length())
}

fun TextInputEditText.isEmpty(): Boolean {
    return this.text.isNullOrEmpty()
}

fun EditText.getTextTrim() = text.trim().toString()
fun EditText.emptyEdt() {
    setText("")
}

fun TextInputEditText.setMaxLength(length: Int) {
    val filterArray = arrayOfNulls<InputFilter>(1)
    filterArray[0] = InputFilter.LengthFilter(length)
    this.filters = filterArray
}

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        currentFocus?.windowToken, 0
    )
}

fun Activity.showKeyBoard() {
    val inputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun EditText.setCursorEnd(string: String){
    requestFocus()
    setSelection(string.length)
}