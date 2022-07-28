package com.example.newspaper.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArticleHistory")
data class ArticleHistory(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "articleId") val articleId: Int,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
)