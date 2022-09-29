package com.example.newspaper.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newspaper.data.database.models.ArticleHistory

@Dao
interface ArticleHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(article: ArticleHistory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleHistory>)

    @Query("DELETE FROM ArticleHistory")
    suspend fun clearHistory()
}