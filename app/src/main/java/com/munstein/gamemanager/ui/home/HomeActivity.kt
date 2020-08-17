package com.munstein.gamemanager.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private var onFabClick = {}

    private var onLogoutOptionSelected = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(home_bottom_bar)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                onLogoutOptionSelected.invoke()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        setupEvents()
    }

    private fun setupEvents() {
        home_fab_add.setOnClickListener {
            onFabClick.invoke()
        }
    }

    fun setOnFabClickListener(action: () -> Unit) {
        onFabClick = action
    }

    fun setOnLogoutOptionSelected(action: () -> Unit) {
        onLogoutOptionSelected = action
    }
}
