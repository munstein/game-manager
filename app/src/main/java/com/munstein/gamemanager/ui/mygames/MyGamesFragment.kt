package com.munstein.gamemanager.ui.mygames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.munstein.gamemanager.R

/**
 * A placeholder fragment containing a simple view.
 */
class MyGamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_games, container, false)
    }

    companion object{
        const val GAMES_ARG = "GAMES_ARG"

        fun newInstance(list: ArrayList<String>): MyGamesFragment? {
            val myFragment = MyGamesFragment()
            val args = Bundle()
            args.putStringArrayList(GAMES_ARG, list)
            myFragment.arguments = args
            return myFragment
        }
    }
}
