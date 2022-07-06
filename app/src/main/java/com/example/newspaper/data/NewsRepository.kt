package com.example.newspaper.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository {

    private val newsService = NewsServiceFactory.newsService

    suspend fun getNews(): List<NewsResponse.Articles> {
        return withContext(Dispatchers.IO) {
            newsService.getNews().articles
        }
    }
}