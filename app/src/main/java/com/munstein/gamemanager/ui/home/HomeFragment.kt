package com.munstein.gamemanager.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseFragment
import com.munstein.gamemanager.base.ResourceState
import com.munstein.gamemanager.model.Platform
import com.munstein.gamemanager.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModel()

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
            buildDialog(it).show()
        }
    }

    private fun buildDialog(context: Context): MaterialDialog {
        return MaterialDialog(context)
                .title(R.string.dialog_add_platform)
                .positiveButton(R.string.ok)
                .negativeButton(R.string.cancel)
                .input { _, text ->
                    addPlatform(text.toString())
                }
    }

    private fun addPlatform(platform: String) {
        GlobalScope.launch {
            homeViewModel.insertPlatform(platform)
        }
    }

    private fun showRemovePlatformDialog() {
        context?.let {
            MaterialDialog(it)
                    .title(R.string.dialog_remove_platform)
                    .positiveButton(R.string.ok) { }
                    .negativeButton(R.string.cancel)
                    .show()
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
                    hideLoading()
                }
                ResourceState.ERROR -> {
                    hideLoading()
                }
            }
        })
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
        val adapter = PlatformsAdapter(platforms, ::navigateToGamesActivity, ::removePlatform)
    }

    private fun navigateToGamesActivity(platform: Platform) {

    }

    private fun removePlatform(platform: Platform) {

    }
}
