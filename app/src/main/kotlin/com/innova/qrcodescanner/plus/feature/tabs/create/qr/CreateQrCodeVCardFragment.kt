package com.innova.qrcodescanner.plus.feature.tabs.create.qr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.innova.qrcodescanner.plus.databinding.FragmentCreateQrCodeVcardBinding
import com.innova.qrcodescanner.plus.extension.textString
import com.innova.qrcodescanner.plus.feature.tabs.create.BaseCreateBarcodeFragment
import com.innova.qrcodescanner.plus.model.Contact
import com.innova.qrcodescanner.plus.model.schema.Schema
import com.innova.qrcodescanner.plus.model.schema.VCard
class CreateQrCodeVCardFragment : BaseCreateBarcodeFragment() {
    private lateinit var _binding: FragmentCreateQrCodeVcardBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding =  FragmentCreateQrCodeVcardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextFirstName.requestFocus()
        parentActivity.isCreateBarcodeButtonEnabled = true
    }
    override fun getBarcodeSchema(): Schema {
       return VCard(firstName = binding.editTextFirstName.textString, lastName = binding.editTextLastName.textString, organization = binding.editTextOrganization.textString, title = binding.editTextJob.textString, email = binding.editTextEmail.textString, phone = binding.editTextPhone.textString, secondaryPhone = binding.editTextFax.textString, url = binding.editTextWebsite.textString)
    }
    override fun showContact(contact: Contact) {
        binding.editTextFirstName.setText(contact.firstName)
        binding.editTextLastName.setText(contact.lastName)
        binding.editTextEmail.setText(contact.email)
        binding.editTextPhone.setText(contact.phone)
    }
}