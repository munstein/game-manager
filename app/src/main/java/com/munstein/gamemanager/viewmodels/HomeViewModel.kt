package com.munstein.gamemanager.viewmodels

import androidx.lifecycle.MutableLiveData
import com.munstein.gamemanager.base.CoroutineViewModel
import com.munstein.gamemanager.base.Resource
import com.munstein.gamemanager.interactor.platform.IPlatformInteractor
import com.munstein.gamemanager.model.Platform
import kotlinx.coroutines.launch

class HomeViewModel(private val platformInteractor: IPlatformInteractor) : CoroutineViewModel() {

    val insert by lazy { MutableLiveData<Resource<Unit>>() }
    val remove by lazy { MutableLiveData<Resource<Unit>>() }
    val platforms by lazy { MutableLiveData<Resource<List<Platform>>>() }

    suspend fun insertPlatform(name: String) {
        jobs add launch {
            try {
                insert.postValue(Resource.loading())
                platformInteractor.addPlatform(name)
                insert.postValue(Resource.success(Unit))
            } catch (x: Exception) {
                insert.postValue(Resource.error(x))
            }
        }
    }

    suspend fun removePlatform(name: String) {
        jobs add launch {
            try {
                remove.postValue(Resource.loading())
                platformInteractor.removePlatform(name)
                remove.postValue(Resource.success(Unit))
            } catch (x: Exception) {
                insert.postValue(Resource.error(x))
            }
        }
    }

    suspend fun getPlatforms() {
        jobs add launch {
            try {
                platforms.postValue(Resource.loading())
                val result = platformInteractor.getPlatforms().await()
                platforms.postValue(Resource.success(result))
            } catch (x: Exception) {
                platforms.postValue(Resource.error(x))
            }
        }
    }
}