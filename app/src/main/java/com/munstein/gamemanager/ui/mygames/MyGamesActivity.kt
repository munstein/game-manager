package com.munstein.gamemanager.ui.mygames

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.ResourceState
import com.munstein.gamemanager.dialog.IDialogBuilder
import com.munstein.gamemanager.entity.Games
import com.munstein.gamemanager.entity.Platform
import com.munstein.gamemanager.viewmodels.MyGamesViewModel
import kotlinx.android.synthetic.main.activity_my_games.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyGamesActivity : AppCompatActivity() {

    private val platform by lazy { intent.getParcelableExtra(PLATFORM_EXTRA) as Platform }
    private val viewModel: MyGamesViewModel by viewModel()
    private val dialogBuilder: IDialogBuilder by inject()

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
        setupObservers()
        loadGames()
        setupEvents()
    }

    private fun setupEvents() {
        my_games_fab.setOnClickListener {
            showAddPlatformDialog()
        }
    }

    private fun setupUI() {
        my_games_txt_title.text = platform.name
        setupViewPager(Games())
        setupTabs()
    }

    private fun setupViewPager(games: Games) {
        val adapter = MyGamesFragmentAdapter(this, games, ::removeGame)
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
                1 -> getString(R.string.playing)
                2 -> getString(R.string.want)
                else -> getString(R.string.wtf)
            }
        }.attach()
    }

    private fun setupTabsTextColors() {
        my_games_tablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent))
        my_games_tablayout.setTabTextColors(ContextCompat.getColor(this, R.color.normalTabTextColor), ContextCompat.getColor(this, R.color.selectedTabTextColor))
    }

    private fun setupObservers() {
        viewModel.games.observe(this, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    hideLoadingShowViewPager()
                    displayGames(it.data)
                }
                ResourceState.ERROR -> {
                    hideLoadingShowViewPager()
                    showOnLoadGamesError()
                }
                ResourceState.LOADING -> {
                    showLoadingHideViewPager()
                }
            }
        })
        viewModel.addGame.observe(this, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    loadGames()
                }
                ResourceState.ERROR -> {
                    hideLoadingShowViewPager()
                }
                ResourceState.LOADING -> {
                    showLoadingHideViewPager()
                }
            }
        })
        viewModel.deleteGame.observe(this, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    hideLoadingShowViewPager()
                }
                ResourceState.ERROR -> {
                    hideLoadingShowViewPager()
                }
                ResourceState.LOADING -> {
                    showLoadingHideViewPager()
                }
            }
        })
    }

    private fun displayGames(data: Games?) {
        data?.let {
            setupViewPager(it)
        }
    }

    private fun hideLoadingShowViewPager() {
        my_games_progress.hide()
        my_games_viewpager.visibility = View.VISIBLE
    }

    private fun showLoadingHideViewPager() {
        my_games_progress.show()
        my_games_viewpager.visibility = View.GONE
    }

    private fun showOnLoadGamesError() {
    }

    private fun removeGame(gameTitle: String, gameColumnEnum: GameColumnEnum) {
        GlobalScope.launch {
            viewModel.removeGame(platform.name, gameTitle, gameColumnEnum)
        }
    }

    private fun addGame(gameTitle: String, gameColumnEnum: GameColumnEnum) {
        GlobalScope.launch {
            viewModel.addGame(gameTitle, platform.name, gameColumnEnum)
        }
    }

    private fun showAddPlatformDialog() {
        dialogBuilder.displayTextInputDialog(this,
                R.string.dialog_add_game,
                R.string.dialog_add_game,
                R.string.ok,
                R.string.cancel) { input ->
            addGame(input, GameColumnEnum.fromInt(my_games_tablayout.selectedTabPosition))
        }
    }

    private fun loadGames() {
        GlobalScope.launch {
            viewModel.getGames(platform.name)
        }
    }
}