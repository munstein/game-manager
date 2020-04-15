package com.munstein.gamemanager.local

import com.google.firebase.auth.FirebaseUser
import com.munstein.gamemanager.model.User
import com.orhanobut.hawk.Hawk

class UserLocal {
    private val userKey = "user_key"

    fun getUser(): User = Hawk.get(userKey)

    fun saveUser(user: User) {
        Hawk.put(userKey, user)
    }
}