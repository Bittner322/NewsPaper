package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.network.NewsService
import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.presentation.onboarding_flow.CategoryFragmentViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class CategoryFragmentModule {

    @Provides
    fun provideNewsRepository(
        newsService: NewsService,
        articleDatabase: ArticleDatabase
    ): NewsRepository {
        return NewsRepository(
            newsService = newsService,
            articleDatabase = articleDatabase,
        )
    }

    @Provides
    fun provideViewModelFactory(newsRepository: NewsRepository): CategoryFragmentViewModelFactory {
        return CategoryFragmentViewModelFactory(
            repository = newsRepository
        )
    }
}