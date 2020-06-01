package com.munstein.gamemanager.viewmodels

import androidx.lifecycle.MutableLiveData
import com.munstein.gamemanager.base.BaseViewModel
import com.munstein.gamemanager.base.Resource
import com.munstein.gamemanager.interactor.games.GamesInteractor
import com.munstein.gamemanager.model.Games
import com.munstein.gamemanager.ui.mygames.GameColumn
import java.lang.Exception

class MyGamesViewModel(private val gamesInteractor: GamesInteractor) : BaseViewModel() {

    val games by lazy { MutableLiveData<Resource<Games>>() }
    val deleteGame by lazy { MutableLiveData<Resource<Unit>>() }
    val addGame by lazy { MutableLiveData<Resource<Unit>>() }

    suspend fun getGames(platformTitle: String) {
        try {
            games.postValue(Resource.loading())
            val gamesResult = gamesInteractor.getGames(platformTitle)
            games.postValue(Resource.success(gamesResult))
        } catch (x: Exception) {
            games.postValue(Resource.error(x))
        }
    }

    suspend fun removeGame(games: Games, gameTitle: String, platformTitle: String, column: GameColumn) {
        try {
            deleteGame.postValue(Resource.loading())
            when (column) {
                GameColumn.HAVE -> {
                    gamesInteractor.deleteHaveGame(games, gameTitle, platformTitle)
                }

                GameColumn.WANT -> {
                    gamesInteractor.deleteWantGame(games, gameTitle, platformTitle)
                }

                GameColumn.PLAYING -> {
                    gamesInteractor.deletePlayingGame(games, gameTitle, platformTitle)
                }
            }
            deleteGame.postValue(Resource.success(Unit))
        } catch (x: Exception) {
            deleteGame.postValue(Resource.error(x))
        }

    }

    suspend fun addGame(games: Games, gameTitle: String, platformTitle: String, column: GameColumn) {
        try {
            addGame.postValue(Resource.loading())
            when (column) {
                GameColumn.HAVE -> {
                    gamesInteractor.addHaveGame(games, gameTitle, platformTitle)
                }

                GameColumn.WANT -> {
                    gamesInteractor.addWantGame(games, gameTitle, platformTitle)
                }

                GameColumn.PLAYING -> {
                    gamesInteractor.addPlayingGame(games, gameTitle, platformTitle)
                }
            }
            addGame.postValue(Resource.success(Unit))
        } catch (x: Exception) {
            addGame.postValue(Resource.error(x))
        }
    }

}