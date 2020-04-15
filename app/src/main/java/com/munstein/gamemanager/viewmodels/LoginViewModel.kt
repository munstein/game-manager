package com.munstein.gamemanager.viewmodels

import androidx.lifecycle.MutableLiveData
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.munstein.gamemanager.base.CoroutineViewModel
import com.munstein.gamemanager.firebase.signin.IFirebaseSignIn

class LoginViewModel(private val firebaseSignIn: IFirebaseSignIn) : CoroutineViewModel() {

    val userIsSignedIn by lazy {
        MutableLiveData<Boolean>()
    }

    fun getSignInIntent(): Intent {
        return firebaseSignIn.buildSignInIntent()
    }

    fun signIn(account: GoogleSignInAccount) {
        firebaseSignIn.signInToFirebaseWithGoogleAccount(account).addOnCompleteListener { task ->

            userIsSignedIn.postValue(task.isSuccessful)
        }
    }

    fun getSignedInAccount(): FirebaseUser? {
        return firebaseSignIn.getSignedInAccount()
    }
}