package com.innova.qrcodescanner.plus.feature.barcode.save
import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.innova.qrcodescanner.plus.R
import com.innova.qrcodescanner.plus.databinding.ActivitySaveBarcodeAsImageBinding
import com.innova.qrcodescanner.plus.di.barcodeImageGenerator
import com.innova.qrcodescanner.plus.di.barcodeImageSaver
import com.innova.qrcodescanner.plus.di.permissionsHelper
import com.innova.qrcodescanner.plus.extension.applySystemWindowInsets
import com.innova.qrcodescanner.plus.extension.showError
import com.innova.qrcodescanner.plus.extension.unsafeLazy
import com.innova.qrcodescanner.plus.feature.BaseActivity
import com.innova.qrcodescanner.plus.model.Barcode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class SaveBarcodeAsImageActivity : BaseActivity() {
    private lateinit var binding: ActivitySaveBarcodeAsImageBinding
    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 101
        private val PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        private const val BARCODE_KEY = "BARCODE_KEY"
        fun start(context: Context, barcode: Barcode) {
            val intent = Intent(context, SaveBarcodeAsImageActivity::class.java).apply {
                putExtra(BARCODE_KEY, barcode)
            }
            context.startActivity(intent)
        }
    }
    private val barcode by unsafeLazy {
        intent?.getSerializableExtra(BARCODE_KEY) as? Barcode ?: throw IllegalArgumentException("No barcode passed")
    }
    private val disposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBarcodeAsImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportEdgeToEdge()
        initToolbar()
        initFormatSpinner()
        initSaveButton()
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionsHelper.areAllPermissionsGranted(grantResults)) {
            saveBarcode()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
    private fun supportEdgeToEdge() {
        binding.rootView.applySystemWindowInsets(applyTop = true, applyBottom = true)
    }
    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    private fun initFormatSpinner() {
        binding.spinnerSaveAs.adapter = ArrayAdapter.createFromResource(this, R.array.activity_save_barcode_as_image_formats, R.layout.item_spinner).apply {
            setDropDownViewResource(R.layout.item_spinner_dropdown)
        }
    }
    private fun initSaveButton() {
        binding.buttonSave.setOnClickListener {
            requestPermissions()
        }
    }
    private fun requestPermissions() {
        permissionsHelper.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSIONS_CODE)
    }
    private fun saveBarcode() {
        val saveFunc = when (binding.spinnerSaveAs.selectedItemPosition) {
            0 -> {
                barcodeImageGenerator.generateBitmapAsync(barcode, 640, 640, 2).flatMapCompletable { barcodeImageSaver.savePngImageToPublicDirectory(this, it, barcode) }
            }
            1 -> {
                barcodeImageGenerator
                    .generateSvgAsync(barcode, 640, 640, 2)
                    .flatMapCompletable { barcodeImageSaver.saveSvgImageToPublicDirectory(this, it, barcode) }
            }
            else -> return
        }
        showLoading(true)
        saveFunc
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { showBarcodeSaved() },
                { error ->
                    showLoading(false)
                    showError(error)
                }
            ).addTo(disposable)
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBarLoading.isVisible = isLoading
        binding.scrollView.isVisible = isLoading.not()
    }
    private fun showBarcodeSaved() {
        Toast.makeText(this, R.string.activity_save_barcode_as_image_file_name_saved, Toast.LENGTH_LONG).show()
        finish()
    }
}