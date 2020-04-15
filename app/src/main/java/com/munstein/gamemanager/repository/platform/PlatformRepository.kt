package com.munstein.gamemanager.repository.platform

import com.google.firebase.database.DatabaseReference
import com.munstein.gamemanager.model.Platform
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PlatformRepository(private val databaseReference: DatabaseReference) : IPlatformRepository {

    val child = "platform"

    override suspend fun addPlatform(name: String): Deferred<Void>  = withContext(IO) {
        async {
            databaseReference.database.reference.child(child).setValue(name).await()
        }
    }

    override suspend fun removePlatform(name: String): Deferred<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlatforms(): Deferred<List<Platform>> {
        TODO("Not yet implemented")
    }
}