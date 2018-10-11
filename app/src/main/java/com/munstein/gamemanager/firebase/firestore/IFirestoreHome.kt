package com.munstein.gamemanager.firebase.firestore

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference

interface IFirestoreHome {
    fun addPlatform(id: String, name: String): Task<DocumentReference>
    fun removePlatform(id: String, name: String): Task<Void>
}