package com.munstein.gamemanager.base

import android.app.Application
import com.orhanobut.hawk.Hawk

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    fun setup() {
        Hawk.init(this).build()
    }

}