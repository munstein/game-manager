package com.munstein.gamemanager.viewmodels

import androidx.lifecycle.MutableLiveData
import com.munstein.gamemanager.base.CoroutineViewModel
import com.munstein.gamemanager.interactor.platform.IPlatformInteractor
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val platformInteractor: IPlatformInteractor) : CoroutineViewModel() {

    val isLoading by lazy { MutableLiveData<Boolean>() }

    suspend fun insertPlatform(name: String) {
        jobs add launch {
            try {
                platformInteractor.addPlatform(name)
                isLoading.postValue(true)
            } catch (x: Exception) {
                val xx = 0
            } finally {

            }
        }
    }

}