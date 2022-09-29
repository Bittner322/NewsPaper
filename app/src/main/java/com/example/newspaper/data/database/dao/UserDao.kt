package com.example.newspaper.data.database.dao

import androidx.room.*
import com.example.newspaper.data.database.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("UPDATE User SET username = (:username) WHERE isCurrentUser = 1")
    suspend fun setUsername(username: String)

    @Query("SELECT username FROM user WHERE isCurrentUser = 1")
    suspend fun getUsername(): String
}