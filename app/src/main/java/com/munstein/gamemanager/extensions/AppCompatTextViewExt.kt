package com.munstein.gamemanager.extensions

import android.view.View
import androidx.appcompat.widget.AppCompatTextView

fun AppCompatTextView.visibleIf(result: Boolean) {
    if (result) {
        this.visibility = View.VISIBLE
    }
}