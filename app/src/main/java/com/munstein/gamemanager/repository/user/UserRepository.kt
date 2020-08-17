package com.munstein.gamemanager.repository.user

import com.munstein.gamemanager.entity.User
import com.orhanobut.hawk.Hawk

class UserRepository : IUserRepository {
    private val userKey = "user_key"

    override fun getUser(): User = Hawk.get(userKey)

    override fun saveUser(user: User) {
        Hawk.put(userKey, user)
    }

    override fun removeUser() {
        Hawk.delete(userKey)
    }
}