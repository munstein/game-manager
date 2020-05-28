package com.munstein.gamemanager.repository.games

import com.munstein.gamemanager.datasource.FirestoreDataSource
import com.munstein.gamemanager.model.Games
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class GamesRepository(private val platformDataSource: FirestoreDataSource) : IGamesRepository {


    override suspend fun getGames(platform: String): Deferred<Games?> = withContext(Dispatchers.IO) {
        async {
            return@async platformDataSource.getCollection().document(platform).get().await().toObject(Games::class.java)
        }
    }

    override suspend fun setGames(platform: String, games: Games) {
        withContext(Dispatchers.IO) {
            launch {
                platformDataSource.getCollection().document(platform).set(games).await()
            }
        }
    }

}