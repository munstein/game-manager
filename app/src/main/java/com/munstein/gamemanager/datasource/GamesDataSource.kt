package com.munstein.gamemanager.datasource

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.munstein.gamemanager.repository.user.IUserRepository

class GamesDataSource(private val databaseReference: FirebaseFirestore, private val userRepository: IUserRepository) : FirestoreDataSource() {

    override val collectionName: String = "games"

    override fun getCollection(): CollectionReference {
        return databaseReference.collection(userCollection)
                .document(userRepository.getUser().id)
                .collection(collectionName)
    }
}