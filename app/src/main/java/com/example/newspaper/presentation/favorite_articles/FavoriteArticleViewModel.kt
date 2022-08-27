package com.example.newspaper.presentation.favorite_articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteArticleViewModel(
    private val repository: NewsRepository
): ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val favoriteArticles = _articles.asStateFlow()


    init {
        viewModelScope.launch {
            _articles.emit(repository.getFavoriteArticles())
        }
    }

    fun setArticleFavorite(article: Article) {
        viewModelScope.launch {
            repository.setArticleFavorite(article)
        }
    }

    fun setArticleNonFavorite(article: Article) {
        viewModelScope.launch {
            repository.setArticleNonFavorite(article)
        }
    }
}

class FavoriteArticleViewModelFactory(
    private val repository: NewsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteArticleViewModel(repository) as T
    }
}

