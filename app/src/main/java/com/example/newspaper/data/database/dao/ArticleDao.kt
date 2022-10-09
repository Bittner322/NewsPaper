package com.example.newspaper.data.database.dao

import androidx.room.*
import com.example.newspaper.data.database.models.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(article: Article)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(articles: List<Article>)

    @Update
    suspend fun update(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Articles ORDER BY publishedAt DESC")
    fun getAllArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Articles WHERE isFavorite = 1")
    fun getFavoriteArticles(): List<Article>

    @Query("SELECT * FROM Articles WHERE url = (:id)")
    fun getArticleById(id: String) : Article

    @Query("SELECT * FROM Articles WHERE url = (:id) AND isFavorite = (:isFavorite)")
    fun getFavoriteArticleById(id: Int, isFavorite: Boolean): Article

    @Query("UPDATE Articles SET isFavorite = 1 WHERE url = (:url)")
    fun setArticleFavorite(url: String)

    @Query("UPDATE Articles SET isFavorite = 0 WHERE url = (:url)")
    fun setArticleNonFavorite(url: String)

    @Query("SELECT * FROM Articles INNER JOIN ArticleHistory ON Articles.url = ArticleHistory.url")
    fun getHistoryArticles(): List<Article>

    @Query("SELECT * FROM Articles WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY publishedAt DESC")
    fun getAllArticlesByQuery(query: String): Flow<List<Article>>
}