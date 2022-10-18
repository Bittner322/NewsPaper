package com.example.newspaper.data.repositories

import com.example.newspaper.data.database.databases.ArticleDatabase
import com.example.newspaper.data.database.models.Article
import com.example.newspaper.data.network.NewsService
import com.example.newspaper.data.repositories.models.CategoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
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

    suspend fun setCategoryIsSelected(id: Int) {
        withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().setCategoryIsSelected(id)
        }
    }

    suspend fun setCategoryIsNotSelected(id: Int) {
        withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().setCategoryIsNotSelected(id)
        }
    }

    fun getNewsFlow(): Flow<List<Article>> {
        return articleDatabase.articleDao().getAllArticles()
            .flowOn(Dispatchers.IO)
    }

    suspend fun getFavoriteArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            articleDatabase.articleDao().getFavoriteArticles()
        }
    }

    suspend fun loadAllArticlesIntoDatabase(query: String): Result<Unit> {
        return runCatching {
            val newsFromNetwork = mapNewsBySearchQuery(query)
            articleDatabase.articleDao().insertAll(newsFromNetwork)
        }
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
                it.content.orEmpty(),
                isFavorite = false,
                isInHistory = false,
            )
        }
        return mappedNews
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
                author = it.author.orEmpty(),
                title = it.title.orEmpty(),
                description = it.description.orEmpty(),
                url = it.url,
                urlToImage = it.urlToImage.orEmpty(),
                publishedAt = simpleDateFormat.parse(it.publishedAt)?.time ?: currentDate.time,
                content = it.content.orEmpty(),
                isFavorite = false,
                isInHistory = false,
            )
        }
        return mappedNews
    }

    suspend fun getCategoryCards(): List<CategoryData> {
        return withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().getCategories().map {
                CategoryData(
                    id = it.id,
                    categoryName = it.categoryName,
                    isSelected = it.isSelected,
                )
            }
        }
    }

    suspend fun getHistoryArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            articleDatabase.articleDao().getAllHistoricalArticles()
        }
    }

    suspend fun addItemToHistory(article: Article) {
        withContext(Dispatchers.IO) {
            articleDatabase.articleDao().setArticleToHistoryByUrl(article.url)
        }
    }

    suspend fun clearHistory() {
        withContext(Dispatchers.IO) {
            articleDatabase.articleDao().clearHistory()
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

    private suspend fun getChosenCategories(): List<String> {
        return withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().getChosenCategoriesForRequest()
        }
    }

    suspend fun deleteAllCategoriesFromDatabase() {
        withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().deleteAllCategoriesFromDatabase()
        }
    }

    fun getAllArticles(query: String): Flow<List<Article>> {
        return articleDatabase.articleDao().getAllArticlesByQuery(query)
            .flowOn(Dispatchers.IO)
    }

    suspend fun getCountOfSelectedCategories(): Int {
        return withContext(Dispatchers.IO) {
            articleDatabase.categoryDao().getCountOfSelectedCategories()
        }
    }

    suspend fun deleteAllArticlesFromDatabase() {
        withContext(Dispatchers.IO) {
            articleDatabase.articleDao().clearArticlesTable()
        }
    }
}
