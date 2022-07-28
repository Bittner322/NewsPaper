package com.example.newspaper.presentation.favorite_articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.network.NewsResponse
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteArticleViewModel: ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val favoriteArticles = _articles.asStateFlow()

    private val repository = NewsRepository()


    init {
        viewModelScope.launch {
            _articles.emit(repository.getFavoriteArticles())
        }
    }
}