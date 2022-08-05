package com.example.newspaper.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "ArticleHistory")
data class ArticleHistory(
    @PrimaryKey @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: Long,
)