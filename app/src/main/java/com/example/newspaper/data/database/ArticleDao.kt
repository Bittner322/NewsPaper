package com.example.newspaper.data.database

import androidx.room.*
import com.example.newspaper.data.network.NewsResponse

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

    @Query("SELECT * FROM Articles WHERE articleId = (:id)")
    fun getArticleById(id: Int) : Article

    @Query("SELECT * FROM Articles WHERE articleId = (:id) AND isFavorite = (:isFavorite)")
    fun getFavoriteArticleById(id: Int, isFavorite: Boolean): Article

    @Query("UPDATE Articles SET isFavorite = 1 WHERE articleId = (:id)")
    fun setArticleFavorite(id: Int)

    @Query("UPDATE Articles SET isFavorite = 0 WHERE articleId = (:id)")
    fun setArticleNonFavorite(id: Int)

    @Query("SELECT isFavorite FROM Articles WHERE articleId = (:id)")
    fun checkArticleIsFavorite(id: Int): Boolean

    @Query("SELECT * FROM Articles INNER JOIN ArticleHistory ON Articles.articleId = ArticleHistory.articleId")
    fun getAllHistoryArticles(): List<Article>

}