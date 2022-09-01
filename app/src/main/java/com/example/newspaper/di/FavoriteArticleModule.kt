package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.network.NewsService
import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.presentation.favorite_articles.FavoriteArticleViewModelFactory
import com.example.newspaper.presentation.onboarding_flow.CategoryFragmentViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class FavoriteArticleModule {

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
    fun provideViewModelFactory(newsRepository: NewsRepository): FavoriteArticleViewModelFactory {
        return FavoriteArticleViewModelFactory(
            repository = newsRepository
        )
    }
}