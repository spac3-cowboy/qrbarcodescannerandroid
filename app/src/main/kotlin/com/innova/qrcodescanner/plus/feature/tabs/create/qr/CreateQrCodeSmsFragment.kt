package com.innova.qrcodescanner.plus.feature.tabs.create.qr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.innova.qrcodescanner.plus.databinding.FragmentCreateQrCodeSmsBinding
import com.innova.qrcodescanner.plus.extension.isNotBlank
import com.innova.qrcodescanner.plus.extension.textString
import com.innova.qrcodescanner.plus.feature.tabs.create.BaseCreateBarcodeFragment
import com.innova.qrcodescanner.plus.model.schema.Schema
import com.innova.qrcodescanner.plus.model.schema.Sms
class CreateQrCodeSmsFragment : BaseCreateBarcodeFragment() {
    private lateinit var _binding: FragmentCreateQrCodeSmsBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCreateQrCodeSmsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTitleEditText()
        handleTextChanged()
    }
    override fun showPhone(phone: String) {
        binding.editTextPhone.apply {
            setText(phone)
            setSelection(phone.length)
        }
    }
    override fun getBarcodeSchema(): Schema {
       return Sms(
           phone = binding.editTextPhone.textString,
           message = binding.editTextMessage.textString
       )
    }
    private fun initTitleEditText() {
        binding.editTextPhone.requestFocus()
    }
    private fun handleTextChanged() {
        binding.editTextPhone.addTextChangedListener { toggleCreateBarcodeButton() }
        binding.editTextMessage.addTextChangedListener { toggleCreateBarcodeButton() }
    }
    private fun toggleCreateBarcodeButton() {
        parentActivity.isCreateBarcodeButtonEnabled = binding.editTextPhone.isNotBlank() || binding.editTextMessage.isNotBlank()
    }
}