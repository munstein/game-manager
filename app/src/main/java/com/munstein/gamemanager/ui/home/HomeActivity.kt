package com.munstein.gamemanager.ui.home

import android.os.Bundle
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        home_fab_add.setOnClickListener { view ->

        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
