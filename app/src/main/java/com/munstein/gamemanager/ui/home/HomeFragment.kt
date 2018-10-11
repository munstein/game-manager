package com.munstein.gamemanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseFragment
import com.munstein.gamemanager.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.fragment_home.*

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
    }

    // TODO implement
    private fun showAddPlatformDialog() {
    }

    // TODO implement
    private fun showRemovePlatformDialog() {
    }

    // TODO implement
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
