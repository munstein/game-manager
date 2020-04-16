package com.munstein.gamemanager.repository.user

import com.munstein.gamemanager.model.User

interface IUserRepository {
    fun getUser() : User
    fun saveUser(user : User)
}