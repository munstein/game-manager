package com.munstein.gamemanager.repository.platform

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.munstein.gamemanager.model.Platform
import kotlinx.coroutines.Deferred

interface IPlatformRepository{
    suspend fun addPlatform(name: String): Deferred<Void>
    suspend fun removePlatform(name: String): Deferred<Void>
    suspend fun getPlatforms() : Deferred<List<Platform>>
}