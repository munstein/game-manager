package com.munstein.gamemanager.viewmodels

import android.content.Intent
import com.google.firebase.auth.FirebaseUser
import com.munstein.gamemanager.base.BaseViewModel
import com.munstein.gamemanager.firebase.IFirebaseSignIn

class LoginViewModel(private val firebaseSignIn: IFirebaseSignIn) : BaseViewModel() {

    fun getSignInIntent() : Intent {
        return firebaseSignIn.buildSignInIntent()
    }

    fun logout() {
        firebaseSignIn.logout()
    }

    fun getSignedInAccount() : FirebaseUser?{
        return firebaseSignIn.getSignedInAccount()
    }

}