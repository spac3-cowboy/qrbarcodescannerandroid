package com.innova.qrcodescanner.plus.model.schema
import com.innova.qrcodescanner.plus.extension.startsWithAnyIgnoreCase
data class GoogleMaps(val url: String) : Schema {
    companion object {
        private val PREFIXES = listOf("http://maps.google.com/", "https://maps.google.com/")
        fun parse(text: String): GoogleMaps? {
            if (text.startsWithAnyIgnoreCase(PREFIXES).not()) {
                return null
            }
            return GoogleMaps(text)
        }
    }
    override val schema = BarcodeSchema.GOOGLE_MAPS
    override fun toFormattedText(): String = url
    override fun toBarcodeText(): String = url
}