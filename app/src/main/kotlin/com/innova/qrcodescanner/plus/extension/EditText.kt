package com.innova.qrcodescanner.plus.extension
import android.widget.EditText
fun EditText.isNotBlank(): Boolean {
    return text.isNotBlank()
}
val EditText.textString: String get() = text.toString()