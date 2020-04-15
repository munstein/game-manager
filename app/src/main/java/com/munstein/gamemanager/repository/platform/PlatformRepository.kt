package com.munstein.gamemanager.repository.platform

import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.model.Platform
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PlatformRepository(private val databaseReference: FirebaseFirestore) : IPlatformRepository {

    val child = "platform"
    val collection = "user_data"

    override suspend fun addPlatform(name: String): Deferred<Void> = withContext(IO) {
        async {
            return@async databaseReference.collection(collection).document(child).set(Platform(name)).await()
        }
    }

    override suspend fun removePlatform(name: String): Deferred<Void> = withContext(IO){
        async {
            return@async databaseReference.collection(collection).document(name).delete().await()
        }
    }

    override suspend fun getPlatforms(): Deferred<List<Platform>> = withContext(IO) {
        TODO("Not yet implemented")
    }
}