package com.munstein.gamemanager.interactor.platform

import com.munstein.gamemanager.entity.Platform
import com.munstein.gamemanager.repository.platform.IPlatformRepository
import kotlinx.coroutines.Deferred

class PlatformInteractor(private val repository: IPlatformRepository) : IPlatformInteractor {
    override suspend fun addPlatform(name: String): Deferred<Void> {
        return repository.addPlatform(name)
    }

    override suspend fun removePlatform(name: String): Deferred<Void> {
        return repository.removePlatform(name)
    }

    override suspend fun getPlatforms(): Deferred<List<Platform>> {
        return repository.getPlatforms()
    }
}