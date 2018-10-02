package com.munstein.gamemanager.firebase.signin

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface IFirebaseSignIn {
    fun buildSignInIntent(): Intent
    fun getSignedInAccount(): FirebaseUser?
    fun signInToFirebaseWithGoogleAccount(account: GoogleSignInAccount): Task<AuthResult>
    fun logout()
}