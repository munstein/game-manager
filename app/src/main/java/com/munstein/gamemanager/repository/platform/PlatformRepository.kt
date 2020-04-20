package com.munstein.gamemanager.repository.platform

import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.model.Platform
import com.munstein.gamemanager.repository.user.IUserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PlatformRepository(private val databaseReference: FirebaseFirestore, private val userRepository: IUserRepository) : IPlatformRepository {

    val platformCollection = "platforms"
    val collection = "user_data"

    override suspend fun addPlatform(name: String): Deferred<Void> = withContext(IO) {
        async {
            return@async databaseReference.collection(collection).document(userRepository.getUser().id).collection(platformCollection).document(name).set(Platform(name = name)).await()
        }
    }

    override suspend fun removePlatform(name: String): Deferred<Void> = withContext(IO) {
        async {
            return@async databaseReference.collection(collection).document(userRepository.getUser().id).collection(platformCollection).document(name).delete().await()
        }
    }

    override suspend fun getPlatforms(): Deferred<List<Platform>> = withContext(IO) {
        async {
            return@async databaseReference.collection(collection).document(userRepository.getUser().id).collection(platformCollection).get().await().map {
                it.toObject(Platform::class.java)
            }
        }
    }
}