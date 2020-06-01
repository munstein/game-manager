package com.munstein.gamemanager.interactor.user

import com.munstein.gamemanager.entity.User

interface IUserInteractor {
    fun getUser(): User
    fun saveUser(user: User)
    fun removeUser()
}