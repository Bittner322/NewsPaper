package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideArticleDatabase(): ArticleDatabase = ArticleDatabase.INSTANCE

}