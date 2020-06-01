package com.munstein.gamemanager.ui.mygames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.munstein.gamemanager.R
import com.munstein.gamemanager.model.Games
import com.munstein.gamemanager.model.Platform
import com.munstein.gamemanager.viewmodels.MyGamesViewModel

import kotlinx.android.synthetic.main.activity_my_games.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyGamesActivity : AppCompatActivity() {

    private val platform by lazy { intent.getParcelableExtra(PLATFORM_EXTRA) as Platform }

    private val viewModel : MyGamesViewModel by viewModel()

    companion object {
        const val PLATFORM_EXTRA = "PLATFORM_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_games)
        init()
    }

    private fun init() {
        setupUI()
    }

    private fun setupUI() {
        my_games_txt_title.text = platform.name
        setupViewPager(Games())
        setupTabs()
    }

    private fun setupViewPager(games: Games) {
        val adapter = MyGamesFragmentAdapter(this, games)
        my_games_viewpager.adapter = adapter
    }

    private fun setupTabs() {
        setupTabsTitles()
        setupTabsTextColors()
    }

    private fun setupTabsTitles() {
        TabLayoutMediator(my_games_tablayout, my_games_viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.have)
                1 -> getString(R.string.want)
                2 -> getString(R.string.playing)
                else -> getString(R.string.wtf)
            }
        }.attach()
    }

    private fun setupTabsTextColors() {
        my_games_tablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent))
        my_games_tablayout.setTabTextColors(ContextCompat.getColor(this, R.color.normalTabTextColor), ContextCompat.getColor(this, R.color.selectedTabTextColor))
    }
}
