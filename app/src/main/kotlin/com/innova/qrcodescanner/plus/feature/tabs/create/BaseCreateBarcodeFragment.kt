package com.innova.qrcodescanner.plus.feature.tabs.create
import androidx.fragment.app.Fragment
import com.innova.qrcodescanner.plus.extension.unsafeLazy
import com.innova.qrcodescanner.plus.model.Contact
import com.innova.qrcodescanner.plus.model.schema.Other
import com.innova.qrcodescanner.plus.model.schema.Schema
abstract class BaseCreateBarcodeFragment : Fragment() {
    protected val parentActivity by unsafeLazy { requireActivity() as CreateBarcodeActivity }
    open val latitude: Double? = null
    open val longitude: Double? = null
    open fun getBarcodeSchema(): Schema = Other("")
    open fun showPhone(phone: String) {}
    open fun showContact(contact: Contact) {}
    open fun showLocation(latitude: Double?, longitude: Double?) {}
}