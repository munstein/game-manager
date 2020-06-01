package com.munstein.gamemanager.datasource

import com.google.firebase.firestore.CollectionReference

abstract class FirestoreDataSource {
    abstract val collectionName: String
    protected val userCollection = "users"
    abstract fun getCollection(): CollectionReference
}