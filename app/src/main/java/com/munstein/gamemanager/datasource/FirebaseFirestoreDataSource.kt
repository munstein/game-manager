package com.munstein.gamemanager.datasource

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.firebase.signin.IFirebaseSignIn

class FirebaseFirestoreDataSource(private val databaseReference: FirebaseFirestore, private val firebaseSignIn: IFirebaseSignIn) {

    private val root = "user_data"

    private fun getRootReference(): DocumentReference {
        firebaseSignIn.getSignedInAccount()?.let {
            return databaseReference.collection(root).document(it.uid)
        }
        throw Exception("No account found.")
    }
}