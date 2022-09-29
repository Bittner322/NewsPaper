package com.example.newspaper.di

import com.example.newspaper.data.database.databases.ArticleDatabase
import com.example.newspaper.data.network.NewsService
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    NetworkModule::class,
    DatabaseModule::class,
])
@Singleton
interface AppComponent {

    val database: ArticleDatabase
    val network: NewsService

}