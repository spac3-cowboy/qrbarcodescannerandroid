package com.innova.qrcodescanner.plus.extension
import com.innova.qrcodescanner.plus.model.Barcode
import com.google.zxing.Result
fun Result.equalTo(barcode: Barcode?): Boolean {
    return barcodeFormat == barcode?.format && text == barcode?.text
}