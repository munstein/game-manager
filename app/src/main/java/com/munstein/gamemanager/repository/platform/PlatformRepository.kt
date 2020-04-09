package com.munstein.gamemanager.repository.platform

import com.munstein.gamemanager.model.Platform
import kotlinx.coroutines.Deferred

class PlatformRepository() : IPlatformRepository {

    val child = "platform"
    override suspend fun addPlatform(name: String): Deferred<Boolean>  {
        TODO("Not yet implemented")
    }

    override suspend fun removePlatform(name: String): Deferred<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlatforms(): Deferred<List<Platform>> {
        TODO("Not yet implemented")
    }

    suspend fun doSomeHeavyOperation() {
        // do some non blocking operation
    }
}