package com.example.newspaper.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.database.Article
import com.example.newspaper.data.repositories.NewsRepository
import com.example.newspaper.data.network.NewsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
}