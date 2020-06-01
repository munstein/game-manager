package com.munstein.gamemanager.ui.mygames

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.munstein.gamemanager.model.Games

class MyGamesFragmentAdapter(activity: AppCompatActivity,
                             private val itemsCount: Int,
                             private val games: Games) :
        FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        val gamesList =
                when (position) {
                    0 -> {
                        games.have
                    }
                    1 -> {
                        games.playing
                    }
                    2 -> {
                        games.want
                    }
                    else -> {
                        arrayListOf()
                    }
                }
        return MyGamesFragment.newInstance(gamesList)
    }
}
