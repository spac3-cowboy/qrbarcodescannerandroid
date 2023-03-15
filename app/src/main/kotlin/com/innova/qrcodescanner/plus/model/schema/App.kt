package com.innova.qrcodescanner.plus.model.schema
import com.innova.qrcodescanner.plus.extension.removePrefixIgnoreCase
import com.innova.qrcodescanner.plus.extension.startsWithAnyIgnoreCase
import com.innova.qrcodescanner.plus.extension.unsafeLazy
class App(val url: String) : Schema {
    companion object {
        private val PREFIXES = listOf("market://details?id=", "market://search", "http://play.google.com/", "https://play.google.com/")
        fun parse(text: String): App? {
            if (text.startsWithAnyIgnoreCase(PREFIXES).not()) {
                return null
            }
            return App(text)
        }
        fun fromPackage(packageName: String): App {
            return App(PREFIXES[0] + packageName)
        }
    }
    override val schema = BarcodeSchema.APP
    override fun toFormattedText(): String = url
    override fun toBarcodeText(): String = url
    val appPackage by unsafeLazy {
        url.removePrefixIgnoreCase(PREFIXES[0])
    }
}