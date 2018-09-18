package com.munstein.gamemanager.ui.login

import android.os.Bundle
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseActivity

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
    }

}
