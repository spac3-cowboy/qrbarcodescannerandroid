package com.innova.qrcodescanner.plus.extension
fun Boolean?.orFalse(): Boolean {
    return this ?: false
}