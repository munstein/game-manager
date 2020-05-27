package com.munstein.gamemanager.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.dialog.IDialogBuilder
import com.munstein.gamemanager.dialog.MaterialDialogBuilder
import com.munstein.gamemanager.firebase.signin.FirebaseSignIn
import com.munstein.gamemanager.firebase.signin.IFirebaseSignIn
import com.munstein.gamemanager.interactor.games.GamesInteractor
import com.munstein.gamemanager.interactor.games.IGamesInteractor
import com.munstein.gamemanager.interactor.platform.IPlatformInteractor
import com.munstein.gamemanager.interactor.platform.PlatformInteractor
import com.munstein.gamemanager.interactor.user.IUserInteractor
import com.munstein.gamemanager.interactor.user.UserInteractor
import com.munstein.gamemanager.repository.games.GamesRepository
import com.munstein.gamemanager.repository.games.IGamesRepository
import com.munstein.gamemanager.repository.platform.IPlatformRepository
import com.munstein.gamemanager.repository.platform.PlatformRepository
import com.munstein.gamemanager.repository.user.IUserRepository
import com.munstein.gamemanager.repository.user.UserRepository
import com.munstein.gamemanager.viewmodels.HomeViewModel
import com.munstein.gamemanager.viewmodels.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

@Suppress("USELESS_CAST")
object KoinModules {

    val firebaseModule: Module = module(override = true) {
        single { FirebaseSignIn(androidContext()) as IFirebaseSignIn }
        single { FirebaseFirestore.getInstance() }
        single { FirebaseDatabase.getInstance().reference }
    }

    val viewModelModule: Module = module(override = true) {
        viewModel { LoginViewModel(get(), get()) }
        viewModel { HomeViewModel(get(), get(), get()) }
    }

    val interactorModule: Module = module(override = true) {
        single { PlatformInteractor(get()) as IPlatformInteractor }
        single { UserInteractor(get()) as IUserInteractor }
        single { GamesInteractor(get()) as IGamesInteractor }
    }

    val repositoryModule: Module = module(override = true) {
        single { UserRepository() as IUserRepository }
        single { PlatformRepository(get(), get()) as IPlatformRepository }
        single { GamesRepository(get(), get()) as IGamesRepository }
    }

    val utilModule: Module = module(override = true) {
        single { MaterialDialogBuilder() as IDialogBuilder }
    }
}