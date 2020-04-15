package com.munstein.gamemanager.interactor.platform

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.munstein.gamemanager.model.Platform
import com.munstein.gamemanager.repository.platform.IPlatformRepository
import kotlinx.coroutines.Deferred

class PlatformInteractor(private val repository: IPlatformRepository) : IPlatformInteractor {
    override suspend fun addPlatform(name: String): Deferred<Void> {
        return repository.addPlatform(name)
    }

    override suspend fun removePlatform(name: String): Deferred<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlatforms(): Deferred<List<Platform>> {
        TODO("Not yet implemented")
    }

}