package com.munstein.gamemanager.ui.mygames

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.munstein.gamemanager.R
import com.munstein.gamemanager.model.Platform

import kotlinx.android.synthetic.main.activity_my_games.*

class MyGamesActivity : AppCompatActivity() {

    private val platform by lazy { intent.getParcelableExtra(PLATFORM_EXTRA) as Platform }

    companion object{
        const val PLATFORM_EXTRA = "PLATFORM_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_games)
    }
}
