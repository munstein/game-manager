package com.munstein.gamemanager.remote

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseFirestoreRemote(private val databaseReference: FirebaseFirestore, private val user: FirebaseUser) {

    private val root = "user_data"

    private fun getRootReference(): DocumentReference {
        return databaseReference.collection(root).document(user.uid)
    }
}