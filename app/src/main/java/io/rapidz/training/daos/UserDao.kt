package io.rapidz.training.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.rapidz.training.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("delete from user")
    suspend fun dropUserTable()

    @Query("select exists (select 1 from user where username = :username)")
    fun checkIfUserExists(username: String): Boolean
}