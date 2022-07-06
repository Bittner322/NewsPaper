package com.example.newspaper.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspaper.data.NewsRepository
import com.example.newspaper.data.NewsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private val _news = MutableStateFlow<List<NewsResponse.Articles>>(emptyList())
    val news = _news.asStateFlow()

    private val repository = NewsRepository()

    init {
        viewModelScope.launch {
            val response = repository.getNews()
            _news.emit(response)
        }
    }
}