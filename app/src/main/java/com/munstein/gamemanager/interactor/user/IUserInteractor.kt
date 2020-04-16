package com.munstein.gamemanager.interactor.user

import com.munstein.gamemanager.model.User

interface IUserInteractor {
    fun getUser() : User
    fun saveUser(user : User)
}