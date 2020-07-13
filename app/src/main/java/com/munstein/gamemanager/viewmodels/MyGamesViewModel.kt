package com.munstein.gamemanager.viewmodels

import androidx.lifecycle.MutableLiveData
import com.munstein.gamemanager.base.BaseViewModel
import com.munstein.gamemanager.base.Resource
import com.munstein.gamemanager.entity.Games
import com.munstein.gamemanager.interactor.games.IGamesInteractor
import com.munstein.gamemanager.ui.mygames.GameColumnEnum

class MyGamesViewModel(private val gamesInteractor: IGamesInteractor) : BaseViewModel() {

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

    suspend fun removeGame(gameTitle: String, platformTitle: String, column: GameColumnEnum) {
        try {
            deleteGame.postValue(Resource.loading())
            val games = games.value?.data ?: Games()
            when (column) {
                GameColumnEnum.HAVE -> {
                    gamesInteractor.deleteHaveGame(games, gameTitle, platformTitle)
                }

                GameColumnEnum.WANT -> {
                    gamesInteractor.deleteWantGame(games, gameTitle, platformTitle)
                }

                GameColumnEnum.PLAYING -> {
                    gamesInteractor.deletePlayingGame(games, gameTitle, platformTitle)
                }
            }
            deleteGame.postValue(Resource.success(Unit))
        } catch (x: Exception) {
            deleteGame.postValue(Resource.error(x))
        }
    }

    suspend fun addGame(gameTitle: String, platformTitle: String, column: GameColumnEnum) {
        try {
            addGame.postValue(Resource.loading())
            val games = games.value?.data ?: Games()
            when (column) {
                GameColumnEnum.HAVE -> {
                    gamesInteractor.addHaveGame(games, gameTitle, platformTitle)
                }

                GameColumnEnum.WANT -> {
                    gamesInteractor.addWantGame(games, gameTitle, platformTitle)
                }

                GameColumnEnum.PLAYING -> {
                    gamesInteractor.addPlayingGame(games, gameTitle, platformTitle)
                }
            }
            addGame.postValue(Resource.success(Unit))
        } catch (x: Exception) {
            addGame.postValue(Resource.error(x))
        }
    }
}