package com.munstein.gamemanager.viewmodels

import androidx.lifecycle.MutableLiveData
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.munstein.gamemanager.base.CoroutineViewModel
import com.munstein.gamemanager.firebase.signin.IFirebaseSignIn
import com.munstein.gamemanager.interactor.user.IUserInteractor
import com.munstein.gamemanager.interactor.user.UserInteractor
import com.munstein.gamemanager.model.User

class LoginViewModel(private val firebaseSignIn: IFirebaseSignIn,
                     private val userInteractor: IUserInteractor) : CoroutineViewModel() {

    val userIsSignedIn by lazy {
        MutableLiveData<Boolean>()
    }

    fun getSignInIntent(): Intent {
        return firebaseSignIn.buildSignInIntent()
    }

    fun signIn(account: GoogleSignInAccount) {
        firebaseSignIn.signInToFirebaseWithGoogleAccount(account).addOnCompleteListener { task ->
            userIsSignedIn.postValue(task.isSuccessful)
            task.result?.user?.let {
                userInteractor.saveUser(User(it.uid, it.displayName ?: ""))
            }
        }
    }

    fun getSignedInAccount(): FirebaseUser? {
        return firebaseSignIn.getSignedInAccount()
    }
}