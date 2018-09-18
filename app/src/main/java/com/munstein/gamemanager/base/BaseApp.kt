package com.munstein.gamemanager.base

import android.app.Application
import com.munstein.gamemanager.di.KoinModules
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.android.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    fun setup() {
        Hawk.init(this).build()
        koin()
    }

    fun koin(){
        startKoin(this, listOf(KoinModules.firebaseLoginModule))
    }

}