package com.munstein.gamemanager.interactor.user

import com.munstein.gamemanager.model.User
import com.munstein.gamemanager.repository.user.IUserRepository

class UserInteractor(private val repository: IUserRepository) : IUserInteractor {
    override fun getUser(): User {
        return repository.getUser()
    }

    override fun saveUser(user: User) {
        repository.saveUser(user)
    }
}