package com.example.newspaper.data.database

import androidx.room.*

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

    @Query("UPDATE User SET userImage = (:image) WHERE userId = (:id) ")
    suspend fun  setUserImage(id: Int, image: String)
}