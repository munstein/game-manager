package com.munstein.gamemanager.repository.games

import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.model.Games
import com.munstein.gamemanager.repository.user.IUserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class GamesRepository(private val databaseReference: FirebaseFirestore, private val userRepository: IUserRepository) : IGamesRepository {

    val gamesCollection = "games"
    val collection = "user_data"

    override suspend fun getGames(platform: String): Deferred<Games> = withContext(Dispatchers.IO) {
        async {
            val x = databaseReference.collection(collection)
                    .document(userRepository.getUser().id)
                    .collection(gamesCollection)
                    .document(platform).get().await()
            return@async x.toObject(Games::class.java)!!
        }
    }

    override suspend fun setGames(platform: String, games: Games) {
        withContext(Dispatchers.IO) {
            launch {
                databaseReference.collection(collection).document(userRepository.getUser().id).collection(gamesCollection).document(platform).set(games).await()
            }
        }
    }

}