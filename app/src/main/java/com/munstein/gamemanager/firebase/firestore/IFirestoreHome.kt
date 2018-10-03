package com.munstein.gamemanager.firebase.firestore

interface IFirestoreHome {
    fun createRootElement(id: String)
    fun addPlatform(name: String)
    fun removePlatform(name: String)
}