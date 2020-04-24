package com.munstein.gamemanager.dialog

import android.content.Context
import androidx.annotation.StringRes

interface IDialogBuilder {
    fun displayTextInputDialog(context: Context,
                               @StringRes title: Int,
                               @StringRes message: Int,
                               @StringRes positive: Int,
                               @StringRes negative: Int,
                               onConfirmCallback: (text: String) -> Unit)

    fun displayConfirmationDialog(context: Context,
                                  @StringRes title: Int,
                                  @StringRes message: Int,
                                  @StringRes positive: Int,
                                  @StringRes negative: Int,
                                  onConfirmCallback: () -> Unit? = {})

    fun displayErrorDialog(context: Context,
                           title: String,
                           @StringRes positive: Int)
}