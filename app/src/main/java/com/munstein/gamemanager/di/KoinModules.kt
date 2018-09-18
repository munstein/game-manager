package com.munstein.gamemanager.di

import com.munstein.gamemanager.firebase.FirebaseLogin
import com.munstein.gamemanager.firebase.IFirebaseSignIn
import com.munstein.gamemanager.viewmodels.LoginViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object KoinModules {

    val firebaseLoginModule: Module = module {
        single { FirebaseLogin(get()) as IFirebaseSignIn }
    }

    val viewModelModule : Module = module {
        viewModel { LoginViewModel() }
    }
}