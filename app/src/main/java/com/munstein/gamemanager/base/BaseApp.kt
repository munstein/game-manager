package com.munstein.gamemanager.base

import android.app.Application
import com.google.firebase.FirebaseApp
import com.munstein.gamemanager.di.KoinModules
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.android.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    fun setup() {
        firebase()
        Hawk.init(this).build()
        koin()
    }

    fun koin() {
        startKoin(this, listOf(
                KoinModules.firebaseModule,
                KoinModules.viewModelModule,
                KoinModules.interactorModule,
                KoinModules.repositoryModule
        ))
    }

    fun firebase() {
        FirebaseApp.initializeApp(this)
    }
}