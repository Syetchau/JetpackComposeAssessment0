package io.rapidz.training.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import io.rapidz.training.daos.UserDao
import io.rapidz.training.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao() : UserDao
}