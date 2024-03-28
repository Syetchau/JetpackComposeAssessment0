package io.rapidz.training.repository

import androidx.lifecycle.LiveData
import io.rapidz.training.daos.UserDao
import io.rapidz.training.models.User
import javax.inject.Inject

class Repository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: User, callback: () -> Unit) {
        userDao.insertUser(user)
        callback.invoke()
    }

    fun getUserPassword(username: String): LiveData<String?> {
        return userDao.getUserPassword(username)
    }
}