package com.munstein.gamemanager.ui.mygames

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.munstein.gamemanager.entity.Games
import com.munstein.gamemanager.exceptions.InvalidMyGamesFragmentAdapterIndexException

class MyGamesFragmentAdapter(
    activity: AppCompatActivity,
    private val games: Games,
    private val onItemRemoveAction: (String, GameColumnEnum) -> Unit
) :
        FragmentStateAdapter(activity) {

    private val itemsCount = 3

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        val myGamesFragmentData =
                when (position) {
                    GameColumnEnum.HAVE.value -> {
                        MyGamesFragmentData(GameColumnEnum.HAVE, games.have, object : MyGamesFragmentActions {
                            override fun onItemRemoveClick(title: String) {
                                onItemRemoveAction(title, GameColumnEnum.HAVE)
                            }
                        })
                    }
                    GameColumnEnum.PLAYING.value -> {
                        MyGamesFragmentData(GameColumnEnum.PLAYING, games.playing, object : MyGamesFragmentActions {
                            override fun onItemRemoveClick(title: String) {
                                onItemRemoveAction(title, GameColumnEnum.PLAYING)
                            }
                        })
                    }
                    GameColumnEnum.WANT.value -> {
                        MyGamesFragmentData(GameColumnEnum.WANT, games.want, object : MyGamesFragmentActions {
                            override fun onItemRemoveClick(title: String) {
                                onItemRemoveAction(title, GameColumnEnum.WANT)
                            }
                        })
                    }
                    else -> {
                        throw InvalidMyGamesFragmentAdapterIndexException()
                    }
                }
        return MyGamesFragment.newInstance(myGamesFragmentData)
    }
}
