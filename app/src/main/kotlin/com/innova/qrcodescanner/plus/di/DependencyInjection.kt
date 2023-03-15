package com.innova.qrcodescanner.plus.di
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.innova.qrcodescanner.plus.App
import com.innova.qrcodescanner.plus.usecase.BarcodeImageScanner
import com.innova.qrcodescanner.plus.usecase.BarcodeSaver
import com.innova.qrcodescanner.plus.usecase.BarcodeImageGenerator
import com.innova.qrcodescanner.plus.usecase.BarcodeImageSaver
import com.innova.qrcodescanner.plus.usecase.BarcodeParser
import com.innova.qrcodescanner.plus.usecase.WifiConnector
import com.innova.qrcodescanner.plus.usecase.OTPGenerator
import com.innova.qrcodescanner.plus.usecase.Settings
import com.innova.qrcodescanner.plus.usecase.BarcodeDatabase
import com.innova.qrcodescanner.plus.usecase.ContactHelper
import com.innova.qrcodescanner.plus.usecase.PermissionsHelper
import com.innova.qrcodescanner.plus.usecase.RotationHelper
import com.innova.qrcodescanner.plus.usecase.ScannerCameraHelper
val App.settings get() = Settings.getInstance(applicationContext)
val barcodeParser get() = BarcodeParser
val barcodeImageScanner get() = BarcodeImageScanner
val barcodeImageGenerator get() = BarcodeImageGenerator
val barcodeSaver get() = BarcodeSaver
val barcodeImageSaver get() = BarcodeImageSaver
val wifiConnector get() = WifiConnector
val otpGenerator get() = OTPGenerator
val AppCompatActivity.barcodeDatabase get() = BarcodeDatabase.getInstance(this)
val AppCompatActivity.settings get() = Settings.getInstance(this)
val contactHelper get() = ContactHelper
val permissionsHelper get() = PermissionsHelper
val rotationHelper get() = RotationHelper
val scannerCameraHelper get() = ScannerCameraHelper
val Fragment.barcodeParser get() = BarcodeParser
val Fragment.barcodeDatabase get() = BarcodeDatabase.getInstance(requireContext())
val Fragment.settings get() = Settings.getInstance(requireContext())
val Fragment.permissionsHelper get() = PermissionsHelper