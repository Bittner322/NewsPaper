package com.example.newspaper.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newspaper.data.network.NewsResponse


@Entity(tableName = "Articles")
data class Article(
    @ColumnInfo(name = "source") val source: NewsResponse.Articles.Source,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "urlToImage")  val urlToImage: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
    @ColumnInfo(name = "content") val content : String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "articleId") var articleId: Int = 0
}
