package com.munstein.gamemanager.interactor.games

import com.munstein.gamemanager.model.Games
import com.munstein.gamemanager.repository.games.IGamesRepository

class GamesInteractor(private val repository: IGamesRepository) : IGamesInteractor {
    override suspend fun getGames(platformTitle: String): Games {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWantGame(games: Games, gameTitle: String, platformTitle: String) {
        games.want.remove(gameTitle)
        setGames(platformTitle, games)
    }

    override suspend fun deletePlayingGame(games: Games, gameTitle: String, platformTitle: String) {
        games.playing.remove(gameTitle)
        setGames(platformTitle, games)
    }

    override suspend fun deleteHaveGame(games: Games, gameTitle: String, platformTitle: String) {
        games.have.remove(gameTitle)
        setGames(platformTitle, games)
    }

    override suspend fun addWantGame(games: Games, gameTitle: String, platformTitle: String) {
        games.want.add(gameTitle)
        setGames(platformTitle, games)
    }

    override suspend fun addPlayingGame(games: Games, gameTitle: String, platformTitle: String) {
        games.playing.add(gameTitle)
        setGames(platformTitle, games)
    }

    override suspend fun addHaveGame(games: Games, gameTitle: String, platformTitle: String) {
        games.have.add(gameTitle)
        setGames(platformTitle, games)
    }

    private suspend fun setGames(platformTitle: String, games: Games) {
        repository.setGames(platformTitle, games)
    }
}