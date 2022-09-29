package com.example.newspaper.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(

    @ColumnInfo(name = "username") val username: String,

    @ColumnInfo(name = "userImage")
    val userImage: String,

    @ColumnInfo(name = "isCurrentUser")
    val isCurrentUser: Boolean,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: Int = 0
}
