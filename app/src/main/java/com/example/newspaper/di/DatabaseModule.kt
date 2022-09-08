package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideArticleDatabase(): ArticleDatabase = ArticleDatabase.INSTANCE

}