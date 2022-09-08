package com.example.newspaper.di

import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.presentation.news.NewsViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class NewsFragmentModule {

    @Provides
    fun provideViewModelFactory(newsRepository: NewsRepository): NewsViewModelFactory {
        return NewsViewModelFactory(
            repository = newsRepository
        )
    }

}