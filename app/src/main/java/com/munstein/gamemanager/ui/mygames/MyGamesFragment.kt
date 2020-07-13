package com.munstein.gamemanager.ui.mygames

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.munstein.gamemanager.R
import com.munstein.gamemanager.extensions.setLinearLayoutManagerAndMargins
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import kotlinx.android.synthetic.main.fragment_my_games.*

class MyGamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.get(MY_GAMES_FRAGMENT_DATA_ARG) as MyGamesFragmentData
        fragment_my_games_rv.setLinearLayoutManagerAndMargins()
        fragment_my_games_rv.adapter = GamesAdapter(data.games) {
            data.onItemRemoveClick.onItemRemoveClick(it)
        }
    }

    companion object {
        const val MY_GAMES_FRAGMENT_DATA_ARG = "MY_GAMES_FRAGMENT_DATA_ARG"

        fun newInstance(myGamesFragmentData: MyGamesFragmentData): Fragment {
            val bundle = Bundle()
            bundle.putParcelable(MY_GAMES_FRAGMENT_DATA_ARG, myGamesFragmentData)
            val fragment = MyGamesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}

@Parcelize
data class MyGamesFragmentData(val columnEnum: GameColumnEnum, val games: List<String>, val onItemRemoveClick: @RawValue MyGamesFragmentActions) : Parcelable

interface MyGamesFragmentActions {
    fun onItemRemoveClick(title: String)
}