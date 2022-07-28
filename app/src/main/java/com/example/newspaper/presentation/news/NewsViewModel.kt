package com.example.newspaper.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.MyApplication
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.database.ArticleDatabase
import com.example.newspaper.data.database.ArticleHistory
import com.example.newspaper.data.repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news = _news.asStateFlow()

    private val repository = NewsRepository()


    init {
        viewModelScope.launch {
            repository.loadAllArticlesIntoDatabase()
            val response = repository.getNews()
            _news.emit(response)
        }
    }


    fun addArticleToHistory(article: Article) {
        viewModelScope.launch {
            repository.addItemToHistory(article)
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