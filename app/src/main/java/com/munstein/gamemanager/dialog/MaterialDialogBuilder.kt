package com.munstein.gamemanager.dialog

import android.content.Context
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.munstein.gamemanager.R

class MaterialDialogBuilder : IDialogBuilder {
    override fun displayTextInputDialog(context: Context, title: Int, message: Int, positive: Int, negative: Int, onConfirmCallback: (text: String) -> Unit) {
        MaterialDialog(context)
                .title(R.string.dialog_add_platform)
                .positiveButton(R.string.ok)
                .negativeButton(R.string.cancel)
                .input { _, text ->
                    onConfirmCallback(text.toString())
                }.show()
    }

    override fun displayConfirmationDialog(context: Context, title: Int, message: Int, positive: Int, negative: Int, onConfirmCallback: () -> Unit?) {
        MaterialDialog(context)
                .title(title)
                .positiveButton(R.string.ok)
                .negativeButton(R.string.cancel)
                .positiveButton {
                    onConfirmCallback()
                }.show()
    }

    override fun displayErrorDialog(context: Context, title: String, @StringRes positive: Int) {
        MaterialDialog(context)
                .title(text = title)
                .positiveButton(R.string.ok)
                .show()
    }
}