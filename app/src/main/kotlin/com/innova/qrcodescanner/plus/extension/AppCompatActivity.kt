package com.innova.qrcodescanner.plus.extension
import androidx.appcompat.app.AppCompatActivity
import com.innova.qrcodescanner.plus.feature.common.dialog.ErrorDialogFragment
fun AppCompatActivity.showError(error: Throwable?) {
    val errorDialog = ErrorDialogFragment.newInstance(this, error)
    errorDialog.show(supportFragmentManager, "")
}