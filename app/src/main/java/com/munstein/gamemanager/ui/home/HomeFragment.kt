package com.munstein.gamemanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseFragment
import com.munstein.gamemanager.base.ResourceState
import com.munstein.gamemanager.custom.HorizontalMarginDecoration
import com.munstein.gamemanager.custom.VerticalSpacing
import com.munstein.gamemanager.dialog.IDialogBuilder
import com.munstein.gamemanager.model.Platform
import com.munstein.gamemanager.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val dialogBuilder: IDialogBuilder by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun showAddPlatformDialog() {
        context?.let {
            dialogBuilder.displayTextInputDialog(it,
                    R.string.dialog_remove_platform,
                    R.string.dialog_remove_platform,
                    R.string.ok,
                    R.string.cancel) { input ->
                addPlatform(input)
            }
        }
    }

    private fun addPlatform(platform: String) {
        GlobalScope.launch {
            homeViewModel.insertPlatform(platform)
        }
    }

    private fun showRemovePlatformDialog(platform: Platform) {
        context?.let {
            dialogBuilder.displayConfirmationDialog(it,
                    R.string.dialog_remove_platform,
                    R.string.dialog_remove_platform,
                    R.string.ok,
                    R.string.cancel) {
                removePlatform(platform)
            }
        }
    }

    private fun init() {
        events()
        observables()
        getPlatforms()
    }

    private fun events() {
        (activity as HomeActivity).setOnFabClickListener {
            showAddPlatformDialog()
        }
    }


    private fun observables() {
        homeViewModel.insert.observe(this, Observer {
            when (it.status) {
                ResourceState.LOADING -> {
                    showLoading()
                }
                ResourceState.SUCCESS -> {
                    hideLoading()
                }
                ResourceState.ERROR -> {
                    hideLoading()
                }
            }
        })

        homeViewModel.remove.observe(this, Observer {
            when (it.status) {
                ResourceState.LOADING -> {
                    showLoading()
                }
                ResourceState.SUCCESS -> {
                    hideLoading()
                }
                ResourceState.ERROR -> {
                    hideLoading()
                }
            }
        })

        homeViewModel.platforms.observe(this, Observer {
            when (it.status) {
                ResourceState.LOADING -> {
                    showLoading()
                }
                ResourceState.SUCCESS -> {
                    displayPlatforms(it.data ?: listOf())
                    hideLoading()
                }
                ResourceState.ERROR -> {
                    hideLoading()
                }
            }
        })
    }

    private fun displayPlatforms(platforms: List<Platform>) {
        if (platforms.isEmpty()) {
            showEmptyState()
        } else {
            setupRecyclerView(platforms)
            hideEmptyState()
        }
    }


    private fun getPlatforms() {
        GlobalScope.launch {
            homeViewModel.getPlatforms()
        }
    }

    private fun showLoading() {
        fragment_home_progress.show()
    }

    private fun hideLoading() {
        fragment_home_progress.hide()
    }

    private fun setupRecyclerView(platforms: List<Platform>) {
        val recyclerMargin = resources.getDimension(R.dimen.app_margin).toInt()
        val adapter = PlatformsAdapter(platforms, ::navigateToGamesActivity, ::removePlatform)
        fragment_home_recycler.layoutManager = LinearLayoutManager(context!!)
        fragment_home_recycler.addItemDecoration(HorizontalMarginDecoration(recyclerMargin))
        fragment_home_recycler.addItemDecoration(VerticalSpacing(recyclerMargin))
        fragment_home_recycler.adapter = adapter
    }

    private fun navigateToGamesActivity(platform: Platform) {

    }

    private fun removePlatform(platform: Platform) {
        GlobalScope.launch {
            homeViewModel.removePlatform(platform.name)
        }
    }

    private fun showEmptyState() {
        fragment_home_txt_empty_state_message.visibility = View.VISIBLE
    }

    private fun hideEmptyState() {
        fragment_home_txt_empty_state_message.visibility = View.GONE
    }
}
