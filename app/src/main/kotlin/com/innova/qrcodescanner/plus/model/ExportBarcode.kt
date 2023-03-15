package com.innova.qrcodescanner.plus.model
import androidx.room.TypeConverters
import com.innova.qrcodescanner.plus.usecase.BarcodeDatabaseTypeConverter
import com.google.zxing.BarcodeFormat
@TypeConverters(BarcodeDatabaseTypeConverter::class)
data class ExportBarcode(
    val date: Long,
    val format: BarcodeFormat,
    val text: String
)