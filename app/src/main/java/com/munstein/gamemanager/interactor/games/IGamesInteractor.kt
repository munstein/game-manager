package com.munstein.gamemanager.interactor.games

import com.munstein.gamemanager.model.Games

interface IGamesInteractor {
    suspend fun getGames(platformTitle: String): Games
    suspend fun deleteWantGame(games: Games, gameTitle: String, platformTitle: String)
    suspend fun deletePlayingGame(games: Games, gameTitle: String, platformTitle: String)
    suspend fun deleteHaveGame(games: Games, gameTitle: String, platformTitle: String)
    suspend fun addWantGame(games: Games, gameTitle: String, platformTitle: String)
    suspend fun addPlayingGame(games: Games, gameTitle: String, platformTitle: String)
    suspend fun addHaveGame(games: Games, gameTitle: String, platformTitle: String)
}