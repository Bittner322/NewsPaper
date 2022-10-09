package com.example.newspaper.data.database.dao

import androidx.room.*
import com.example.newspaper.data.database.models.SearchedNewsArticle

@Dao
interface SearchedNewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(searchedNewsArticle: SearchedNewsArticle)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(searchedNewsArticles: List<SearchedNewsArticle>)

    @Query("DELETE FROM SearchedNewsArticles")
    suspend fun clearSearchedNews()
}