package com.munstein.gamemanager.firebase

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseLogin(val context: Context) : IFirebaseSignIn {

    private val firebaseAuthInstance by lazy {
        FirebaseAuth.getInstance()
    }

    override fun buildSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
        return mGoogleSignInClient.signInIntent
    }

    override fun getSignedInAccount(): FirebaseUser? {
        return firebaseAuthInstance.currentUser
    }

    override fun logout(){
        firebaseAuthInstance.signOut()
    }
}