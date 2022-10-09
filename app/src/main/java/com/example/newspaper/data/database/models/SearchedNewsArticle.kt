package com.example.newspaper.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchedNewsArticles")
data class SearchedNewsArticle(
    @PrimaryKey
    @ColumnInfo(name = "url") val url: String,

    @ColumnInfo(name = "publishedAt") val publishedAt: Long,
)
