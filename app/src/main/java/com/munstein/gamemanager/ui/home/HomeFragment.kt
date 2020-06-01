package com.munstein.gamemanager.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseFragment
import com.munstein.gamemanager.base.ResourceState
import com.munstein.gamemanager.custom.HorizontalMarginDecoration
import com.munstein.gamemanager.custom.VerticalMarginDecorator
import com.munstein.gamemanager.dialog.IDialogBuilder
import com.munstein.gamemanager.entity.Platform
import com.munstein.gamemanager.ui.login.LoginActivity
import com.munstein.gamemanager.ui.mygames.MyGamesActivity
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

    private fun init() {
        initEvents()
        initUI()
        initObservables()
        hideEmptyState()
        getPlatforms()
    }

    private fun initEvents() {
        (activity as HomeActivity).setOnFabClickListener {
            showAddPlatformDialog()
        }
        (activity as HomeActivity).setOnLogoutOptionSelected {
            homeViewModel.logout()
        }
    }

    private fun initUI() {
        setupRecyclerView()
    }

    private fun initObservables() {
        homeViewModel.insert.observe(this, Observer {
            when (it.status) {
                ResourceState.LOADING -> {
                    showLoadingHideRecyclerView()
                }
                ResourceState.SUCCESS -> {
                    getPlatforms()
                }
                ResourceState.ERROR -> {
                    hideLoadingShowRecyclerView()
                    it.error?.message?.let { message ->
                        showOnErrorDialog(message)
                    }
                }
            }
        })

        homeViewModel.remove.observe(this, Observer {
            when (it.status) {
                ResourceState.LOADING -> {
                    showLoadingHideRecyclerView()
                }
                ResourceState.SUCCESS -> {
                    getPlatforms()
                }
                ResourceState.ERROR -> {
                    hideLoadingShowRecyclerView()
                }
            }
        })

        homeViewModel.platforms.observe(this, Observer {
            when (it.status) {
                ResourceState.LOADING -> {
                    showLoadingHideRecyclerView()
                }
                ResourceState.SUCCESS -> {
                    displayPlatforms(it.data ?: listOf())
                    hideLoadingShowRecyclerView()
                }
                ResourceState.ERROR -> {
                    hideLoadingShowRecyclerView()
                }
            }
        })

        homeViewModel.logout.observe(this, Observer {
            navigateToLoginActivity()
        })
    }

    private fun showAddPlatformDialog() {
        context?.let {
            dialogBuilder.displayTextInputDialog(it,
                    R.string.dialog_add_platform,
                    R.string.dialog_add_platform,
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

    private fun showOnErrorDialog(message: String) {
        context?.let {
            dialogBuilder.displayErrorDialog(it,
                    message,
                    R.string.ok)
        }
    }

    private fun displayPlatforms(platforms: List<Platform>) {
        if (platforms.isEmpty()) {
            showEmptyState()
        } else {
            setupAdapter(platforms)
            hideEmptyState()
        }
    }

    private fun getPlatforms() {
        GlobalScope.launch {
            homeViewModel.getPlatforms()
        }
    }

    private fun showLoadingHideRecyclerView() {
        fragment_home_progress.show()
        fragment_home_recycler.visibility = View.GONE
    }

    private fun hideLoadingShowRecyclerView() {
        fragment_home_progress.hide()
        fragment_home_recycler.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        val recyclerMargin = resources.getDimension(R.dimen.app_margin).toInt()
        fragment_home_recycler.layoutManager = LinearLayoutManager(context!!)
        fragment_home_recycler.addItemDecoration(HorizontalMarginDecoration(recyclerMargin))
        fragment_home_recycler.addItemDecoration(VerticalMarginDecorator(recyclerMargin))
    }

    private fun setupAdapter(platforms: List<Platform>) {
        val adapter = PlatformsAdapter(platforms, ::navigateToMyGamesActivity, ::showRemovePlatformDialog)
        fragment_home_recycler.adapter = adapter
    }

    private fun navigateToMyGamesActivity(platform: Platform) {
        context?.let {
            val intent = Intent(it, MyGamesActivity::class.java)
            intent.putExtra(MyGamesActivity.PLATFORM_EXTRA, platform)
            startActivity(intent)
        }
    }

    private fun navigateToLoginActivity() {
        context?.let {
            val intent = Intent(it, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
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
