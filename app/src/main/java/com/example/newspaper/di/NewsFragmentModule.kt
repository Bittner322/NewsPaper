package com.example.newspaper.di

import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.presentation.news.NewsViewModelFactory

class NewsFragmentModule(
    networkModule: NetworkModule,
    databaseModule: DatabaseModule,
) {

    private val newsRepository = NewsRepository(
        newsService = networkModule.newsService,
        articleDatabase = databaseModule.articleDatabase
    )

    val viewModelFactory = NewsViewModelFactory(
        repository = newsRepository
    )

}