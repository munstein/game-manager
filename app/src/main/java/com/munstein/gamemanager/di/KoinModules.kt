package com.munstein.gamemanager.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.firebase.firestore.FirestoreHome
import com.munstein.gamemanager.firebase.signin.FirebaseSignIn
import com.munstein.gamemanager.interactor.platform.IPlatformInteractor
import com.munstein.gamemanager.interactor.platform.PlatformInteractor
import com.munstein.gamemanager.repository.platform.IPlatformRepository
import com.munstein.gamemanager.repository.platform.PlatformRepository
import com.munstein.gamemanager.viewmodels.HomeViewModel
import com.munstein.gamemanager.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object KoinModules {

    val firebaseModule: Module = module {
        single { FirebaseSignIn(get()) }
        single { FirestoreHome(get()) }
        single { FirebaseFirestore.getInstance() }
    }

    val viewModelModule: Module = module {
        viewModel { LoginViewModel(get()) }
        viewModel { HomeViewModel(get()) }
    }

    val interactor: Module = module {
        single { PlatformInteractor(get()) as IPlatformInteractor }
    }

    val repository: Module = module {
        single { PlatformRepository() as IPlatformRepository }
    }
}