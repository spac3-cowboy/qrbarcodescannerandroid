package com.innova.qrcodescanner.plus.extension
import java.util.Locale
val Locale?.isRussian: Boolean get() = this?.language == "ru"