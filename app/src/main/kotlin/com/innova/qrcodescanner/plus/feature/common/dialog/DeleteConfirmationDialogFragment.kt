package com.innova.qrcodescanner.plus.feature.common.dialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.innova.qrcodescanner.plus.R
import com.innova.qrcodescanner.plus.extension.orZero
class DeleteConfirmationDialogFragment : DialogFragment() {
    companion object {
        private const val MESSAGE_ID_KEY = "MESSAGE_ID_KEY"
        fun newInstance(messageId: Int): DeleteConfirmationDialogFragment {
            return DeleteConfirmationDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(MESSAGE_ID_KEY, messageId)
                }
                isCancelable = false
            }
        }
    }
    interface Listener {
        fun onDeleteConfirmed()
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = requireActivity() as? Listener ?: parentFragment as? Listener
        val messageId = arguments?.getInt(MESSAGE_ID_KEY).orZero()
        val dialog = AlertDialog.Builder(requireActivity())
            .setMessage(messageId)
            .setPositiveButton(R.string.delete) { _, _ -> listener?.onDeleteConfirmed() }
            .setNegativeButton(android.R.string.ok, null)
            .create()
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGoogleRed))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
        }
        return dialog
    }
}