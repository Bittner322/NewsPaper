package com.example.newspaper.di

import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.presentation.favorite_articles.FavoriteArticleViewModelFactory

class FavoriteArticleModule(
    networkModule: NetworkModule,
    databaseModule: DatabaseModule,
) {

    private val newsRepository = NewsRepository(
        newsService = networkModule.newsService,
        articleDatabase = databaseModule.articleDatabase
    )

    val viewModelFactory = FavoriteArticleViewModelFactory(
        repository = newsRepository
    )
}