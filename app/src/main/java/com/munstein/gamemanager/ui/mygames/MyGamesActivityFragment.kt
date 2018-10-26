package com.munstein.gamemanager.ui.mygames

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.munstein.gamemanager.R

/**
 * A placeholder fragment containing a simple view.
 */
class MyGamesActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_games, container, false)
    }
}
