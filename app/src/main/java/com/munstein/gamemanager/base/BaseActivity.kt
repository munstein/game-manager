package com.munstein.gamemanager.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}