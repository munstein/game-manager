package com.munstein.gamemanager.firebase

import android.accounts.Account
import android.content.Intent
import com.google.firebase.auth.FirebaseUser

interface IFirebaseSignIn {
    fun buildSignInIntent() : Intent
    fun getSignedInAccount() : FirebaseUser?
    fun logout()
}