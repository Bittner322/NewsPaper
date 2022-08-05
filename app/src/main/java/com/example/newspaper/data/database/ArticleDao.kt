package com.example.newspaper.data.database

import androidx.room.*

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Update
    suspend fun update(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Articles")
    fun getAllArticles(): List<Article>

    @Query("SELECT * FROM Articles WHERE isFavorite = 1")
    fun getAllFavoriteArticles(): List<Article>

    @Query("SELECT * FROM Articles WHERE url = (:id)")
    fun getArticleById(id: String) : Article

    @Query("SELECT * FROM Articles WHERE url = (:id) AND isFavorite = (:isFavorite)")
    fun getFavoriteArticleById(id: Int, isFavorite: Boolean): Article

    @Query("UPDATE Articles SET isFavorite = 1 WHERE url = (:id)")
    fun setArticleFavorite(id: String)

    @Query("UPDATE Articles SET isFavorite = 0 WHERE url = (:id)")
    fun setArticleNonFavorite(id: String)

    @Query("SELECT * FROM Articles INNER JOIN ArticleHistory ON Articles.url = ArticleHistory.url")
    fun getAllHistoryArticles(): List<Article>

}