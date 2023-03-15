package com.innova.qrcodescanner.plus.extension
fun Int?.orZero(): Int {
    return this ?: 0
}