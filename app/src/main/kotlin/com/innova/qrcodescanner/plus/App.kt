package com.innova.qrcodescanner.plus
import androidx.multidex.MultiDexApplication
import com.innova.qrcodescanner.plus.di.settings
class App : MultiDexApplication() {
    override fun onCreate() {
        applyTheme()
        super.onCreate()
    }
    private fun applyTheme() {
        settings.reapplyTheme()
    }
}