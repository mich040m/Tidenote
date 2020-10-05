package tideware.app.tidenote.ui

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_about.view.*
import tideware.app.tidenote.R

class AboutDialog: DialogFragment() {
    private lateinit var dialogView: View
    private lateinit var alertDialog: AlertDialog


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            dialogView = inflater.inflate(R.layout.dialog_about, null)
            builder.setView(dialogView)
            setDialogData()
            setOnClickListeners()
            alertDialog = builder.create()
            alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            return alertDialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setDialogData() {
        dialogView.dialog_simple_title.text = resources.getText(R.string.about_text)
        dialogView.dialog_simple_description.text = resources.getText(R.string.about_content_text)
    }

    private fun setOnClickListeners(){
        dialogView.dialog_simple_close.setOnClickListener {
            dialog?.dismiss()
        }
        dialogView.dialog_simple_done.setOnClickListener {
            dialog?.dismiss()
        }
    }
}