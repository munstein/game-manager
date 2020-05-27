package com.munstein.gamemanager.repository.games

import com.munstein.gamemanager.model.Games
import kotlinx.coroutines.Deferred

interface IGamesRepository {
    suspend fun getGames(platform: String): Deferred<Games>
    suspend fun setGames(platform : String, games: Games)
}