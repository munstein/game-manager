package com.munstein.gamemanager.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.firebase.firestore.FirestoreHome
import com.munstein.gamemanager.firebase.signin.FirebaseSignIn
import com.munstein.gamemanager.interactor.platform.IPlatformInteractor
import com.munstein.gamemanager.interactor.platform.PlatformInteractor
import com.munstein.gamemanager.repository.platform.IPlatformRepository
import com.munstein.gamemanager.repository.platform.PlatformRepository
import com.munstein.gamemanager.viewmodels.HomeViewModel
import com.munstein.gamemanager.viewmodels.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object KoinModules {

    val firebaseModule: Module = module(override = true) {
        single { FirebaseSignIn(androidContext()) }
        single { FirestoreHome(get()) }
        single { FirebaseFirestore.getInstance() }
        single { FirebaseDatabase.getInstance().reference }
    }

    val viewModelModule: Module = module {
        viewModel { LoginViewModel(FirebaseSignIn(androidContext())) }
        viewModel { HomeViewModel(get()) }
    }

    val interactorModule: Module = module {
        single { PlatformInteractor(get()) as IPlatformInteractor }
    }

    val repositoryModule: Module = module {
        single { PlatformRepository(get()) as IPlatformRepository }
    }
}