package com.example.newspaper.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "categoryName")
    val categoryName: String,
)
