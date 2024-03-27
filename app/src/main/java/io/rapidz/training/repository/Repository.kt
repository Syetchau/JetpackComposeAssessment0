package io.rapidz.training.repository

import io.rapidz.training.daos.UserDao
import io.rapidz.training.models.User
import javax.inject.Inject

class Repository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun dropUserTable() {
        userDao.dropUserTable()
    }

    fun checkIfUserExists(username: String): Boolean {
        return userDao.checkIfUserExists(username)
    }
}