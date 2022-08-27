package com.example.newspaper.di

import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.presentation.history.HistoryActivityModelFactory

class HistoryActivityModule(
    networkModule: NetworkModule,
    databaseModule: DatabaseModule,
) {

    private val newsRepository = NewsRepository(
        newsService = networkModule.newsService,
        articleDatabase = databaseModule.articleDatabase
    )

    val viewModelFactory = HistoryActivityModelFactory(
        repository = newsRepository
    )

}