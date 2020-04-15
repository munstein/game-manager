package com.munstein.gamemanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseFragment
import com.munstein.gamemanager.viewmodels.HomeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), PlatformViewHolder.OnHolderClick {

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
        GlobalScope.launch {
            homeViewModel.insertPlatform("texto")
        }
    }

    // TODO implement
    override fun onClick(platformTitle: String) {
    }

    private fun showAddPlatformDialog() {
        context?.let {
            MaterialDialog(it)
                    .title(R.string.dialog_add_platform)
                    .positiveButton(R.string.ok)
                    .negativeButton(R.string.cancel)
                    .input { _, text ->
                        addPlatform(text.toString())
                    }
                    .show()
        }
    }

    // TODO implement
    private fun addPlatform(platform: String) {
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
        ui()
        events()
    }

    // TODO implement
    private fun events() {
    }

    // TODO implement
    private fun ui() {
    }

    // TODO implement
    private fun observables() {
    }
}
