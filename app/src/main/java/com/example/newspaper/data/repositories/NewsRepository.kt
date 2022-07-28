package com.example.newspaper.data.repositories

import com.example.newspaper.MainApplication
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.database.ArticleHistory
import com.example.newspaper.data.network.NewsResponse
import com.example.newspaper.data.network.NewsServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class NewsRepository {

    private val newsService = NewsServiceFactory.newsService
    private val articleDatabase = ArticleDatabase.getInstance(MainApplication.applicationContext())

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val date = Calendar.getInstance().apply {
        add(Calendar.MONTH, -1)
    }.time

    private val from = runCatching {
        simpleDateFormat.format(date)
    }.getOrNull().orEmpty()

    suspend fun getNews(): List<Article> {
        return withContext(Dispatchers.IO) {
            articleDatabase.articleDao().getAllArticles()
        }
    }

    suspend fun getFavoriteArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            articleDatabase.articleDao().getAllFavoriteArticles()
        }
    }

    suspend fun loadAllArticlesIntoDatabase() {
        articleDatabase.articleDao().insertAll(newsService.getNews(from = from).articles.map {
            Article(it.source, it.author ?: "", it.title, it.description ?: "", it.url, it.urlToImage ?: "", it.publishedAt, it.content, isFavorite = false)
        })
    }

    suspend fun getHistoryArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            articleDatabase.articleDao().getAllHistoryArticles()
        }
    }
}
