package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.presentation.full_article.FullArticleViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class FullArticleModule {

    @Provides
    fun provideViewModelFactory(articleDatabase: ArticleDatabase) : FullArticleViewModelFactory {
        return FullArticleViewModelFactory(
            url = "",
            database = articleDatabase
        )
    }
}