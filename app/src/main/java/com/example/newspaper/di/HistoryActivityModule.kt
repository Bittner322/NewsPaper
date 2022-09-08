package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.network.NewsService
import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.presentation.history.HistoryActivityModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class HistoryActivityModule {



    @Provides
    fun provideViewModelFactory(newsRepository: NewsRepository): HistoryActivityModelFactory {
        return HistoryActivityModelFactory(
            repository = newsRepository
        )
    }

}