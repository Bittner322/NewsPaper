package com.example.newspaper.di

import com.example.newspaper.data.database.ArticleDatabase

class DatabaseModule {

    val articleDatabase = ArticleDatabase.INSTANCE

}