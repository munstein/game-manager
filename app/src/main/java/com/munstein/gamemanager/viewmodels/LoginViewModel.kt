package com.munstein.gamemanager.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.munstein.gamemanager.base.BaseViewModel

class LoginViewModel : BaseViewModel(){

    private val loginComplete by lazy {
        MutableLiveData<Boolean>()
    }

    fun login(){

    }

}