package com.example.newspaper.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId: Int,

    @ColumnInfo(name = "username") val username: String,

    @ColumnInfo(name = "userImage")
    val userImage: String,
)
