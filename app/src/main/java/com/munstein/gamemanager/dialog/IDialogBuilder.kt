package com.munstein.gamemanager.dialog

interface IDialogBuilder {
    fun buildTextInputDialog(title : String ,message: String, onConfirmCallback : (text : String) -> Unit)
    fun buildConfirmationDialog(title : String, message: String)
}