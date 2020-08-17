package com.munstein.gamemanager.repository.platform

import com.munstein.gamemanager.entity.Platform
import kotlinx.coroutines.Deferred

interface IPlatformRepository {
    suspend fun addPlatform(name: String): Deferred<Void>
    suspend fun removePlatform(name: String): Deferred<Void>
    suspend fun getPlatforms(): Deferred<List<Platform>>
}