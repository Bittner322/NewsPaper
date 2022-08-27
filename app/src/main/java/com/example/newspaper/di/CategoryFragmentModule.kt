package com.example.newspaper.di

import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.presentation.onboarding_flow.CategoryFragmentViewModelFactory

class CategoryFragmentModule(
    networkModule: NetworkModule,
    databaseModule: DatabaseModule,
) {

    private val newsRepository = NewsRepository(
        newsService = networkModule.newsService,
        articleDatabase = databaseModule.articleDatabase
    )

    val viewModelFactory = CategoryFragmentViewModelFactory(
        repository = newsRepository
    )
}