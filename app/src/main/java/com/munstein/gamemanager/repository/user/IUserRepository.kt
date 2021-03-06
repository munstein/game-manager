package com.munstein.gamemanager.repository.user

import com.munstein.gamemanager.entity.User

interface IUserRepository {
    fun getUser(): User
    fun saveUser(user: User)
    fun removeUser()
}