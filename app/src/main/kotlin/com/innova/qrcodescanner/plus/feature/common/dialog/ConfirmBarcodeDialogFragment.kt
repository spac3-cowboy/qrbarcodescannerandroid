package com.innova.qrcodescanner.plus.feature.common.dialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.innova.qrcodescanner.plus.R
import com.innova.qrcodescanner.plus.extension.toStringId
import com.innova.qrcodescanner.plus.model.Barcode
class ConfirmBarcodeDialogFragment : DialogFragment() {
    interface Listener {
        fun onBarcodeConfirmed(barcode: Barcode)
        fun onBarcodeDeclined()
    }
    companion object {
        private const val BARCODE_KEY = "BARCODE_FORMAT_MESSAGE_ID_KEY"
        fun newInstance(barcode: Barcode): ConfirmBarcodeDialogFragment {
            return ConfirmBarcodeDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(BARCODE_KEY, barcode)
                }
                isCancelable = false
            }
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = parentFragment as? Listener
        val barcode = arguments?.getSerializable(BARCODE_KEY) as? Barcode ?: throw IllegalArgumentException("No barcode passed")
        val messageId = barcode.format.toStringId()
        val dialog = AlertDialog.Builder(requireActivity())
            .setTitle(R.string.confirm)
            .setMessage(messageId)
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                listener?.onBarcodeConfirmed(barcode)
            }
            .setNegativeButton(android.R.string.ok) { _, _ ->
                listener?.onBarcodeDeclined()
            }
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGoogleRed))
        }
        return dialog
    }
}