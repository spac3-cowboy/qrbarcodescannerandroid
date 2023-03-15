package com.innova.qrcodescanner.plus.extension
fun Long?.orZero(): Long {
    return this ?: 0L
}