package com.example.newspaper.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(article: ArticleHistory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleHistory>)

    @Query("DELETE FROM ArticleHistory")
    suspend fun clearHistory()
}