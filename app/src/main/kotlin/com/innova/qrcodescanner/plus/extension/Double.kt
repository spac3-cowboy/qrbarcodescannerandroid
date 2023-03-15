package com.innova.qrcodescanner.plus.extension
fun Double?.orZero(): Double {
    return this ?: 0.0
}