package com.munstein.gamemanager.datasource

import com.google.firebase.firestore.CollectionReference

abstract class FirestoreDataSource {
    abstract val collectionName: String
    protected val userCollection = "user_data"
    abstract fun getCollection(): CollectionReference
}