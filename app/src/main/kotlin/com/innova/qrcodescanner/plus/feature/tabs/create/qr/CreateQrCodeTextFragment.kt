package com.innova.qrcodescanner.plus.feature.tabs.create.qr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.innova.qrcodescanner.plus.databinding.FragmentCreateQrCodeTextBinding
import com.innova.qrcodescanner.plus.di.barcodeParser
import com.innova.qrcodescanner.plus.extension.isNotBlank
import com.innova.qrcodescanner.plus.extension.textString
import com.innova.qrcodescanner.plus.feature.tabs.create.BaseCreateBarcodeFragment
import com.innova.qrcodescanner.plus.model.schema.Schema
import com.google.zxing.BarcodeFormat
class CreateQrCodeTextFragment : BaseCreateBarcodeFragment() {
    private lateinit var _binding: FragmentCreateQrCodeTextBinding
    private val binding get() = _binding
    companion object {
        private const val DEFAULT_TEXT_KEY = "DEFAULT_TEXT_KEY"
        fun newInstance(defaultText: String): CreateQrCodeTextFragment {
            return CreateQrCodeTextFragment().apply {
                arguments = Bundle().apply {
                    putString(DEFAULT_TEXT_KEY, defaultText)
                }
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCreateQrCodeTextBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleTextChanged()
        initEditText()
    }
    override fun getBarcodeSchema(): Schema {
        return barcodeParser.parseSchema(BarcodeFormat.QR_CODE, binding.editText.textString)
    }
    private fun initEditText() {
        val defaultText = arguments?.getString(DEFAULT_TEXT_KEY).orEmpty()
        binding.editText.apply {
            setText(defaultText)
            setSelection(defaultText.length)
            requestFocus()
        }
    }
    private fun handleTextChanged() {
        binding.editText.addTextChangedListener {
            parentActivity.isCreateBarcodeButtonEnabled = binding.editText.isNotBlank()
        }
    }
}