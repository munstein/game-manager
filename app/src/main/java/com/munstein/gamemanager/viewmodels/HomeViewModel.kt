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
                isLoading.postValue(true)
                platformInteractor.addPlatform("xbox")
                isLoading.postValue(false)
            } catch (x: Exception) {
                val xx = 0
            } finally {

            }
        }
    }


}