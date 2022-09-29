package com.example.newspaper.data.repositories

import com.example.newspaper.data.database.models.Article
import com.example.newspaper.data.database.databases.ArticleDatabase
import com.example.newspaper.data.database.models.ArticleHistory
import com.example.newspaper.data.database.models.Category
import com.example.newspaper.data.network.NewsService
import com.example.newspaper.data.repositories.models.CategoryCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val articleDatabase: ArticleDatabase
) {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val currentDate = Calendar.getInstance().apply {
        add(Calendar.MONTH, -1)
    }.time

    private val from = runCatching {
        simpleDateFormat.format(currentDate)
    }.getOrNull().orEmpty()

    suspend fun getCategoriesForRequest(): List<Category> {
        return withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().getCategories()
        }
    }

    suspend fun addCategoryIntoDatabase(category: Category) {
        withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().add(category)
        }
    }

    suspend fun deleteCategoryFromDatabase(category: Category) {
        withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().delete(category)
        }
    }

    fun getNewsFlow(): Flow<List<Article>> {
        return articleDatabase.articleDao().getAllArticles()
    }

    suspend fun getFavoriteArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            articleDatabase.articleDao().getFavoriteArticles()
        }
    }

    suspend fun loadAllArticlesIntoDatabase(): Result<Unit> {
        return runCatching {
            val newsFromNetwork = mapNews()
            articleDatabase.articleDao().insertAll(newsFromNetwork)
        }
    }

    suspend fun getNewsBySearchQuery(query: String) : Result<List<Article>> {
       return runCatching { mapNewsBySearchQuery(query) }
    }

    private suspend fun mapNewsBySearchQuery(query: String): List<Article> {
        val mappedNews = newsService.getNews(q = query, from = from).articles.map {
            Article(
                it.author.orEmpty(),
                it.title.orEmpty(),
                it.description.orEmpty(),
                it.url,
                it.urlToImage.orEmpty(),
                simpleDateFormat.parse(it.publishedAt)?.time ?: currentDate.time,
                it.content,
                isFavorite = false
            )
        }
        return mappedNews
    }

    private suspend fun mapNews(): List<Article> {
        val mappedItems = newsService.getNews(from = from).articles.map {
            Article(
                it.author.orEmpty(),
                it.title.orEmpty(),
                it.description.orEmpty(),
                it.url,
                it.urlToImage.orEmpty(),
                simpleDateFormat.parse(it.publishedAt)?.time ?: currentDate.time,
                it.content,
                isFavorite = false
            )
        }
        return mappedItems
    }

    suspend fun loadCategorizedNewsIntoDatabase(): Result<Unit> {
        return runCatching {
            val categorizedNews = mapCategorizedNews()
            articleDatabase.articleDao().insertAll(categorizedNews)
        }
    }

    private suspend fun mapCategorizedNews(): List<Article> {
        val mappedNews = newsService.getNewsByCategories(category = getChosenCategories()).articles.map {
            Article(
                it.author.orEmpty(),
                it.title.orEmpty(),
                it.description.orEmpty(),
                it.url,
                it.urlToImage.orEmpty(),
                simpleDateFormat.parse(it.publishedAt)?.time ?: currentDate.time,
                it.content,
                isFavorite = false
            )
        }
        return mappedNews
    }

    suspend fun getCategories(): List<CategoryCard> {
        return withContext(Dispatchers.IO) {
            CategoryCard.values().toList()
        }
    }

    suspend fun getHistoryArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            articleDatabase.articleDao().getHistoryArticles()
        }
    }

    suspend fun addItemToHistory(article: Article) {
        withContext(Dispatchers.IO) {
            articleDatabase.historyDao().add(ArticleHistory(article.url, article.publishedAt))
        }
    }

    suspend fun clearHistory() {
        withContext(Dispatchers.IO) {
            articleDatabase.historyDao().clearHistory()
        }
    }

    suspend fun setArticleFavorite(article: Article) {
        withContext(Dispatchers.IO) {
            articleDatabase.articleDao().setArticleFavorite(article.url)
        }
    }

    suspend fun setArticleNonFavorite(article: Article) {
        withContext(Dispatchers.IO) {
            articleDatabase.articleDao().setArticleNonFavorite(article.url)
        }
    }

    private suspend fun getChosenCategories(): List<Category> {
        return withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().getCategories()
        }
    }

}
