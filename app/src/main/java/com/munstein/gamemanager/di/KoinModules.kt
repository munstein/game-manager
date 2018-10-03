package com.munstein.gamemanager.di

import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.firebase.firestore.FirestoreHome
import com.munstein.gamemanager.firebase.firestore.IFirestoreHome
import com.munstein.gamemanager.firebase.signin.FirebaseSignIn
import com.munstein.gamemanager.firebase.signin.IFirebaseSignIn
import com.munstein.gamemanager.viewmodels.HomeViewModel
import com.munstein.gamemanager.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

object KoinModules {

    val firebaseModule : Module = module {
        single { FirebaseSignIn(get()) as IFirebaseSignIn }
        single { FirestoreHome() as IFirestoreHome}
        single { FirebaseFirestore.getInstance() }
    }

    val viewModelModule: Module = module {
        viewModel { LoginViewModel(get())}
        viewModel { HomeViewModel(get()) }
    }
}