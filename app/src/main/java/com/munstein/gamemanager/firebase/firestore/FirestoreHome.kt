package com.munstein.gamemanager.firebase.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreHome(private val dbInstance: FirebaseFirestore) : IFirestoreHome {

    override fun addPlatform(id: String, name: String): Task<DocumentReference> {
        return dbInstance.collection(id).add(name)
    }

    override fun removePlatform(id: String, name: String): Task<Void> {
        return dbInstance.collection(id).document(name).delete()
    }
}