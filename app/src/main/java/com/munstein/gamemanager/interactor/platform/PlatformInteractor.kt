package com.munstein.gamemanager.interactor.platform

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.munstein.gamemanager.repository.platform.IPlatformRepository

class PlatformInteractor(repository : IPlatformRepository) : IPlatformInteractor {
    override fun addPlatform(name: String): Task<DocumentReference> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removePlatform(name: String): Task<Void> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}