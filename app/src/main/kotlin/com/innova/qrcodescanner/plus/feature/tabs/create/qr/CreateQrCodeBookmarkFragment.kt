package com.innova.qrcodescanner.plus.feature.tabs.create.qr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.innova.qrcodescanner.plus.databinding.FragmentCreateQrCodeBookmarkBinding
import com.innova.qrcodescanner.plus.extension.isNotBlank
import com.innova.qrcodescanner.plus.extension.textString
import com.innova.qrcodescanner.plus.feature.tabs.create.BaseCreateBarcodeFragment
import com.innova.qrcodescanner.plus.model.schema.Bookmark
import com.innova.qrcodescanner.plus.model.schema.Schema
class CreateQrCodeBookmarkFragment : BaseCreateBarcodeFragment() {
    private lateinit var _binding: FragmentCreateQrCodeBookmarkBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCreateQrCodeBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitleEditText()
        handleTextChanged()
    }
    override fun getBarcodeSchema(): Schema {
        return Bookmark(
            title = binding.editTextTitle.textString,
            url = binding.editTextUrl.textString
        )
    }
    private fun initTitleEditText() {
        binding.editTextTitle.requestFocus()
    }
    private fun handleTextChanged() {
        binding.editTextTitle.addTextChangedListener { toggleCreateBarcodeButton() }
        binding.editTextUrl.addTextChangedListener { toggleCreateBarcodeButton() }
    }
    private fun toggleCreateBarcodeButton() {
        parentActivity.isCreateBarcodeButtonEnabled = binding.editTextTitle.isNotBlank() || binding.editTextUrl.isNotBlank()
    }
}