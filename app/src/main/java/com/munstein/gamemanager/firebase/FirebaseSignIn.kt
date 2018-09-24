package com.munstein.gamemanager.firebase

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseSignIn(val context: Context) : IFirebaseSignIn {

    private val firebaseAuthInstance by lazy {
        FirebaseAuth.getInstance()
    }

    override fun buildSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("320641422271-6fr92pm1c6q385i8u2h92s1t6i6j21di.apps.googleusercontent.com")
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

    override fun signInToFirebaseWithGoogleAccount(account: GoogleSignInAccount) : Task<AuthResult> {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        return FirebaseAuth.getInstance().signInWithCredential(credential)
    }
}