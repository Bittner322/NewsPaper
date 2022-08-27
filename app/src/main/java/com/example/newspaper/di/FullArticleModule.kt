package com.example.newspaper.di

import com.example.newspaper.presentation.full_article.FullArticleViewModelFactory

class FullArticleModule(
    url: String,
    databaseModule: DatabaseModule
) {
    val viewModelFactory = FullArticleViewModelFactory(
        url = url,
        database = databaseModule.articleDatabase
    )
}