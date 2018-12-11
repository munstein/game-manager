package com.munstein.gamemanager.interactor.platform

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference

interface IPlatformInteractor {
    fun addPlatform(name: String): Task<DocumentReference>
    fun removePlatform(name: String): Task<Void>
}