package com.munstein.gamemanager.repository.platform

import com.munstein.gamemanager.datasource.FirestoreDataSource
import com.munstein.gamemanager.model.Platform
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PlatformRepository(private val platformDataSource: FirestoreDataSource) : IPlatformRepository {

    override suspend fun addPlatform(name: String): Deferred<Void> = withContext(IO) {
        async {
            return@async platformDataSource.getCollection().document(name).set(Platform(name = name)).await()
        }
    }

    override suspend fun removePlatform(name: String): Deferred<Void> = withContext(IO) {
        async {
            return@async platformDataSource.getCollection().document(name).delete().await()
        }
    }

    override suspend fun getPlatforms(): Deferred<List<Platform>> = withContext(IO) {
        async {
            return@async platformDataSource.getCollection().get().await().map {
                it.toObject(Platform::class.java)
            }
        }
    }
}